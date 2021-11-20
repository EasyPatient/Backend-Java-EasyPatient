package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.SmartbandDao;
import com.easypatient.easypatient.dto.SmartbandDTO;
import com.easypatient.easypatient.dto.SmartbandGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SmartbandService {
    private final SmartbandDao smartbandDao;

    @Autowired
    public SmartbandService(@Qualifier("SmartbandPostgres") SmartbandDao smartbandDao) {
        this.smartbandDao = smartbandDao;
    }

    public void addSmartband(SmartbandDTO smartband) {
        smartbandDao.insertSmartband(smartband);
    }

    public List<SmartbandGetDTO> getAllSmartbands() {
        return smartbandDao.selectAllSmartbands();
    }

    public Optional<SmartbandGetDTO> getSmartbandById(UUID id) {
        return smartbandDao.selectSmartbandById(id);
    }

    public List<SmartbandGetDTO> getSmartbandByVariables(Optional<String> mac,
                                                         Optional<String> name,
                                                         Optional<LocalDateTime> createdAt,
                                                         Optional<LocalDateTime> updatedAt) throws SQLException {
        return smartbandDao.selectSmartbandByVariables(mac, name, createdAt, updatedAt);
    }

    public void deleteSmartband(UUID id) {
        smartbandDao.deleteSmartbandById(id);
    }

    public void updateSmartband(UUID id,
                                Optional<String> mac,
                                Optional<String> name) throws SQLException {
        smartbandDao.updateSmartbandById(id, mac, name);
    }
}
