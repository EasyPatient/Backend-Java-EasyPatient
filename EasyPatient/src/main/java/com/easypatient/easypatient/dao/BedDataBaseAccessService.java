package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.BedDTO;
import com.easypatient.easypatient.dto.BedGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("BedPostgres")
public class BedDataBaseAccessService implements BedDao {

    @Override
    public void insertBed(BedDTO bed) {
    }

    @Override
    public List<BedGetDTO> selectAllBeds() {
        return List.of();
    }

    @Override
    public void deleteBedById(UUID id) {
    }

    @Override
    public void updateBedById(UUID id,
                              Optional<Integer> number,
                              Optional<UUID> patientId,
                              Optional<UUID> roomId) throws SQLException {
    }

    @Override
    public Optional<BedGetDTO> selectBedById(UUID id) {
        return Optional.ofNullable(BedGetDTO.builder().build());
    }

    @Override
    public List<BedGetDTO> selectBedByVariables(Optional<Integer> number,
                                                Optional<UUID> patientId,
                                                Optional<UUID> roomId,
                                                Optional<LocalDateTime> updatedAt,
                                                Optional<LocalDateTime> createdAt) throws SQLException {
        int i = 0;
        int k = 0;
        final String sqlSelectBedByVariable = "SELECT id, number, patient_id, room_id, created_at, updated_at FROM bed WHERE ";
        final String sqlNumber = " (number = ?)";
        final String sqlPatientId = " (patient_id = ?)";
        final String sqlRoomId = " (room_id = ?)";
        final String sqlUpdatedAt = " (created_at = ?)";
        final String sqlCreatedAt = " (updated_at = ?)";
        final String sqlAnd = " AND ";
        final String sqlSemicolon = ";";

        List<String> expressions = new java.util.ArrayList<>(List.of(sqlSelectBedByVariable));

        if(number.isPresent()) {
            i++;
            expressions.add(sqlNumber);
        }
        if(patientId.isPresent()) {
            i++;
            expressions.add(sqlPatientId);
            if(i > 1) {
                expressions.add(sqlAnd);
            }
        }
        if(roomId.isPresent()) {
            i++;
            expressions.add(sqlRoomId);
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
        if(createdAt.isPresent()) {
            i++;
            expressions.add(sqlCreatedAt);
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
            if(patientId.isPresent()) {
                jdbcTable[k] = patientId.get();
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
