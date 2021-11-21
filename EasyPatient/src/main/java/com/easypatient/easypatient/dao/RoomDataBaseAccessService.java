package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.PatientDTO;
import com.easypatient.easypatient.dto.RoomDTO;
import com.easypatient.easypatient.dto.RoomGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("RoomPostgres")
public class RoomDataBaseAccessService implements RoomDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    final String sqlInsertRoom = "INSERT INTO room VALUES(?, ?, ?, ?)";


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
        return List.of();
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
