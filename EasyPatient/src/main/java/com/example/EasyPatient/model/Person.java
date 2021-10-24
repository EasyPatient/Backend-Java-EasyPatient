package com.example.EasyPatient.model;

import java.sql.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	
	private final UUID id;
	
	@NotBlank
	private final String name;
	
	private int age;
	private UUID bedId;
	private final Date arrivedAt;
	private final Date createdAt;
	private Date updatedAt;
	
	public Person(@JsonProperty("id") UUID id, 
			@JsonProperty("name") String name, 
			@JsonProperty("age") int age, 
			@JsonProperty("bedId") UUID bedId, 
			@JsonProperty("arrivedAt") Date arrivedAt, 
			@JsonProperty("createdAt") Date createdAt) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.bedId = bedId;
		this.arrivedAt = arrivedAt;
		this.createdAt = createdAt;
		this.updatedAt = createdAt;
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public UUID getBedId() {
		return this.bedId;
	}
	
	public Date getArrivedAt() {
		return this.arrivedAt;
	}
	
	public Date getCreatedAt() {
		return this.createdAt;
	}
	
	public Date getUpdatedAt() {
		return this.updatedAt;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setBedId(UUID bedId) {
		this.bedId = bedId;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
