package com.DiceGame.springbootmongodbmysql.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DiceGame.springbootmongodbmysql.entity.Player;

@Repository 
public interface PlayerRepository extends JpaRepository<Player,String>{

	public Boolean existsByName(String name);
	public List<Player> findByName(String name);
	public void  deleteByName(String name);
	
}
