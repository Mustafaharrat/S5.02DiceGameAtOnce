package com.DiceGame.springbootmongodbmysql.model;


import java.util.Date;

public class PlayerModel {
	
	private String name;
	private double percent;
	private Date date_at;
	
	public PlayerModel() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public Date getDate_at() {
		return date_at;
	}

	public void setDate_at(Date date_at) {
		this.date_at = date_at;
	}

	@Override
	public String toString() {
		return "PlayerModel [name=" + name + ", percent=" + percent + ", date_at=" + date_at + "]";
	}
	
	
}
