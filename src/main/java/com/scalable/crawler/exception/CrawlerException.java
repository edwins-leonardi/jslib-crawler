package com.scalable.crawler.exception;

public class CrawlerException extends Exception {
	private static final long serialVersionUID = 4799079987257682812L;

	public CrawlerException(Throwable t) {
		super("There was a problem searching your term, please check your connectivity!", t);
	}
}
