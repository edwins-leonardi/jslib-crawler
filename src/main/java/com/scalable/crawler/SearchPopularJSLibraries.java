package com.scalable.crawler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.scalable.crawler.http.PageRequester;
import com.scalable.crawler.model.ResultDTO;
import com.scalable.crawler.model.ResultsComparator;

public class SearchPopularJSLibraries {

	private String searchTerm;
	private PageRequester pageRequester;
	private HashMap<String, ResultDTO> results = new HashMap<>();

	public SearchPopularJSLibraries(String searchTerm) {
		this.searchTerm = searchTerm;
		pageRequester = new PageRequester();
	}

	public List<String> getPopularLibraries() throws Exception {
		Crawler crawler = new GoogleCrawler();
		List<ResultDTO> results = extractResultFromSearchResults(crawler.getSearchResults(searchTerm));
		return results.stream().map(r -> r.getName()).collect(Collectors.toList());
	}

	private List<ResultDTO> extractResultFromSearchResults(List<String> sites) {
		for (String url : sites) {
			try {
				String page = pageRequester.getResultPage(url);
				Document doc = Jsoup.parse(page);
				Elements elements = doc.getElementsByTag("script");
				for (Element el : elements) {
					if (!el.hasAttr("async") && el.hasAttr("src") && !el.hasAttr("crossorigin")) {
						String src = el.absUrl("src");
						if (src.lastIndexOf("/") != -1) {
							String script = src.substring(src.lastIndexOf("/") + 1);
							if (script.contains(".js")) {
								String key = script.substring(0, script.indexOf("."));
								if (!results.containsKey(key))
									results.put(key, new ResultDTO(key));
								results.get(key).incrementOcurrences();
							}
						}
					}
				}
			} catch (Exception e) {

			}
		}
		List<ResultDTO> list = new ArrayList<>(results.values());
		Collections.sort(list, new ResultsComparator());
		return list.subList(0, Math.min(list.size(), 5));
	}
}
