package com.wilgen.carcenter.service;

import com.wilgen.carcenter.dto.VehicleDTO;
import com.wilgen.carcenter.model.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAll() throws Exception;
    List<Vehicle> findAllByUser(String email) throws Exception;

    VehicleDTO save(VehicleDTO vehicleDTO) throws Exception;

    VehicleDTO update(VehicleDTO vehicleDTO) throws Exception;

    String delete(VehicleDTO vehicleDTO) throws Exception;
}
