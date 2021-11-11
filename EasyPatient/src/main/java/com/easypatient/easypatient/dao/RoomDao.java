package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomDao {
    void insertRoom(UUID id, Room room);

    List<Room> selectAllRooms();

    void deleteRoomById(UUID id);

    void updateRoomById(UUID id, Room room);

    Optional<Room> selectRoomById(UUID id);
}
