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
    final String sqlSelectRoomByVariable = "SELECT number, name, created_at, updated_at FROM room WHERE ";
    final String sqlNumber = " (number = ?)";
    final String sqlName = " (name = ?)";
    final String sqlCreatedAt = " (created_at = ?)";
    final String sqlUpdatedAt = " (updated_at = ?)";
    final String sqlAnd = " AND ";
    final String sqlSemicolon = ";";


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
    }

    @Override
    public void updateRoomById(UUID id,
                               Optional<Integer> number,
                               Optional<String> name) throws SQLException {
    }

    @Override
    public Optional<RoomGetDTO> selectRoomById(UUID id) {
        RoomGetDTO room = jdbcTemplate.queryForObject(
                sqlSelectRoomByID,
                new Object[]{id},
                RoomDataBaseAccessService::mapRow);
        return Optional.ofNullable(RoomGetDTO.builder().build());
    }

    @Override
    public List<RoomGetDTO> selectRoomByVariables(Optional<Integer> number,
                                                  Optional<String> name,
                                                  Optional<LocalDateTime> createdAt,
                                                  Optional<LocalDateTime> updatedAt) throws SQLException {
        int i = 0;
        int k = 0;

        List<String> expressions = new java.util.ArrayList<>(List.of(sqlSelectRoomByVariable));

        if(number.isPresent()) {
            i++;
            expressions.add(sqlNumber);
        }
        if(name.isPresent()) {
            i++;
            expressions.add(sqlName);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(createdAt.isPresent()) {
            i++;
            expressions.add(sqlCreatedAt);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(updatedAt.isPresent()) {
            i++;
            expressions.add(sqlUpdatedAt);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }

        expressions.add(sqlSemicolon);
        String sqlExpression = String.join(" ", expressions);

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
            if(createdAt.isPresent()) {
                jdbcTable[k] = createdAt.get();
                k++;
            }
            if(updatedAt.isPresent()) {
                jdbcTable[k] = updatedAt.get();
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
