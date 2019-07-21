package com.luizalabs.quake.logparser.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {

	private static final long serialVersionUID = -4634763787256957877L;

	private Long id;
	private Long totalKills;
	private List<Player> players;

	public Game() {
		super();
		this.totalKills = 0l;
		this.players = new ArrayList<Player>();
	}

	public Game(Long id, Long totalKills, List<Player> players) {
		super();
		this.id = id;
		this.totalKills = totalKills;
		this.players = players;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTotalKills() {
		return totalKills;
	}

	public void setTotalKills(Long totalKills) {
		this.totalKills = totalKills;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void addKill() {
		this.totalKills += 1;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", totalKills=" + totalKills + ", players=" + players + "]";
	}
}
