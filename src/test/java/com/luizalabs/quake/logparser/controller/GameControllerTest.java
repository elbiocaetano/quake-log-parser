package com.luizalabs.quake.logparser.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void findTenFirstGamesTest() throws Exception {
		this.mockMvc.perform(get("/v1/games")).andExpect(status().isOk())
				.andExpect(jsonPath("$.meta.limit", equalTo(10))).andExpect(jsonPath("$.meta.offset", equalTo(0)))
				.andExpect(jsonPath("$.meta.recordCount", equalTo(10)))
				.andExpect(jsonPath("$.meta.totalRecords", equalTo(21)))
				.andExpect(jsonPath("$.records[0].id", equalTo(1)))
				.andExpect(jsonPath("$.records[0].players").isArray())
				.andExpect(jsonPath("$.records[0].players", hasSize(0)))
				.andExpect(jsonPath("$.records[1].id", equalTo(2)))
				.andExpect(jsonPath("$.records[1].players").isArray())
				.andExpect(jsonPath("$.records[1].players", hasSize(2)))
				.andExpect(jsonPath("$.records[1].players[0]", equalTo("Isgalamido")))
				.andExpect(jsonPath("$.records[1].players[1]", equalTo("Mocinha")))
				.andExpect(jsonPath("$.records[1].kills.Mocinha", equalTo(0)))
				.andExpect(jsonPath("$.records[1].kills.Isgalamido", equalTo(0)))
				.andExpect(jsonPath("$.records[2].id", equalTo(3)))
				.andExpect(jsonPath("$.records[2].players").isArray())
				.andExpect(jsonPath("$.records[2].players", hasSize(4)))
				.andExpect(jsonPath("$.records[2].players[0]", equalTo("Mocinha")))
				.andExpect(jsonPath("$.records[2].players[1]", equalTo("Isgalamido")))
				.andExpect(jsonPath("$.records[2].players[2]", equalTo("Zeh")))
				.andExpect(jsonPath("$.records[2].players[3]", equalTo("Dono da Bola")))
				.andExpect(jsonPath("$.records[2].kills.Mocinha", equalTo(0)))
				.andExpect(jsonPath("$.records[2].kills.Isgalamido", equalTo(1)))
				.andExpect(jsonPath("$.records[2].kills.Zeh", equalTo(0)))
				.andExpect(jsonPath("$.records[2].kills.['Dono da Bola']", equalTo(0)));
	}

	@Test
	public void findTenSecondGamesTest() throws Exception {
		this.mockMvc.perform(get("/v1/games").param("offset", "1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.meta.limit", equalTo(10))).andExpect(jsonPath("$.meta.offset", equalTo(1)))
				.andExpect(jsonPath("$.meta.recordCount", equalTo(10)))
				.andExpect(jsonPath("$.meta.totalRecords", equalTo(21)))
				.andExpect(jsonPath("$.records[0].id", equalTo(11)))
				.andExpect(jsonPath("$.records[0].players").isArray())
				.andExpect(jsonPath("$.records[0].players", hasSize(7)))
				.andExpect(jsonPath("$.records[0].players[0]", equalTo("Dono da Bola")))
				.andExpect(jsonPath("$.records[0].players[1]", equalTo("Isgalamido")))
				.andExpect(jsonPath("$.records[0].players[2]", equalTo("Assasinu Credi")))
				.andExpect(jsonPath("$.records[0].players[3]", equalTo("Oootsimo")))
				.andExpect(jsonPath("$.records[0].players[4]", equalTo("Chessus")))
				.andExpect(jsonPath("$.records[0].players[5]", equalTo("Zeh")))
				.andExpect(jsonPath("$.records[0].players[6]", equalTo("Mal")))
				.andExpect(jsonPath("$.records[0].kills.['Dono da Bola']", equalTo(1)))
				.andExpect(jsonPath("$.records[0].kills.Chessus", equalTo(0)))
				.andExpect(jsonPath("$.records[0].kills.['Assasinu Credi']", equalTo(0)))
				.andExpect(jsonPath("$.records[0].kills.Mal", equalTo(0)))
				.andExpect(jsonPath("$.records[0].kills.Isgalamido", equalTo(6)))
				.andExpect(jsonPath("$.records[0].kills.Zeh", equalTo(0)))
				.andExpect(jsonPath("$.records[0].kills.Oootsimo", equalTo(4)))
				.andExpect(jsonPath("$.records[1].id", equalTo(12)))
				.andExpect(jsonPath("$.records[1].players").isArray())
				.andExpect(jsonPath("$.records[1].players", hasSize(7)))
				.andExpect(jsonPath("$.records[1].players[0]", equalTo("Dono da Bola")))
				.andExpect(jsonPath("$.records[1].players[1]", equalTo("Chessus")))
				.andExpect(jsonPath("$.records[1].players[2]", equalTo("Assasinu Credi")))
				.andExpect(jsonPath("$.records[1].players[3]", equalTo("Mal")))
				.andExpect(jsonPath("$.records[1].players[4]", equalTo("Zeh")))
				.andExpect(jsonPath("$.records[1].players[5]", equalTo("Oootsimo")))
				.andExpect(jsonPath("$.records[1].players[6]", equalTo("Isgalamido")))
				.andExpect(jsonPath("$.records[1].kills.['Dono da Bola']", equalTo(7)))
				.andExpect(jsonPath("$.records[1].kills.Chessus", equalTo(13)))
				.andExpect(jsonPath("$.records[1].kills.['Assasinu Credi']", equalTo(18)))
				.andExpect(jsonPath("$.records[1].kills.Mal", equalTo(0)))
				.andExpect(jsonPath("$.records[1].kills.Isgalamido", equalTo(24)))
				.andExpect(jsonPath("$.records[1].kills.Zeh", equalTo(11)))
				.andExpect(jsonPath("$.records[1].kills.Oootsimo", equalTo(12)))
				.andExpect(jsonPath("$.records[2].id", equalTo(13)))
				.andExpect(jsonPath("$.records[2].players").isArray())
				.andExpect(jsonPath("$.records[2].players", hasSize(5)))
				.andExpect(jsonPath("$.records[2].players[0]", equalTo("Isgalamido")))
				.andExpect(jsonPath("$.records[2].players[1]", equalTo("Assasinu Credi")))
				.andExpect(jsonPath("$.records[2].players[2]", equalTo("Oootsimo")))
				.andExpect(jsonPath("$.records[2].players[3]", equalTo("Dono da Bola")))
				.andExpect(jsonPath("$.records[2].players[4]", equalTo("Zeh")))
				.andExpect(jsonPath("$.records[2].kills.['Dono da Bola']", equalTo(0)))
				.andExpect(jsonPath("$.records[2].kills.['Assasinu Credi']", equalTo(0)))
				.andExpect(jsonPath("$.records[2].kills.Isgalamido", equalTo(0)))
				.andExpect(jsonPath("$.records[2].kills.Zeh", equalTo(2)))
				.andExpect(jsonPath("$.records[2].kills.Oootsimo", equalTo(1)));
	}

	@Test
	public void findGamesOffsetNotFoundTest() throws Exception {
		this.mockMvc.perform(get("/v1/games").param("offset", "200")).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.developerMessage", equalTo("Game  not found")))
				.andExpect(jsonPath("$.userMessage", equalTo("Tried to find a game, but did not find any")))
				.andExpect(jsonPath("$.errorCode", equalTo(404)));
	}

	@Test
	public void findByIdSuccessTest() throws Exception {
		this.mockMvc.perform(get("/v1/games/11")).andExpect(status().isOk())
				.andExpect(jsonPath("$.meta.limit", equalTo(1))).andExpect(jsonPath("$.meta.offset", equalTo(0)))
				.andExpect(jsonPath("$.meta.recordCount", equalTo(1)))
				.andExpect(jsonPath("$.meta.totalRecords", equalTo(1)))
				.andExpect(jsonPath("$.records[0].id", equalTo(11)))
				.andExpect(jsonPath("$.records[0].players").isArray())
				.andExpect(jsonPath("$.records[0].players", hasSize(7)))
				.andExpect(jsonPath("$.records[0].players[0]", equalTo("Dono da Bola")))
				.andExpect(jsonPath("$.records[0].players[1]", equalTo("Isgalamido")))
				.andExpect(jsonPath("$.records[0].players[2]", equalTo("Assasinu Credi")))
				.andExpect(jsonPath("$.records[0].players[3]", equalTo("Oootsimo")))
				.andExpect(jsonPath("$.records[0].players[4]", equalTo("Chessus")))
				.andExpect(jsonPath("$.records[0].players[5]", equalTo("Zeh")))
				.andExpect(jsonPath("$.records[0].players[6]", equalTo("Mal")))
				.andExpect(jsonPath("$.records[0].kills.['Dono da Bola']", equalTo(1)))
				.andExpect(jsonPath("$.records[0].kills.Chessus", equalTo(0)))
				.andExpect(jsonPath("$.records[0].kills.['Assasinu Credi']", equalTo(0)))
				.andExpect(jsonPath("$.records[0].kills.Mal", equalTo(0)))
				.andExpect(jsonPath("$.records[0].kills.Isgalamido", equalTo(6)))
				.andExpect(jsonPath("$.records[0].kills.Zeh", equalTo(0)))
				.andExpect(jsonPath("$.records[0].kills.Oootsimo", equalTo(4)));
	}

	@Test
	public void findByIdNotFoundTest() throws Exception {
		this.mockMvc.perform(get("/v1/games/9999")).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.developerMessage", equalTo("Game 9999 not found")))
				.andExpect(jsonPath("$.userMessage", equalTo("Tried to find a game, but did not find any")))
				.andExpect(jsonPath("$.errorCode", equalTo(404)));
	}

	@Test
	public void findByIdInvalidNumberTest() throws Exception {
		this.mockMvc.perform(get("/v1/games/a")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.developerMessage",
						equalTo("Invalid value 'a' for parameter id. It must be a number")))
				.andExpect(
						jsonPath("$.userMessage", equalTo("Invalid value 'a' for parameter id. It must be a number")))
				.andExpect(jsonPath("$.errorCode", equalTo(400)));
	}

	@Test
	public void findByIdNumberLessThan1Test() throws Exception {
		this.mockMvc.perform(get("/v1/games/0")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.developerMessage", equalTo("findById.id: id must be greater than or equal 1")))
				.andExpect(jsonPath("$.userMessage", equalTo("findById.id: id must be greater than or equal 1")))
				.andExpect(jsonPath("$.errorCode", equalTo(400)));
	}
}
