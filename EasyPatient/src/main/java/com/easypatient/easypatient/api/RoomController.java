package com.easypatient.easypatient.api;

import com.easypatient.easypatient.dto.RoomDTO;
import com.easypatient.easypatient.dto.RoomGetDTO;
import com.easypatient.easypatient.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/room")
@RestController
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public void addRoom(@Valid @NonNull @RequestBody RoomDTO room) {
        roomService.addRoom(room);
    }

    @GetMapping
    public List<RoomGetDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping(path = "{id}")
    public RoomGetDTO getRoomById(@PathVariable("id") UUID id) {
        return roomService.getRoomById(id)
                .orElse(null);
    }

    @GetMapping(path = "/getByVariables")
    public List<RoomGetDTO> getRoomByVariables(@RequestParam(required = false) Optional<Integer> number,
                                               @RequestParam(required = false) Optional<String> name,
                                               @RequestParam(required = false)
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                                           Optional<LocalDateTime> createdAfter,
                                               @RequestParam(required = false)
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd'T'HH:mm" })
                                                           Optional<LocalDateTime> updatedAfter) throws SQLException {
        return roomService.getRoomByVariables(number,
                name,
                createdAfter,
                updatedAfter);
    }

    @DeleteMapping(path = "{id}")
    public void deleteRoomById(@PathVariable("id") UUID id) {
        roomService.deleteRoom(id);
    }

    @PutMapping(path = "{id}")
    public void updateRoomById(@PathVariable("id") UUID id,
                               @RequestParam(required = false) Optional<Integer> number,
                               @RequestParam(required = false) Optional<String> name) throws SQLException {
        roomService.updateRoom(id, number, name);
    }
}
