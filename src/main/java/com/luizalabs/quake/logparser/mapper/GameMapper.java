package com.luizalabs.quake.logparser.mapper;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.luizalabs.quake.logparser.dto.GameDTO;
import com.luizalabs.quake.logparser.dto.MetaDTO;
import com.luizalabs.quake.logparser.dto.ResponseDTO;
import com.luizalabs.quake.logparser.model.Game;
import com.luizalabs.quake.logparser.model.Player;

@Component
public class GameMapper {

	public ResponseDTO map(Page<Game> games) {
		ResponseDTO response = new ResponseDTO();
		response.setRecords(games.stream().map(this::mapSingleGame).collect(Collectors.toList()));
		response.setMeta(new MetaDTO(games.getPageable().getPageSize(), games.getPageable().getPageNumber(),
				games.getNumberOfElements(), games.getTotalElements()));
		return response;
	}

	private GameDTO mapSingleGame(Game game) {
		GameDTO response = new GameDTO(game.getId());
		game.getPlayers().forEach((Player player) -> {
			response.getPlayers().add(player.getName());
			response.getKills().put(player.getName(), player.getKills());
		});
		return response;
	}

}
