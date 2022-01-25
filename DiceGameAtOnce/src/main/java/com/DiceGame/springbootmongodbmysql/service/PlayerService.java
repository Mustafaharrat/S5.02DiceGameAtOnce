package com.DiceGame.springbootmongodbmysql.service;


import java.util.ArrayList;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.DiceGame.springbootmongodbmysql.document.DiceThrow;
import com.DiceGame.springbootmongodbmysql.document.Dice;
import com.DiceGame.springbootmongodbmysql.entity.Player;
import com.DiceGame.springbootmongodbmysql.model.DiceThrowModel;
import com.DiceGame.springbootmongodbmysql.model.PlayerModel;
import com.DiceGame.springbootmongodbmysql.repository.DiceThrowRepository;
import com.DiceGame.springbootmongodbmysql.repository.PlayerRepository;

@Service
public class PlayerService{
	
	@Autowired
	DiceThrowRepository diceThrowRepository;
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Transactional
	public String createPlayer(PlayerModel playerModel) {
		if(playerRepository.findByName(playerModel.getName()).isEmpty() || playerModel.getName()==null) {
			
			if (playerModel.getName()==null) {
			playerModel.setName("Anonymous");
			}
			
			playerModel.setDate_at(new Date());
			Player player=new Player();
			BeanUtils.copyProperties(playerModel,player);
			
		playerRepository.save(player);
		return "Created";
	}
		return "This name is in use";
	}
	
	public List<Player> readPlayers(){
		
		List<Player> playerList=new ArrayList<>();
		playerList=playerRepository.findAll();
		return playerList;
	}
	
	@Transactional
	public String createDiceThrow(PlayerModel playerModel) {
		
		if (playerRepository.existsByName(playerModel.getName())) {
			
			Player player=playerRepository.findByName(playerModel.getName()).get(0);
			//pasamos el player a playerModel para poder trabajar
			BeanUtils.copyProperties(player,playerModel);
			
			DiceThrowModel diceThrowModel= new DiceThrowModel();

			Dice dice1=new Dice(1,"dice1",(int)(Math.random()*(7-1))+1);
			Dice dice2=new Dice(2,"dice2",(int)(Math.random()*(7-1))+1);
			
			diceThrowModel.setDice1(dice1.getValueDice());
			diceThrowModel.setDice2(dice2.getValueDice());
			diceThrowModel.setPlayerid(player.getId());
			diceThrowModel.setDate_throw(new Date());
			diceThrowModel.setThrowValue(dice1.getValueDice()+dice2.getValueDice());
			
			//diceThrowModel. en  los Models sus valores estan vacios.
			//Por lo tanto necesitas saber que tirada es la última, antes de asignarle un valor a: diceThrowModel.setThrowNumber();
			int throwNumber=diceThrowRepository.findThrowsByPlayerid(player.getId()).size()+1;
			diceThrowModel.setThrowNumber(throwNumber);
			
			DiceThrow diceThrow=new DiceThrow();
			BeanUtils.copyProperties(diceThrowModel,diceThrow);
			diceThrowRepository.save(diceThrow);
			
			BeanUtils.copyProperties(playerModel,player);//devolvemos el Model al player
			playerRepository.save(player);
			
			
			List<DiceThrow>throwList=new ArrayList<DiceThrow>();
			throwList=diceThrowRepository.findThrowsByPlayerid(player.getId());
			
			double win=0;
			double attempts=throwList.size();

				for (DiceThrow e :throwList) {
						if (e.getThrowValue()==7) {
							win++;
						}
					}

			double porcentage= (win/attempts)*100;
			player.setPercent(porcentage);
		
			
			if (diceThrow.getThrowValue()==7) {
				
				
				return "YOU WINN!!!! \nResult of the throw number "+diceThrow.getThrowNumber()+": "+
						diceThrow.getThrowValue()+"\nDice 1 result: "+dice1.getValueDice()+
									 " \nDice 2 result: "+dice2.getValueDice()+"\nPercent of victories: "+ player.getPercent();
			}else {
				return "YOU LOUS!!!! \nResult of the throw number "+diceThrow.getThrowNumber()+": "+
						diceThrow.getThrowValue()+"\nDice 1 result: "+dice1.getValueDice()+
									" \nDice 2 result: "+dice2.getValueDice()+"\nPercent of victories: "+ player.getPercent();
			}
		}
	return "Player don't exists";
	}
	@Transactional
	public String updatePlayer(String playerName,String newName) {
		
		if (playerRepository.existsByName(playerName) & !playerRepository.existsByName(newName)) {
			
			Player player=playerRepository.findByName(playerName).get(0);
			player.setName(newName);
			playerRepository.save(player);
			return "New Name incorporated: " +player.getName();
		}
			return "Player don't exists or new name is in use";
	}
	
	public List<DiceThrow> throwsList(int playerid){
		
		List <DiceThrow> diceThrowList=diceThrowRepository.findThrowsByPlayerid(playerid);
		
		return diceThrowList;
	}
	public List<String> ranking(){
		
		List<String>lista=playerRepository.findAll().stream()
				.flatMap(s-> Stream.of("Player: "+s.getName()+" Winning percentage:"+s.getPercent()+"\n")) 
				.collect(Collectors.toList());
		
		return lista;
	}
	public String loser(){
			
		Player sortedList = playerRepository.findAll().stream()
							.min(Comparator.comparingDouble(s-> s.getPercent())).get();
		return "Worst percentage: "+sortedList.getPercent()+"\nPlayer: "+sortedList.getName();
	}
	public String winner(){
		
		Player winner = playerRepository.findAll().stream()
				.max(Comparator.comparingDouble(s-> s.getPercent())).get();
	return "Best percentage: "+winner.getPercent()+"\nPlayer: "+winner.getName();
	}
	
	public String deleteThrows(int playerid) {
		
		diceThrowRepository.deleteAllByPlayerid(playerid);
		
		return "All diceThrows remove of player id: "+playerid;
	}
	
}
