package com.DiceGame.springbootmongodbmysql.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Player {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)//con IDENTITY da error
	private int id;
	private String name ;
	private double percent;
	private Date date_at;
	
	public Player(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		return "Player [id=" + id + ", name=" + name + ", percent=" + percent + ", date_at=" + date_at + "]";
	}
	
	
	
	
	
	

}
