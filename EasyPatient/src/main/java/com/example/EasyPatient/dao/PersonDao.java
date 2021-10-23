package com.example.EasyPatient.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.EasyPatient.model.Person;

public interface PersonDao {

	int insertPerson(UUID id, Person person);
	
	List<Person> selectAllPeople();
	
	int deletePersonById(UUID id);
	
	int updatePersonById(UUID id, Person person);
	
	Optional<Person> selectPersonById(UUID id);
	
}
