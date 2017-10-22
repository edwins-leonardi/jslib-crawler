package com.scalable.crawler;

import java.util.List;

import com.scalable.crawler.exception.CrawlerException;

public interface Crawler {
	List<String> getSearchResults(String searchTerm) throws CrawlerException;
}
