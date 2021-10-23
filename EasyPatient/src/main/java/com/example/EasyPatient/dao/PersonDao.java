package com.example.EasyPatient.dao;

import java.util.UUID;

import com.example.EasyPatient.model.Person;

public interface PersonDao {

	int insertPerson(UUID id, Person person);
	
}
