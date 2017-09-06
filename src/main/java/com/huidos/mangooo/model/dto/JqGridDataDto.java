package com.huidos.mangooo.model.dto;

import java.util.List;

public class JqGridDataDto<T> {

	/** Total number of pages */
	private int total;
	/** The current page number */
	private int page;
	/** Total number of records */
	private int records;
	/** The actual data */
	private List<T> rows;

	public JqGridDataDto(int total, int page, int records, List<T> rows) {
		this.total = total;
		this.page = page;
		this.records = records;
		this.rows = rows;
	}

	public JqGridDataDto() {
	}

	public int getTotal() {
		return total;
	}

	public int getPage() {
		return page;
	}

	public int getRecords() {
		return records;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}