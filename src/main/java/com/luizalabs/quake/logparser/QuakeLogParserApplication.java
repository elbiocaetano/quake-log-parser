package com.luizalabs.quake.logparser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.luizalabs.quake.logparser.model.Game;
import com.luizalabs.quake.logparser.parser.LogParser;
import com.luizalabs.quake.logparser.service.GameService;

@SpringBootApplication
public class QuakeLogParserApplication {

	private final String FILE_PATH = "static/quake-log.log";

	@Autowired
	private GameService gameService;

	@Autowired
	private LogParser logParser;

	public static void main(String[] args) {
		SpringApplication.run(QuakeLogParserApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void seedDatabaseAfterStartup() {
		try {
			List<Game> games = logParser.parse(FILE_PATH);
			games.forEach(game -> this.gameService.save(game));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
