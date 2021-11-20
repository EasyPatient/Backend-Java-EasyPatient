package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.RoomDTO;
import com.easypatient.easypatient.dto.RoomGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("RoomPostgres")
public class RoomDataBaseAccessService implements RoomDao {
    @Override
    public void insertRoom(RoomDTO room) {
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
