package com.wilgen.carcenter.service.impl;

import com.wilgen.carcenter.constant.CrudOperations;
import com.wilgen.carcenter.dto.VehicleDTO;
import com.wilgen.carcenter.model.Vehicle;
import com.wilgen.carcenter.repository.BrandRepository;
import com.wilgen.carcenter.repository.VehicleRepository;
import com.wilgen.carcenter.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;
    public static final String DELETE = "D";
    public static final String INSERT = "I";
    public static final String UPDATE = "U";

    public VehicleServiceImpl(VehicleRepository vehicleRepository, BrandRepository brandRepository) {
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Vehicle> findAll() throws Exception {
        try {
            return vehicleRepository.findAll();
        } catch (Exception e) {
            throw new Exception("unexpected error", e);
        }
    }

    @Override
    public VehicleDTO save(VehicleDTO vehicleDTO) throws Exception {
        String plate = vehicleDTO.getPlate();
        Long brandId = vehicleDTO.getBrand_id();

        if (vehicleRepository.existsById(plate)) {
            throw new EntityNotFoundException("Vehicle with plate " + plate + " already exist");
        }

        if (!brandRepository.existsById(brandId)) {
            throw new EntityNotFoundException("Brand with ID " + brandId + " does not exist");
        }

        try {
            vehicleRepository.crud_vehicle(
                    plate,
                    brandId,
                    vehicleDTO.getColor(),
                    INSERT);

            return vehicleDTO;
        } catch (Exception e) {
            throw new Exception("unexpected error");
        }


    }

    @Override
    public VehicleDTO update(VehicleDTO vehicleDTO) throws Exception {
        String plate = vehicleDTO.getPlate();
        Long brandId = vehicleDTO.getBrand_id();

        if (!vehicleRepository.existsById(plate)) {
            throw new EntityNotFoundException("Vehicle with plate " + plate + " does not exist");
        }

        if (!brandRepository.existsById(brandId)) {
            throw new EntityNotFoundException("Brand with ID " + brandId + " does not exist");
        }

        try {
            vehicleRepository.crud_vehicle(plate, brandId, vehicleDTO.getColor(), UPDATE);
            return vehicleDTO;
        } catch (Exception e) {
            throw new Exception("Unexpected error while updating vehicle");
        }

    }

    @Override
    public String delete(VehicleDTO vehicleDTO) throws Exception {
        String plate = vehicleDTO.getPlate();

        if (!vehicleRepository.existsById(plate)) {
            throw new EntityNotFoundException("Vehicle with plate " + plate + " does not exist");
        }

        try {
            vehicleRepository.crud_vehicle(
                    vehicleDTO.getPlate(),
                    vehicleDTO.getBrand_id(),
                    vehicleDTO.getColor(),
                    DELETE);

            return "Vehicle deleted successfully";
        } catch (Exception e) {
            throw new Exception("Unexpected error while deleting vehicle");
        }
    }
}
