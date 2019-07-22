package com.luizalabs.quake.logparser.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = 4280570170912573282L;

	private MetaDTO meta;

	private List<GameDTO> records;

	public MetaDTO getMeta() {
		return meta;
	}

	public void setMeta(MetaDTO meta) {
		this.meta = meta;
	}

	public List<GameDTO> getRecords() {
		return records;
	}

	public void setRecords(List<GameDTO> records) {
		this.records = records;
	}
}
