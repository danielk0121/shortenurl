package dev.danielk0121.test.musinsa.shortenurl.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString @Getter @NoArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
public class ShortenUrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7257428353976862335L;
	
	private long id;
	private String encodedUrl;
	private String url;
	private long viewcnt;
	
	@Builder
	public ShortenUrl(long id, String encodedUrl, String url, long viewcnt) {
		super();
		this.id = id;
		this.encodedUrl = encodedUrl;
		this.url = url;
		this.viewcnt = viewcnt;
	}
}
