package com.example.EasyPatient.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.EasyPatient.model.Person;

@Repository("personDao")
public class PersonDataAccessService implements PersonDao {

	private static List<Person> DB = new ArrayList<>();
	
	@Override
	public int insertPerson(UUID id, Person person) {
		DB.add(new Person(id, person.getName(), person.getAge(), person.getBedId(), 
				person.getArrivedAt()));
		return 1;
	}

	@Override
	public List<Person> selectAllPeople() {
		return DB;
	}

	@Override
	public int deletePersonById(UUID id) {
		Optional<Person> personToDelete = selectPersonById(id);
		if (personToDelete.isEmpty()) {
			return 0;
		}
		DB.remove(personToDelete.get());
		return 1;
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		return selectPersonById(id)
				.map(p -> {
					int indexOfPersonToUpdate = DB.indexOf(p);
					if(indexOfPersonToUpdate >= 0) {
						DB.set(indexOfPersonToUpdate, new Person(id, 
								person.getName(), 
								person.getAge(), 
								person.getBedId(), 
								person.getArrivedAt()));
						return 1;
					}
					return 0;
				})
				.orElse(0);
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		return DB.stream()
				.filter(person -> person.getId().equals(id))
				.findFirst();
	}


}
