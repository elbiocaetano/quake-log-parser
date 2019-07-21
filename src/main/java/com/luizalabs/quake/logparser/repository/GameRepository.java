package com.luizalabs.quake.logparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizalabs.quake.logparser.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
