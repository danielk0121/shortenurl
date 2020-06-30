package dev.danielk0121.test.musinsa.shortenurl.study;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * MD5 단방향 문자열 인코딩에 대한 학습 코드
 */
@Slf4j
public class MessageDigestTest {

	@Test
	public void t0() {
		
		String input = "https://en.wikipedia.org/wiki/URL_shortening/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ/asdfㅁㄴㅇㄹ";
		String s1, s2;
		
		s1 = encode(input);
		for(int i = 0; i < 1000; i++) {
			s2 = encode(input);
			if(i % 100 == 0) {
				log.debug("s1: {}, s2: {}", s1, s2);
			}
			assertThat(s1).isEqualTo(s2);  // 인코딩을 여러번 해도 값이 바뀌지 않아야 한다
		}
	}
	
	private String encode(String input) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			log.error("", e);
		}
		
		byte[] arr = md.digest(input.getBytes());
		String encoded = Base64.getEncoder().encodeToString(arr);
		return encoded;
	}
}
