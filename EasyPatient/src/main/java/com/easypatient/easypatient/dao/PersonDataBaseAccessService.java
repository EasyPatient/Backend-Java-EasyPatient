package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("PersonPostgres")
public class PersonDataBaseAccessService implements PersonDao {

    final String sqlSelectAllPeople = "SELECT id, name, age, bed_id, arrived_at, created_at, updated_at FROM person1";
    final String sqlSelectPersonByID = "SELECT id, name, age, bed_id, arrived_at, created_at, updated_at FROM person1 WHERE id = ?";
    final String sqlInsretPerson = "INSERT INTO person1 VALUES(?, ?, ?, ?, ?, ?, ?)";
    final String sqlDeletePerson = "DELETE FROM person1 WHERE id = ?";
    final String sqlUpdatePersonById = "UPDATE person1 SET name = ?, age = ?, bed_id = ?, arrived_at = ?, created_at = ?, updated_at = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertPerson(UUID id, Person person) {
        String name = person.getName();
        int age = person.getAge();
        UUID bedId = person.getBedId();
        LocalDateTime arrivedAt = person.getArrivedAt();
        LocalDateTime createdAt = person.getCreatedAt();
        LocalDateTime updatedAt = person.getUpdatedAt();
        jdbcTemplate.update(sqlInsretPerson, id, name, age, bedId, arrivedAt, createdAt, updatedAt);
    }

    @Override
    public List<Person> selectAllPeople() {
        return jdbcTemplate.query(sqlSelectAllPeople,
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    UUID bedId = UUID.fromString((resultSet.getString("bed_id")));
                    LocalDateTime arrivedAt = resultSet.getTimestamp("arrived_at").toLocalDateTime();
                    LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                    LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
                    return Person.builder()
                            .id(id)
                            .name(name)
                            .age(age)
                            .bedId(bedId)
                            .arrivedAt(arrivedAt)
                            .createdAt(createdAt)
                            .updatedAt(updatedAt)
                            .build();
                });
    }

    @Override
    public void deletePersonById(UUID id) {
        Object[] args = new Object[]{id};
        jdbcTemplate.update(sqlDeletePerson, args);
    }

    @Override
    public void updatePersonById(UUID id, Person person) {
        String name = person.getName();
        int age = person.getAge();
        UUID bedId = person.getBedId();
        LocalDateTime arrivedAt = person.getArrivedAt();
        LocalDateTime createdAt = person.getCreatedAt();
        LocalDateTime updatedAt = person.getUpdatedAt();

        jdbcTemplate.update(sqlUpdatePersonById, name, age, bedId, arrivedAt, createdAt, updatedAt, id);
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        Person person = jdbcTemplate.queryForObject(
                sqlSelectPersonByID,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    UUID bedId = UUID.fromString((resultSet.getString("bed_id")));
                    LocalDateTime arrivedAt = resultSet.getTimestamp("arrived_at").toLocalDateTime();
                    LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                    LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
                    return Person.builder()
                            .id(personId)
                            .name(name)
                            .age(age)
                            .bedId(bedId)
                            .arrivedAt(arrivedAt)
                            .createdAt(createdAt)
                            .updatedAt(updatedAt)
                            .build();
                });
        return Optional.ofNullable(person);
    }

}
