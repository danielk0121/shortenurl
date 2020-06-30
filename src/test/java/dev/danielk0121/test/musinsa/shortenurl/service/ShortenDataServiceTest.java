package dev.danielk0121.test.musinsa.shortenurl.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.danielk0121.test.musinsa.shortenurl.vo.ShortenData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ShortenDataServiceTest {

	@Autowired private ShortenDataService service;
	
//	@Test
	public void t0_캐시테스트_조회데이터() {
		
		String shortenKey = "B";
		ShortenData d1, d2;
		
		d1 = service.findByShortenKeyWithCache(shortenKey);
		for(int i = 0; i < 4; i++) {
			d2 = service.findByShortenKeyWithCache(shortenKey);
			log.debug("d1: {}, d2: {}", d1, d2);
			assertThat(d1.toString()).isEqualTo(d2.toString());
		}
	}
	
//	@Test
	public void t0_캐시테스트_없는데이터() {
		
		String shortenKey = "X";
		ShortenData d1, d2;
		
		d1 = service.findByShortenKeyWithCache(shortenKey);
		for(int i = 0; i < 4; i++) {
			d2 = service.findByShortenKeyWithCache(shortenKey);
			log.debug("d1: {}, d2: {}", d1, d2);
			assertThat(d1.toString()).isEqualTo(d2.toString());
		}
	}
}
