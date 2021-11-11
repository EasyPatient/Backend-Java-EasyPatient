package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Room;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("RoomPostgres")
public class RoomDataBaseAccessService implements RoomDao {
    @Override
    public void insertRoom(UUID id, Room room) {
    }

    @Override
    public List<Room> selectAllRooms() {
        return List.of();
    }

    @Override
    public void deleteRoomById(UUID id) {
    }

    @Override
    public void updateRoomById(UUID id, Room room) {
    }

    @Override
    public Optional<Room> selectRoomById(UUID id) {
        return Optional.ofNullable(Room.builder().build());
    }
}
