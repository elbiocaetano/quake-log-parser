package com.luizalabs.quake.logparser.test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.luizalabs.quake.logparser.model.Game;
import com.luizalabs.quake.logparser.model.Player;
import com.luizalabs.quake.logparser.repository.GameRepository;
import com.luizalabs.quake.logparser.repository.PlayerRepository;
import com.luizalabs.quake.logparser.service.impl.GameServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

	@Autowired
	@InjectMocks
	private GameServiceImpl gameService;

	@MockBean
	private GameRepository gameRepository;

	@MockBean
	private PlayerRepository playerRepository;

	@TestConfiguration
	static class GameServiceTestConfiguration {
		@Bean
		public GameServiceImpl gameService() {
			return new GameServiceImpl();
		}
	}

	@Test
	public void saveGameWithoutUserSucess() {
		Game game = new Game();
		when(this.gameRepository.save(game)).thenReturn(new Game(1l, 2l, ((List<Player>) new ArrayList<Player>())));

		Game savedGame = this.gameService.save(game);

		verify(this.gameRepository, times(1)).save(game);
		assertTrue(savedGame.getId() == 1l);
		assertTrue(savedGame.getTotalKills() == 2l);
		assertTrue(savedGame.getPlayers().isEmpty());
	}

	@Test
	public void saveGameWithOneUserSucess() {
		Game game = new Game(null, 3l, this.getListWithOnePlayer());
		when(this.gameRepository.save(game)).thenReturn(new Game(1l, 3l, this.getListWithOneSavedPlayer()));

		Game savedGame = this.gameService.save(game);

		verify(this.gameRepository, times(1)).save(game);
		verify(this.playerRepository, times(1)).save(game.getPlayers().get(0));
		assertTrue(savedGame.getId() == 1l);
		assertTrue(savedGame.getTotalKills() == 3l);
		assertTrue(!savedGame.getPlayers().isEmpty());
		assertTrue(savedGame.getPlayers().size() == 1);
		assertTrue(savedGame.getPlayers().get(0).getId() == 1l);
		assertTrue(savedGame.getPlayers().get(0).getName() == "João");
		assertTrue(savedGame.getPlayers().get(0).getKills() == 2l);
	}
	
	@Test
	public void saveGameWithManyUserSucess() {
		Game game = new Game(null, 5l, this.getListWithManyPlayers());
		when(this.gameRepository.save(game)).thenReturn(new Game(1l, 5l, this.getListWithManySavedPlayers()));

		Game savedGame = this.gameService.save(game);

		verify(this.gameRepository, times(1)).save(game);
		verify(this.playerRepository, times(1)).save(game.getPlayers().get(0));
		verify(this.playerRepository, times(1)).save(game.getPlayers().get(1));
		verify(this.playerRepository, times(1)).save(game.getPlayers().get(2));
		verify(this.playerRepository, times(1)).save(game.getPlayers().get(3));
		assertTrue(savedGame.getId() == 1l);
		assertTrue(savedGame.getTotalKills() == 5l);
		assertTrue(!savedGame.getPlayers().isEmpty());
		assertTrue(savedGame.getPlayers().size() == 4);
		assertTrue(savedGame.getPlayers().get(0).getId() == 1l);
		assertTrue(savedGame.getPlayers().get(0).getName() == "João");
		assertTrue(savedGame.getPlayers().get(0).getKills() == 2l);
		
		assertTrue(savedGame.getPlayers().get(1).getId() == 2l);
		assertTrue(savedGame.getPlayers().get(1).getName() == "Maria");
		assertTrue(savedGame.getPlayers().get(1).getKills() == 3l);
		
		assertTrue(savedGame.getPlayers().get(2).getId() == 3l);
		assertTrue(savedGame.getPlayers().get(2).getName() == "Fulano");
		assertTrue(savedGame.getPlayers().get(2).getKills() == 0l);
		
		assertTrue(savedGame.getPlayers().get(3).getId() == 4l);
		assertTrue(savedGame.getPlayers().get(3).getName() == "Sicrano");
		assertTrue(savedGame.getPlayers().get(3).getKills() == 0l);
	}

	private List<Player> getListWithOnePlayer() {
		Player player = new Player("João", 2l);
		List<Player> players = new ArrayList<>();
		players.add(player);
		return players;
	}

	private List<Player> getListWithOneSavedPlayer() {
		Player player = new Player(1l, "João", 2l);
		List<Player> players = new ArrayList<>();
		players.add(player);
		return players;
	}
	
	private List<Player> getListWithManyPlayers() {
		List<Player> players = new ArrayList<>();
		players.add(new Player("João", 2l));
		players.add(new Player("Maria", 3l));
		players.add(new Player("Fulano", 0l));
		players.add(new Player("Sicrano", 0l));
		return players;
	}
	
	private List<Player> getListWithManySavedPlayers() {
		List<Player> players = new ArrayList<>();
		players.add(new Player(1l, "João", 2l));
		players.add(new Player(2l, "Maria", 3l));
		players.add(new Player(3l, "Fulano", 0l));
		players.add(new Player(4l, "Sicrano", 0l));
		return players;
	}
}
