package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("BedPostgres")
public class BedDataBaseAccessService implements BedDao {

    final String sqlSelectAllBeds = "SELECT id, number, room_id, created_at, updated_at FROM bed";
    final String sqlSelectBedByID = "SELECT id, number, room_id, created_at, updated_at FROM bed WHERE id = ?";
    final String sqlSelectBedByVariable = "SELECT id, number, room_id, created_at, updated_at FROM bed WHERE";
    final String sqlNumber = "(number = ?)";
    final String sqlRoomId = "(room_id = ?)";
    final String sqlCreatedAt = "(created_at = ?)";
    final String sqlUpdatedAt = "(updated_at = ?)";
    final String sqlAnd = "AND";
    final String sqlSemicolon = ";";
    final String sqlInsertBed = "INSERT INTO bed VALUES(?, ?, ?, ?)";
    final String sqlDeleteBed = "DELETE FROM bed WHERE id = ?";
    final String sqlUpdateBedById = "UPDATE bed SET number = ?, room_id = ?, updated_at = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final RoomDataBaseAccessService roomDataBaseAccessService;

    @Autowired
    public BedDataBaseAccessService(JdbcTemplate jdbcTemplate,
                                    RoomDataBaseAccessService roomDataBaseAccessService) {
        this.jdbcTemplate = jdbcTemplate;
        this.roomDataBaseAccessService = roomDataBaseAccessService;
    }

    private static BedGetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        int number = resultSet.getInt("number");
        UUID room_id = UUID.fromString(resultSet.getString("room_id"));
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return BedGetDTO.builder()
                .id(id)
                .number(number)
                .roomId(room_id)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @Override
    public void insertBed(BedDTO bed) throws SQLException {

        LocalDateTime date = LocalDateTime.now();

        UUID roomId = bed.getRoomId();

        Optional<RoomGetDTO> roomFromDB;
        try {
            roomFromDB = roomDataBaseAccessService.selectRoomById(roomId);
        } catch (Exception e) {
            throw new SQLException("can not insert bed with room ID: " + roomId);
        }

        if(roomFromDB.isPresent()) {
            jdbcTemplate.update(sqlInsertBed,
                    bed.getNumber(),
                    bed.getRoomId(),
                    date,
                    date);
        } else {
            throw new SQLException("can not insert bed with room ID: " + roomId);
        }
    }


    @Override
    public List<BedGetDTO> selectAllBeds() {
        return jdbcTemplate.query(sqlSelectAllBeds,
                BedDataBaseAccessService::mapRow);
    }

    @Override
    public void deleteBedById(UUID id) {
        Object[] args = new Object[]{id};
        jdbcTemplate.update(sqlDeleteBed, args);
    }

    @Override
    public void updateBedById(UUID id,
                              Optional<Integer> number,
                              Optional<UUID> roomId) throws SQLException {
        Optional<BedGetDTO> existingBedOptional = selectBedById(id);
        BedGetDTO existingBed;
        int numberToUpdate;
        UUID roomIdToUpdate;
        LocalDateTime updatedAtToUpdate;

        if(existingBedOptional.isPresent()) {
            existingBed = existingBedOptional.get();
        } else {
            throw new SQLException("Bed with ID: " + id + "does not exist.");
        }
        numberToUpdate = number.orElseGet(existingBed::getNumber);
        roomIdToUpdate = roomId.orElseGet(existingBed::getRoomId);
        updatedAtToUpdate = LocalDateTime.now();

        jdbcTemplate.update(sqlUpdateBedById,
                numberToUpdate,
                roomIdToUpdate,
                updatedAtToUpdate,
                id);
    }

    @Override
    public Optional<BedGetDTO> selectBedById(UUID id) {
        BedGetDTO bed = jdbcTemplate.queryForObject(
                sqlSelectBedByID,
                new Object[]{id},
                BedDataBaseAccessService::mapRow);
        return Optional.ofNullable(bed);
    }


    @Override
    public List<BedGetDTO> selectBedByVariables(Optional<Integer> number,
                                                Optional<UUID> roomId,
                                                Optional<LocalDateTime> updatedAt,
                                                Optional<LocalDateTime> createdAt) throws SQLException {
        int i = 0;
        int k = 0;

        List<String> expressions = new java.util.ArrayList<>(List.of(sqlSelectBedByVariable));

        if(number.isPresent()) {
            i++;
            expressions.add(sqlNumber);
        }
        if(roomId.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlRoomId);
        }
        if(updatedAt.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlUpdatedAt);
        }
        if(createdAt.isPresent()) {
            i++;
            if(i > 1) {
                expressions.add(sqlAnd);
            }
            expressions.add(sqlCreatedAt);
        }

        String sqlExpression = String.join(" ", expressions);
        sqlExpression = sqlExpression.concat(sqlSemicolon);

        if(i != 0) {
            Object[] jdbcTable = new Object[i];
            if(number.isPresent()) {
                jdbcTable[k] = number.get();
                k++;
            }
            if(roomId.isPresent()) {
                jdbcTable[k] = roomId.get();
                k++;
            }
            if(updatedAt.isPresent()) {
                jdbcTable[k] = updatedAt.get();
                k++;
            }
            if(createdAt.isPresent()) {
                jdbcTable[k] = createdAt.get();
                k++;
            }
            return jdbcTemplate.query(
                    sqlExpression,
                    jdbcTable,
                    BedDataBaseAccessService::mapRow);
        } else {
            throw new SQLException("can not query without any parameters!");
        }
    }
}
