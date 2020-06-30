package dev.danielk0121.test.musinsa.shortenurl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import dev.danielk0121.test.musinsa.shortenurl.config.RedisCacheKey;
import dev.danielk0121.test.musinsa.shortenurl.dao.ShortenUrlDao;
import dev.danielk0121.test.musinsa.shortenurl.domain.ShortenUrl;
import dev.danielk0121.test.musinsa.shortenurl.vo.ShortenData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShortenDataService {

	@Autowired private ShortenUrlDao shortenUrlDao;
	@Autowired private EncodingService encodingService;
	
	@Async
	public void updateShortenUrlByIdOnViewcntAsync(ShortenData shortenData) {
		
		ShortenUrl param = shortenData.getShortenUrl();
		shortenUrlDao.updateShortenUrlByIdOnViewcnt(param);
	}
	
	/**
	 * 단축키를 디코딩해서  pk id 생성 후, shortenurl 테이블 조회
	 */
	public ShortenData findByShortenKey(String shortenKey) {
		
		long id = encodingService.decodeByShortenKey(shortenKey);
		
		ShortenUrl param = ShortenUrl.builder()
				.id(id)
				.build() ;
		ShortenUrl found = shortenUrlDao.selectOneShortenUrlById(param);
		
		ShortenData shortenData = ShortenData.builder()
				.shortenKey(shortenKey)
				.shortenUrl(found)
				.build() ;
		log.debug("shortenKey: {}, shortenData: {}", shortenKey, shortenData);
		return shortenData;
	}
	
	/**
	 * 캐시 사용, 단축키를 디코딩해서  pk id 생성 후, shortenurl 테이블 조회
	 */
	@Cacheable(value = RedisCacheKey.findByShortenKeyWithCache, 
			key = "#shortenKey", 
			unless = "#result.shortenUrl == null"
			)
	public ShortenData findByShortenKeyWithCache(String shortenKey) {
		
		log.debug("cache_fault, shortenKey: {}", shortenKey);
		return findByShortenKey(shortenKey);
	}
	
	/**
	 * url 에 해당하는 shortenUrl 이 이미 있으면 추가로 생성하지 않고, 이미 있는 것 제공
	 * insert - 디비 입력 후 pk id 받음
	 * id 를 62진법으로 변환 후 문자로 맵핑, 키생성
	 */
	public ShortenData saveAndGet(String url) {
		
		String encodedUrl = encodingService.encodeMd5AndBase64(url);
		
		ShortenUrl alreadyExistsBean = shortenUrlDao.selectOneShortenUrlByEncodedUrl(
				ShortenUrl.builder()
					.encodedUrl(encodedUrl)
					.build());
		if(alreadyExistsBean != null) {
			ShortenData shortenData = convert(alreadyExistsBean);
			return shortenData;
		}
		
		ShortenUrl insertParam = ShortenUrl.builder()
				.encodedUrl(encodedUrl)
				.url(url)
				.viewcnt(0)
				.build() ;
		shortenUrlDao.insertShortenUrl(insertParam);
		
		ShortenData shortenData = convert(insertParam);
		log.debug("url: {}, shortenData: {}", url, shortenData);
		return shortenData;
	}
	
	private ShortenData convert(ShortenUrl shortenUrl) {
		
		long id = shortenUrl.getId();
		String shortenKey = encodingService.encodeToShortenKey(id);
		ShortenData shortenData = ShortenData.builder()
				.shortenKey(shortenKey)
				.shortenUrl(shortenUrl)
				.build() ;
		return shortenData;
	}
	
}
