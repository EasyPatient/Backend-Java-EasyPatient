package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.PatientDTO;
import com.easypatient.easypatient.dto.PatientGetDTO;
import com.easypatient.easypatient.dto.RoomDTO;
import com.easypatient.easypatient.dto.RoomGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("RoomPostgres")
public class RoomDataBaseAccessService implements RoomDao {

    final String sqlSelectAllRooms = "SELECT id, number, name, created_at, updated_at FROM room";
    final String sqlSelectRoomByID = "SELECT id, number, name, created_at, updated_at FROM room WHERE id = ?";
    final String sqlInsertRoom = "INSERT INTO room VALUES(?, ?, ?, ?)";
    final String sqlSelectRoomByVariable = "SELECT id, number, name, created_at, updated_at FROM room WHERE";
    final String sqlNumber = "(number = ?)";
    final String sqlName = "(name = ?)";
    final String sqlCreatedAfter = "(created_at >= ?)";
    final String sqlUpdatedAfter = "(updated_at >= ?)";
    final String sqlAnd = "AND";
    final String sqlSemicolon = ";";
    final String sqlDeleteRoom = "DELETE FROM room WHERE id = ?";
    final String sqlUpdateRoomById = "UPDATE room SET number = ?, name = ?, updated_at = ? WHERE id = ?";


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static RoomGetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        int number = resultSet.getInt("number");
        String name = resultSet.getString("name");
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return RoomGetDTO.builder()
                .id(id)
                .number(number)
                .name(name)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @Override
    public void insertRoom(RoomDTO room) {
        LocalDateTime date = LocalDateTime.now();

        jdbcTemplate.update(sqlInsertRoom,
                room.getNumber(),
                room.getName(),
                date,
                date);
    }

    @Override
    public List<RoomGetDTO> selectAllRooms() {
        return jdbcTemplate.query(sqlSelectAllRooms,
                RoomDataBaseAccessService::mapRow);

    }

    @Override
    public void deleteRoomById(UUID id) {
        Object[] args = new Object[]{id};
        jdbcTemplate.update(sqlDeleteRoom, args);
    }

    @Override
    public void updateRoomById(UUID id,
                               Optional<Integer> number,
                               Optional<String> name) throws SQLException {
        Optional<RoomGetDTO> existingRoomOptional = selectRoomById(id);
        RoomGetDTO existingRoom;
        int numberToUpdate;
        String nameToUpdate;
        LocalDateTime updatedAtToUpdate;

        if(existingRoomOptional.isPresent()) {
            existingRoom = existingRoomOptional.get();
        } else {
            throw new SQLException("Room with ID: " + id + "doesn't exist.");
        }

        numberToUpdate = number.orElseGet(existingRoom::getNumber);
        nameToUpdate = name.orElseGet(existingRoom::getName);
        updatedAtToUpdate = LocalDateTime.now();

        jdbcTemplate.update(sqlUpdateRoomById,
                numberToUpdate,
                nameToUpdate,
                updatedAtToUpdate,
                id);
    }

    @Override
    public Optional<RoomGetDTO> selectRoomById(UUID id) {
        RoomGetDTO room = jdbcTemplate.queryForObject(
                sqlSelectRoomByID,
                new Object[]{id},
                RoomDataBaseAccessService::mapRow);
        return Optional.ofNullable(room);
    }

    @Override
    public List<RoomGetDTO> selectRoomByVariables(Optional<Integer> number,
                                                  Optional<String> name,
                                                  Optional<LocalDateTime> createdAfter,
                                                  Optional<LocalDateTime> updatedAfter) throws SQLException {
        int i = 0;
        int k = 0;

        List<String> expressions = new java.util.ArrayList<>(List.of(sqlSelectRoomByVariable));

        if(number.isPresent()) {
            i++;
            expressions.add(sqlNumber);
        }
        if(name.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlName);
        }
        if(createdAfter.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlCreatedAfter);
        }
        if(updatedAfter.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlUpdatedAfter);
        }

        String sqlExpression = String.join(" ", expressions);
        sqlExpression = sqlExpression.concat(sqlSemicolon);

        if(i != 0) {
            Object[] jdbcTable = new Object[i];
            if(number.isPresent()) {
                jdbcTable[k] = number.get();
                k++;
            }
            if(name.isPresent()) {
                jdbcTable[k] = name.get();
                k++;
            }
            if(createdAfter.isPresent()) {
                jdbcTable[k] = createdAfter.get();
                k++;
            }
            if(updatedAfter.isPresent()) {
                jdbcTable[k] = updatedAfter.get();
                k++;
            }
            return jdbcTemplate.query(
                    sqlExpression,
                    jdbcTable,
                    RoomDataBaseAccessService::mapRow);
        } else {
            throw new SQLException("can not query without any parameters!");
        }
    }
}
