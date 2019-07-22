package com.luizalabs.quake.logparser.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
		game.getPlayers().forEach(this.playerRepository::save);
		return this.gameRepository.save(game);
	}

	@Override
	public Page<Game> findAll(Integer offset, Integer limit, String orderBy, String direction) {
		Page<Game> page = this.gameRepository
				.findAll(PageRequest.of(offset, limit, Direction.fromString(direction), orderBy));
		if (page.isEmpty()) {
			throw new ObjectNotFoundException(null, Game.class.getName());
		}
		return page;
	}

	@Override
	public Game findById(Long id) {
		return this.gameRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(id, Game.class.getName()));
	}
}
