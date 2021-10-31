package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.PersonDao;
import com.easypatient.easypatient.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("PersonPostgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public void addPerson(Person person) {
        personDao.insertPerson(person.getId(), person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public void deletePerson(UUID id) {
        personDao.deletePersonById(id);
    }

    public void updatePerson(UUID id, Person person) {
        personDao.updatePersonById(id, person);
    }
}
