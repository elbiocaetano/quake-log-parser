package com.luizalabs.quake.logparser.service;

import org.springframework.data.domain.Page;

import com.luizalabs.quake.logparser.model.Game;

public interface GameService {
	public Game save(Game game);

	public Page<Game> findAll(Integer offset, Integer limit);

	public Game findById(Long id);
}
