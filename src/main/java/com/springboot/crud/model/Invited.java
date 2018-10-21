package com.springboot.crud.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotEmpty;

@Entity
public class Invited {

	@Id
	@NotEmpty(message = "National ID can't be empty !")
	private String nationalID;
	@NotEmpty(message = "Name can't be empty !")
	private String name;
	
	@ManyToOne
	private Event event; 
	
	
	public String getNationalID() {
		return nationalID;
	}
	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
}
