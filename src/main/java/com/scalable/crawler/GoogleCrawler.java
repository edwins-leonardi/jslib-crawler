package com.scalable.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.scalable.crawler.exception.CrawlerException;
import com.scalable.crawler.http.PageRequester;

public class GoogleCrawler implements Crawler {

	private static final String URL = "https://www.google.com/search?q=";
	private PageRequester pageRequester;

	public GoogleCrawler() {
		this.pageRequester = new PageRequester();
	}

	public List<String> getSearchResults(String searchTerm) throws CrawlerException {
		List<String> results = new ArrayList<>();
		try {
			String resultPage = pageRequester.getResultPage(URL + searchTerm);
			Document doc = Jsoup.parse(resultPage);
			Elements rows = doc.select(".g");
			for (Element e : rows) {
				Elements row = e.select(".r");
				Node aElement = row.get(0).childNode(0);
				String href = aElement.attr("href");
				results.add(href.replaceAll("/url\\?q=", "").split("&sa=U&ved=0")[0]);
			}
		} catch (Exception ex) {
			throw new CrawlerException(ex);
		}

		return results;
	}
}
