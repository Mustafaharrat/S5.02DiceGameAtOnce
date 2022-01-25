package com.DiceGame.springbootmongodbmysql.model;


import java.util.Date;



public class DiceThrowModel {
	
	private int throwNumber;
	private int dice1;
	private int dice2;
	private int throwValue;
	private Date date_throw;
	private int playerid;
	
	public DiceThrowModel() {}

	public int getThrowNumber() {
		return throwNumber;
	}

	public void setThrowNumber(int throwNumber) {
		this.throwNumber = throwNumber;
	}

	public int getDice1() {
		return dice1;
	}

	public void setDice1(int dice1) {
		this.dice1 = dice1;
	}

	public int getDice2() {
		return dice2;
	}

	public void setDice2(int dice2) {
		this.dice2 = dice2;
	}

	public int getThrowValue() {
		return throwValue;
	}

	public void setThrowValue(int throwValue) {
		this.throwValue = throwValue;
	}

	public Date getDate_throw() {
		return date_throw;
	}

	public void setDate_throw(Date date_throw) {
		this.date_throw = date_throw;
	}
	
	public int getPlayerid() {
		return playerid;
	}

	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}
	

	@Override
	public String toString() {
		return "DiceThrowModel [throwNumber=" + throwNumber + ", dice1=" + dice1 + ", dice2=" + dice2 + ", throwValue="
				+ throwValue + ", date_throw=" + date_throw + "]";
	}
	
	
	
	

}
