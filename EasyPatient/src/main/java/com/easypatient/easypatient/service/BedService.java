package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.BedDao;
import com.easypatient.easypatient.dto.BedDTO;
import com.easypatient.easypatient.dto.BedGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BedService {
    private final BedDao bedDao;

    @Autowired
    public BedService(@Qualifier("BedPostgres") BedDao bedDao) {
        this.bedDao = bedDao;
    }

    public void addBed(@Valid BedDTO bed) throws SQLException {
        bedDao.insertBed(bed);
    }

    public List<BedGetDTO> getAllBeds() {
        return bedDao.selectAllBeds();
    }

    public Optional<BedGetDTO> getBedById(UUID id) {
        return bedDao.selectBedById(id);
    }

    public List<BedGetDTO> getBedByVariables(Optional<Integer> number,
                                             Optional<UUID> roomId,
                                             Optional<LocalDateTime> createdAfter,
                                             Optional<LocalDateTime> updatedAfter) throws SQLException {
        return bedDao.selectBedByVariables(number, roomId, updatedAfter, createdAfter);
    }

    public void deleteBed(UUID id) {
        bedDao.deleteBedById(id);
    }

    public void updateBed(UUID id,
                          Optional<Integer> number,
                          Optional<UUID> roomId) throws SQLException {
        bedDao.updateBedById(id, number, roomId);
    }
}
