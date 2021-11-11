package com.easypatient.easypatient.dao;

import com.easypatient.easypatient.model.Smartband;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SmartbandDao {
    void insertSmartband(UUID id, Smartband smartband);

    List<Smartband> selectAllSmartbands();

    void deleteSmartbandById(UUID id);

    void updateSmartbandById(UUID id, Smartband smartband);

    Optional<Smartband> selectSmartbandById(UUID id);
}
