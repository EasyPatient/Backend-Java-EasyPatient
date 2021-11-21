package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.RoomDTO;
import com.easypatient.easypatient.dto.RoomGetDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomDao {
    void insertRoom(RoomDTO room);

    List<RoomGetDTO> selectAllRooms();

    void deleteRoomById(UUID id);

    void updateRoomById(UUID id,
                        Optional<Integer> number,
                        Optional<String> name) throws SQLException;

    Optional<RoomGetDTO> selectRoomById(UUID id);

    List<RoomGetDTO> selectRoomByVariables(Optional<Integer> number,
                                           Optional<String> name,
                                           Optional<LocalDateTime> createdAt,
                                           Optional<LocalDateTime> updatedAt) throws SQLException;
}
