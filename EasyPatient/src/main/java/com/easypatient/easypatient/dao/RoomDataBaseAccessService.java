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
    final String sqlInsertRoom = "INSERT INTO room VALUES(?, ?, ?, ?)";


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
        return Optional.ofNullable(RoomGetDTO.builder().build());
    }

    @Override
    public List<RoomGetDTO> selectRoomByVariables(Optional<Integer> number,
                                                  Optional<String> name,
                                                  Optional<LocalDateTime> createdAt,
                                                  Optional<LocalDateTime> updatedAt) throws SQLException {
        return List.of();
    }
}
