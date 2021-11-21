package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.dto.BedDTO;
import com.easypatient.easypatient.dto.BedGetDTO;
import com.easypatient.easypatient.dto.PatientGetDTO;
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

    final String sqlSelectAllBeds = "SELECT id, number, patient_id, room_id, created_at, updated_at FROM bed";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BedDataBaseAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static BedGetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        int number = resultSet.getInt("number");
        UUID patient_id = UUID.fromString(resultSet.getString("patient_id"));
        UUID room_id = UUID.fromString(resultSet.getString("room_id"));
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return BedGetDTO.builder()
                .id(id)
                .number(number)
                .patientId(patient_id)
                .roomId(room_id)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @Override
    public void insertBed(BedDTO bed) {
    }


    @Override
    public List<BedGetDTO> selectAllBeds() {
        return jdbcTemplate.query(sqlSelectAllBeds,
                BedDataBaseAccessService::mapRow);
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
        return List.of();
    }
}
