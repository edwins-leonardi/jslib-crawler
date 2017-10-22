package com.scalable.crawler.model;

public class ResultDTO {
	private String name;
	private Integer ocurrences;

	public ResultDTO(String name) {
		this.name = name;
		this.ocurrences = 0;
	}

	public String getName() {
		return name;
	}

	public Integer getOcurrences() {
		return ocurrences;
	}

	public void incrementOcurrences() {
		this.ocurrences++;
	}
}
