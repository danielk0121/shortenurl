package dev.danielk0121.test.musinsa.shortenurl.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.danielk0121.test.musinsa.shortenurl.domain.ShortenUrl;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString @Getter @NoArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
public class ShortenData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1323909203870800383L;

	/**
	 * id 숫자를 62진법으로 변환 한 문자열 키
	 */
	private String shortenKey;
	
	/**
	 * shortenurl 테이블 도메인
	 */
	private ShortenUrl shortenUrl;

	@Builder
	public ShortenData(String shortenKey, ShortenUrl shortenUrl) {
		super();
		this.shortenKey = shortenKey;
		this.shortenUrl = shortenUrl;
	}
}
