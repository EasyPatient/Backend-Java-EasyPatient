package com.example.EasyPatient.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.EasyPatient.model.Person;

@Repository("personDao")
public class PersonDataAccessService implements PersonDao {

	private static List<Person> DB = new ArrayList<>();
	
	@Override
	public int insertPerson(UUID id, Person person) {
		DB.add(new Person(id, person.getName(), person.getAge(), person.getBedId(), 
				person.getArrivedAt(), person.getCreatedAt()));
		return 1;
	}

}
