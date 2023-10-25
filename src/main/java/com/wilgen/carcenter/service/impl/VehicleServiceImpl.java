package com.wilgen.carcenter.service.impl;

import com.wilgen.carcenter.dto.VehicleDTO;
import com.wilgen.carcenter.model.User;
import com.wilgen.carcenter.model.Vehicle;
import com.wilgen.carcenter.repository.BrandRepository;
import com.wilgen.carcenter.repository.UserRepository;
import com.wilgen.carcenter.repository.VehicleRepository;
import com.wilgen.carcenter.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;
    public static final String DELETE = "D";
    public static final String INSERT = "I";
    public static final String UPDATE = "U";

    public VehicleServiceImpl(VehicleRepository vehicleRepository, BrandRepository brandRepository, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
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
    public List<Vehicle> findAllByUser(String email) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        try {
            return vehicleRepository.findAllByUser(user.get());
        } catch (Exception e) {
            throw new Exception("unexpected error", e);
        }
    }

    @Override
    public VehicleDTO save(VehicleDTO vehicleDTO) throws Exception {
        User user = getUserAuthenticated();
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
                    user.getId(),
                    INSERT);

            return vehicleDTO;
        } catch (Exception e) {
            throw new Exception("unexpected error");
        }


    }

    @Override
    public VehicleDTO update(VehicleDTO vehicleDTO) throws Exception {
        User user = getUserAuthenticated();
        String plate = vehicleDTO.getPlate();
        Long brandId = vehicleDTO.getBrand_id();

        if (!vehicleRepository.existsById(plate)) {
            throw new EntityNotFoundException("Vehicle with plate " + plate + " does not exist");
        }

        if (!brandRepository.existsById(brandId)) {
            throw new EntityNotFoundException("Brand with ID " + brandId + " does not exist");
        }

        try {
            vehicleRepository.crud_vehicle(plate, brandId, vehicleDTO.getColor(), user.getId(),UPDATE);
            return vehicleDTO;
        } catch (Exception e) {
            throw new Exception("Unexpected error while updating vehicle");
        }

    }

    @Override
    public String delete(VehicleDTO vehicleDTO) throws Exception {
        User user = getUserAuthenticated();
        String plate = vehicleDTO.getPlate();

        if (!vehicleRepository.existsById(plate)) {
            throw new EntityNotFoundException("Vehicle with plate " + plate + " does not exist");
        }

        try {
            vehicleRepository.crud_vehicle(
                    vehicleDTO.getPlate(),
                    vehicleDTO.getBrand_id(),
                    vehicleDTO.getColor(),
                    user.getId(),
                    DELETE);

            return "Vehicle deleted successfully";
        } catch (Exception e) {
            throw new Exception("Unexpected error while deleting vehicle");
        }
    }

    private User getUserAuthenticated() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email);

        return user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
