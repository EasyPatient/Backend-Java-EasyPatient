package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    void insertPerson(UUID id, Person person);

    List<Person> selectAllPeople();

    void deletePersonById(UUID id);

    void updatePersonById(UUID id, Person person);

    Optional<Person> selectPersonById(UUID id);
}
