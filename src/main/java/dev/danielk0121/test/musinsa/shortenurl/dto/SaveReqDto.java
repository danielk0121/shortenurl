package dev.danielk0121.test.musinsa.shortenurl.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode @NoArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
public class SaveReqDto {

	private String url;

	@Builder
	public SaveReqDto(String url) {
		super();
		this.url = url;
	}
}
