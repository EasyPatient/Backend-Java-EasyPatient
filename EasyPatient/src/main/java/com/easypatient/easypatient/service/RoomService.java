package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.RoomDao;
import com.easypatient.easypatient.dto.RoomDTO;
import com.easypatient.easypatient.dto.RoomGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {
    private final RoomDao roomDao;

    @Autowired
    public RoomService(@Qualifier("RoomPostgres") RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void addRoom(RoomDTO room) {
        int number = room.getNumber();
        if(number < 0) {
            throw new IllegalStateException("Cannot create a room with number that is less than 0.");
        }
        roomDao.insertRoom(room);
    }

    public List<RoomGetDTO> getAllRooms() {
        return roomDao.selectAllRooms();
    }

    public Optional<RoomGetDTO> getRoomById(UUID id) {
        return roomDao.selectRoomById(id);
    }

    public List<RoomGetDTO> getRoomByVariables(Optional<Integer> number,
                                               Optional<String> name,
                                               Optional<LocalDateTime> createdAt,
                                               Optional<LocalDateTime> updatedAt) throws SQLException {
        return roomDao.selectRoomByVariables(number, name, createdAt, updatedAt);
    }

    public void deleteRoom(UUID id) {
        roomDao.deleteRoomById(id);
    }

    public void updateRoom(UUID id,
                           Optional<Integer> number,
                           Optional<String> name) throws SQLException {
        if (number.isPresent() && number.get() < 0) {
            throw new IllegalStateException("Cannot create a room with number that is less than 0.");
        }
        roomDao.updateRoomById(id, number, name);
    }

}
