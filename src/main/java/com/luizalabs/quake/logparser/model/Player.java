package com.luizalabs.quake.logparser.model;

import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = 1231922581795881337L;

	private String name;
	private Long kills;

	public Player() {
		super();
		this.kills = 0l;
	}

	public Player(String name, Long kills) {
		super();
		this.name = name;
		this.kills = kills;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getKills() {
		return kills;
	}

	public void setKills(Long kills) {
		this.kills = kills;
	}

	public void addKill() {
		this.kills += 1;
	}

	public void removeKill() {
		if (this.kills > 0) {
			this.kills -= 1;
		}
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", kills=" + kills + "]";
	}
}
