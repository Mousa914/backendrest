package com.restaurants.restaurants.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	private String type;
	private String phone;
	private String location;
	
	public Restaurant (){
		
	}
	
	public Restaurant(long id, String name, String type, String phone, String location) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.phone = phone;
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "restaurant "
				+ "[name=" + name + 
				", type=" + type + 
				", phone=" + phone + 
				", location=" + location + 
				"]";
	}
	
	

}
