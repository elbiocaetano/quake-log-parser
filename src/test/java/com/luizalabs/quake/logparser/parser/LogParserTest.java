package com.luizalabs.quake.logparser.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.luizalabs.quake.logparser.model.Game;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogParserTest {

	@Autowired
	private LogParser logParser;

	@Test
	public void parseSuccess() throws IOException, URISyntaxException {
		List<Game> games = logParser.parse("quake-log.log");
		System.out.println(games);
		assertTrue(games.size() == 3);
		assertTrue(games.get(0).getId() == null);
		assertTrue(games.get(0).getPlayers().isEmpty());
		assertTrue(games.get(0).getTotalKills() == 0l);

		assertTrue(games.get(1).getId() == null);
		assertTrue(!games.get(1).getPlayers().isEmpty());
		assertTrue(games.get(1).getPlayers().size() == 2);
		assertEquals(games.get(1).getPlayers().get(0).getName(), "Isgalamido");
		assertTrue(games.get(1).getPlayers().get(0).getKills() == 0l);
		assertEquals(games.get(1).getPlayers().get(1).getName(), "Mocinha");
		assertTrue(games.get(1).getPlayers().get(1).getKills() == 0l);

		assertTrue(games.get(2).getId() == null);
		assertTrue(!games.get(2).getPlayers().isEmpty());
		assertTrue(games.get(2).getPlayers().size() == 4);
		assertEquals(games.get(2).getPlayers().get(0).getName(), "Mocinha");
		assertTrue(games.get(2).getPlayers().get(0).getKills() == 0l);
		assertEquals(games.get(2).getPlayers().get(1).getName(), "Isgalamido");
		assertTrue(games.get(2).getPlayers().get(1).getKills() == 1l);
		assertEquals(games.get(2).getPlayers().get(2).getName(), "Zeh");
		assertTrue(games.get(2).getPlayers().get(2).getKills() == 0l);
		assertEquals(games.get(2).getPlayers().get(3).getName(), "Dono da Bola");
		assertTrue(games.get(2).getPlayers().get(3).getKills() == 0l);
	}

	@Test(expected = FileNotFoundException.class)
	public void parseFileNotFoundError() throws IOException, URISyntaxException {
		logParser.parse("quake-log-not-found.log");
	}

}
