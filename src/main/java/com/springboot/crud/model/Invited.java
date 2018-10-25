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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nationalID == null) ? 0 : nationalID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invited other = (Invited) obj;
		if (nationalID == null) {
			if (other.nationalID != null)
				return false;
		} else if (!nationalID.equals(other.nationalID))
			return false;
		return true;
	}
	
	
}
