package dev.danielk0121.test.musinsa.shortenurl.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.danielk0121.test.musinsa.shortenurl.domain.ShortenUrl;

@Service
public class ShortenUrlDao {

	private static final String SQLID_selectNow = "selectNow";
	private static final String SQLID_insertShortenUrl = "insertShortenUrl";
	private static final String SQLID_selectOneShortenUrlById = "selectOneShortenUrlById";
	private static final String SQLID_selectOneShortenUrlByEncodedUrl = "selectOneShortenUrlByEncodedUrl";
	private static final String SQLID_updateShortenUrlByIdOnViewcnt = "updateShortenUrlByIdOnViewcnt";
	
	@Autowired private SqlSessionTemplate template;

	public String selectNow() {
		return template.selectOne(SQLID_selectNow);
	}
	
	public int insertShortenUrl(ShortenUrl param) {
		return template.insert(SQLID_insertShortenUrl, param);
	}
	
	public ShortenUrl selectOneShortenUrlById(ShortenUrl param) {
		return template.selectOne(SQLID_selectOneShortenUrlById, param);
	}
	
	public ShortenUrl selectOneShortenUrlByEncodedUrl(ShortenUrl param) {
		return template.selectOne(SQLID_selectOneShortenUrlByEncodedUrl, param);
	}
	
	public int updateShortenUrlByIdOnViewcnt(ShortenUrl param) {
		return template.update(SQLID_updateShortenUrlByIdOnViewcnt, param);
	}
}
