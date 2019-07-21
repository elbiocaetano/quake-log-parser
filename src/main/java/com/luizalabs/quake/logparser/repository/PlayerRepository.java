package com.luizalabs.quake.logparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizalabs.quake.logparser.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
