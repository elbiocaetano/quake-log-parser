package com.luizalabs.quake.logparser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizalabs.quake.logparser.model.Game;
import com.luizalabs.quake.logparser.repository.GameRepository;
import com.luizalabs.quake.logparser.repository.PlayerRepository;
import com.luizalabs.quake.logparser.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerRepository playerRepository;

	public Game save(Game game) {
		game.getPlayers().forEach(player -> this.playerRepository.save(player));
		return this.gameRepository.save(game);
	}
}
