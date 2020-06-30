package dev.danielk0121.test.musinsa.shortenurl.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = EncodingService.class)
public class EncodingServiceTest {

	@Autowired private EncodingService service;
	
	@Test
	public void t0_인코딩테스트() {

		long term = System.currentTimeMillis();
		
//		long id = 12500;
//		long id = Long.MAX_VALUE;
//		long id = Integer.MAX_VALUE;
//		long id = 9_223_372_036_854_775_807L;  // Long.MAX_VALUE
//		long id =             2_147_483_647L;  // Integer.MAX_VALUE
		long id =       218_340_105_584_895L;  // 62진법 8자리 최대값
		
		String shortenKey = service.encodeToShortenKey(id);
		long reversedId = service.decodeByShortenKey(shortenKey);
		
		term = (System.currentTimeMillis() - term);
		
		log.debug("term: {}, id: {}, shortenKey: {}, reversedId: {}", term, id, shortenKey, reversedId);
		assertThat(id).isEqualTo(reversedId);
		assertThat(shortenKey.length()).isLessThan(9);
	}
	
	@Test
	public void t1_md5base64테스트() {
		
		long term = System.currentTimeMillis();
		String input = "asdfqwer";
		String code1 = service.encodeMd5AndBase64(input);
		String code2 = "";
		for(int i = 0; i < 100_000; i++) {
			code2 = service.encodeMd5AndBase64(input);
			assertThat(code1).isEqualTo(code2);
		}
		term = (System.currentTimeMillis() - term);
		
		log.debug("term: {}, input: {}, code2: {}", term, input, code2);
	}
}
