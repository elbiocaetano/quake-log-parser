package com.luizalabs.quake.logparser.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.luizalabs.quake.logparser.model.Game;
import com.luizalabs.quake.logparser.model.Player;

public class LogParser {

	private final String KILLER = "killer";
	private final String KILLED = "killed";
	private final String WORLD = "<world>";
	private final String INIT_GAME = ".*InitGame:.*";
	private final String END_GAME = ".*ShutdownGame:.*";
	private final String KILL = ".*\\skilled\\s.*";
	private final Pattern killPattern = Pattern.compile("([^:]+)killed(.*?)\\sby");

	private Game game;
	private List<Game> games;

	public List<Game> parse(String filePath) throws IOException, URISyntaxException {
		games = new ArrayList<Game>();
		
		Stream<String> lines = this.readFile(filePath);
		lines.forEach(this::parseLine);
		lines.close();
		return games;
	}
	
	private Stream<String> readFile(String filePath) throws IOException, URISyntaxException {
		URL url = ClassLoader.getSystemResource(filePath);
		if(url != null) {
			return Files.lines(Paths.get(url.toURI()));
		}
		throw new FileNotFoundException(String.format("File %s not found", filePath));
	}

	private void parseLine(String line) {
		if (line.matches(INIT_GAME)) {
			this.newGame();
		} else if (line.matches(KILL)) {
			this.game.addKill();
			Map<String, String> killInfo = this.parsePlayerLine(line);

			String playerName = killInfo.get(KILLER);

			if (WORLD.equals(playerName)) {
				Player killed = this.getPlayer(killInfo.get(KILLED));
				if (killed.getKills() > 0) {
					killed.removeKill();
				}
			} else {
				Player killed = this.getPlayer(killInfo.get(KILLED));
				Player killer = this.getPlayer(killInfo.get(KILLER));

				if (killer.getName() != killed.getName()) {
					killer.addKill();
				}
			}

		} else if (line.matches(END_GAME)) {
			this.gameOver();
		}
	}

	private void newGame() {
		if (this.game != null) {
			gameOver();
		}
		this.game = new Game();
	}

	private void gameOver() {
		this.games.add(game);
		game = null;
	}

	private Map<String, String> parsePlayerLine(String line) {
		Map<String, String> killInfo = new HashMap<String, String>();
		Matcher matcher = killPattern.matcher(line);
		if (matcher.find()) {

			killInfo.put(KILLER, matcher.group(1).trim());
			killInfo.put(KILLED, matcher.group(2).trim());
		}
		;
		return killInfo;
	}

	private Player getPlayer(String name) {
		Optional<Player> currentPlayer = this.game.getPlayers().stream().filter(player -> player.getName().equals(name))
				.findFirst();
		if (currentPlayer.isEmpty()) {
			Player player = new Player(name, 0l);
			this.game.getPlayers().add(player);
			return player;
		}
		return currentPlayer.get();
	}
}
