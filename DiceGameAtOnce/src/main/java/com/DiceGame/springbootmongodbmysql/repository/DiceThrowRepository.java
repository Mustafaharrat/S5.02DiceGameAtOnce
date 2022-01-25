package com.DiceGame.springbootmongodbmysql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.DiceGame.springbootmongodbmysql.document.DiceThrow;

@Repository
public interface DiceThrowRepository extends MongoRepository<DiceThrow,String>{
	
	public List<DiceThrow> findThrowsByPlayerid(int playerid);
	public void deleteAllByPlayerid(int playerid);

	}
