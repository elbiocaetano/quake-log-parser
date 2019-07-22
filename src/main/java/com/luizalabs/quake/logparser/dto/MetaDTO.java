package com.luizalabs.quake.logparser.dto;

import java.io.Serializable;

public class MetaDTO implements Serializable {
	private static final long serialVersionUID = -7351352111008938502L;

	private Integer limit;
	private Integer offset;
	private Integer recordCount;
	private Long totalRecords;

	public MetaDTO() {
		super();
	}

	public MetaDTO(Integer limit, Integer offset, Integer recordCount, Long totalRecords) {
		super();
		this.limit = limit;
		this.offset = offset;
		this.recordCount = recordCount;
		this.totalRecords = totalRecords;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
}
