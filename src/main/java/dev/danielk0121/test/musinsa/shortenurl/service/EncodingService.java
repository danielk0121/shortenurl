package dev.danielk0121.test.musinsa.shortenurl.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EncodingService {

	private static final String MD5 = "MD5";
	
	/**
	 * BASE62 문자열 맵핑 테이블
	 */
    private static final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    /**
     * base62 인코딩, 나머지가 없을때까지 62로 나눈다, 62진법을 BASE62 테이블에 맵핑
     * , 자리수가 높은것부터 좌측으로 써져야 하지만, 자리수가 낮은것부터 좌측에서 코드를 생성함
     */
    public String encodeToShortenKey(long id) {
    	
    	long value = id;
    	StringBuilder sb = new StringBuilder();
    	do {
    		int remain = (int) (value % 62);
    		sb.append(BASE62[remain]);
    		log.debug("id: {}, value: {}, remain: {}, BASE62[remain]: {}, sb: {}"
    				, id, value, remain, BASE62[remain], sb.toString());
    		value /= 62;
    	} while( value > 0);
    	return sb.toString();
    }
    
    /**
     * code 를 long 으로 변환, 62진법의 10진법 수치를 계산함
     * , 좌측 문자열부터 1의 자리 이므로 그대로 계산
     */
    public long decodeByShortenKey(String code) {
    	
    	long result = 0;
    	long power = 1;
    	
    	for(int i = 0; i < code.length(); i++) {
    		char c = code.charAt(i);
    		int digit = new String(BASE62).indexOf(c);
    		result += digit * power;
    		log.debug("code: {}, i: {}, c: {}, digit: {}, result: {}"
    				, code, i, c, digit, result);
    		power *= 62;
    	}
    	return result;
    }
    
    /**
     * MD5 후 base64 를 통해 단방향 문자열 인코딩
     */
	public String encodeMd5AndBase64(String input) {
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(MD5);
		} catch (NoSuchAlgorithmException e) {
			log.error("", e);
		}
		
		byte[] arr = md.digest(input.getBytes());
		String encoded = Base64.getEncoder().encodeToString(arr);
		return encoded;
	}
}
