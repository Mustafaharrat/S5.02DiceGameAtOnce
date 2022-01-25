package com.DiceGame.springbootmongodbmysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DiceGame.springbootmongodbmysql.document.DiceThrow;
import com.DiceGame.springbootmongodbmysql.entity.Player;
import com.DiceGame.springbootmongodbmysql.model.PlayerModel;
import com.DiceGame.springbootmongodbmysql.service.PlayerService;

@RestController
public class GameController {
	
	@Autowired
	PlayerService playerService;
	
	@RequestMapping(value="player", method=RequestMethod.POST)
	public String createPlayer(@RequestBody PlayerModel playerModel) {
		
		return playerService.createPlayer(playerModel);
	}
	
	@RequestMapping(value="readplayers",method=RequestMethod.GET)
	public List<Player> readPlayers(){
		
		return playerService.readPlayers();
	}
	@RequestMapping(value="diceThrow", method=RequestMethod.POST)
	public String diceThrow(@RequestBody PlayerModel playerModel) {
		
		return playerService.createDiceThrow(playerModel);
	}
	@RequestMapping(value="updateName/{playerName}/{newName}", method=RequestMethod.PUT)
	public String updateName(@PathVariable("playerName") String playerName,@PathVariable("newName") String newName){
		
		return playerService.updatePlayer(playerName,newName);
	}
	@RequestMapping(value="throwsList/{playerid}", method=RequestMethod.GET)
	public List<DiceThrow> throwsList(@PathVariable("playerid") int playerid){
		
		return playerService.throwsList(playerid);
	}
	@RequestMapping(value="ranking", method=RequestMethod.GET)
	public List<String> ranking(){
		
		return playerService.ranking();
	}
	@RequestMapping(value="winner", method=RequestMethod.GET)
	public String winner() {
		
		return playerService.winner();
	}
	@RequestMapping(value="loser", method=RequestMethod.GET)
	public String loser() {
		
		return playerService.loser();
	}
	@RequestMapping(value="deleteThrows/{playerid}", method=RequestMethod.DELETE)
	public String deleteThrows(@PathVariable("playerid") int playerid) {
		
		return playerService.deleteThrows(playerid);
	}

}
