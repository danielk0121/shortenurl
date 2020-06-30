package dev.danielk0121.test.musinsa.shortenurl.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.danielk0121.test.musinsa.shortenurl.domain.ShortenUrl;
import dev.danielk0121.test.musinsa.shortenurl.service.EncodingService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ShortenUrlDaoTest {

	@Autowired private ShortenUrlDao dao;
	@Autowired private EncodingService encodingService;
	
//	@Test
	public void t0() {
		
		log.debug("now : {}", dao.selectNow());
	}
	
//	@Test
	public void t1_insert테스트() {
		
		String url = "https://en.wikipedia.org/wiki/URL_shortening";
		String encodedUrl = encodingService.encodeMd5AndBase64(url);
		ShortenUrl param = ShortenUrl.builder()
				.url(url)
				.encodedUrl(encodedUrl)
				.viewcnt(0)
				.build() ;
		log.debug("befor insert, param: {}", param);
		int insertCnt = dao.insertShortenUrl(param);
		log.debug("after insert, param: {}, insertCnt: {}", param, insertCnt);
		
		assertThat(param.getId()).isNotNull();
		assertThat(param.getId()).isNotZero();
	}
}
