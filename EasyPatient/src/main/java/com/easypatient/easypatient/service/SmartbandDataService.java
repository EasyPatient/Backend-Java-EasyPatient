package com.easypatient.easypatient.service;

import com.easypatient.easypatient.dao.SmartbandDataDao;
import com.easypatient.easypatient.dto.SmartbandDataDTO;
import com.easypatient.easypatient.dto.SmartbandDataGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class SmartbandDataService {
    private final SmartbandDataDao smartbandDataDao;

    @Autowired
    public SmartbandDataService(@Qualifier("SmartbandDataPostgres") SmartbandDataDao smartbandDataDao) {
        this.smartbandDataDao = smartbandDataDao;
    }

    public void addSmartbandData(SmartbandDataDTO smartbandData) {
        smartbandDataDao.insertSmartbandData(smartbandData);
    }

    public List<SmartbandDataGetDTO> getAllSmartbandData() {
        return smartbandDataDao.selectAllSmartbandDatas();
    }

    public List<SmartbandDataGetDTO> getSmartbandDataBySmartbandId(UUID smartbandId) throws SQLException {
        return smartbandDataDao.selectSmartbandDataBySmartbandId(smartbandId);
    }

    public List<SmartbandDataGetDTO> getSmartbandDataByPatientId(UUID patientId) throws SQLException  {
        return smartbandDataDao.selectSmartbandDataByPatientId(patientId);
    }

    public void deleteSmartbandData(UUID smartbandId, UUID patientId) {
        smartbandDataDao.deleteSmartbandDataByIds(smartbandId, patientId);
    }




}
