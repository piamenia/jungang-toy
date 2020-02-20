package io.dktechin.common.wrapper;

import lombok.Data;

import java.util.List;

@Data
public class PageContents<T> {
	private int offset;// current page
	private int limit; // unit per page
	private int total; // total size
	private List<T> contents;
}
