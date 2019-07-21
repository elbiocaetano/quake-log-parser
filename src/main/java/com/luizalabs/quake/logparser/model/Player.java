package com.luizalabs.quake.logparser.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player implements Serializable {

	private static final long serialVersionUID = 1231922581795881337L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	
	public Player(Long id, String name, Long kills) {
		super();
		this.id = id;
		this.name = name;
		this.kills = kills;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "Player [id=" + id + ", name=" + name + ", kills=" + kills + "]";
	}

}
