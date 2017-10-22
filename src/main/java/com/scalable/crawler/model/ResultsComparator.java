package com.scalable.crawler.model;

import java.util.Comparator;

public class ResultsComparator implements Comparator<ResultDTO> {

	@Override
	public int compare(ResultDTO a, ResultDTO b) {
		return b.getOcurrences().compareTo(a.getOcurrences());
	}
}
