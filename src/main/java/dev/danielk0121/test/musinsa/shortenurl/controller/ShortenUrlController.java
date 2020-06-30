package dev.danielk0121.test.musinsa.shortenurl.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.danielk0121.test.musinsa.shortenurl.dto.SaveReqDto;
import dev.danielk0121.test.musinsa.shortenurl.service.ShortenDataService;
import dev.danielk0121.test.musinsa.shortenurl.vo.ShortenData;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class ShortenUrlController {

	@Autowired private ShortenDataService shortenDataService;
	
	@GetMapping(path = "/")
	public String create() {
		
		log.debug("create");
		return "create";
	}
	
	@PostMapping(path = "/save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ShortenData> save(@RequestBody SaveReqDto req) {
		
		log.debug("save, req: {}", req);
		ShortenData shortenData = shortenDataService.saveAndGet(req.getUrl());
		return ResponseEntity.ok(shortenData);
	}
	
	@GetMapping(path = { "/{shortenKey}" })
	public String redirectExternal(
			@PathVariable(name = "shortenKey", required = true) String shortenKey
			) {
		
		log.debug("redirectExternal, shortenKey: {}", shortenKey);
		if(StringUtils.equals(shortenKey, "favicon.ico")) {
			return "forward:/";
		}
		
		ShortenData shortenData = shortenDataService.findByShortenKeyWithCache(shortenKey);
		String url = shortenData.getShortenUrl().getUrl();
		shortenDataService.updateShortenUrlByIdOnViewcntAsync(shortenData);
		return "redirect:" + url;
	}
	
	@GetMapping(path = "/{shortenKey}/+", produces = "application/json")
	public ResponseEntity<ShortenData> search(
			@PathVariable(name = "shortenKey", required = true) String shortenKey
			) {
		
		log.debug("search");
		ShortenData shortenData = shortenDataService.findByShortenKey(shortenKey);
		return ResponseEntity.ok(shortenData);
	}
}
