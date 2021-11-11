package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Smartband;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SmartbandDao {
    void insertSmartband(UUID smartbandId, Smartband smartband);

    List<Smartband> selectAllSmartbands();

    void deleteSmartbandById(UUID smartbandId);

    void updateSmartbandById(UUID smartbandId, Smartband smartband);

    Optional<Smartband> selectSmartbandById(UUID smartbandId);
}
