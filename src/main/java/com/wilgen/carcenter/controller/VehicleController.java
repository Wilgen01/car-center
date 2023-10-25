package com.wilgen.carcenter.controller;

import com.wilgen.carcenter.dto.Response;
import com.wilgen.carcenter.dto.SuccessResponse;
import com.wilgen.carcenter.dto.VehicleDTO;
import com.wilgen.carcenter.model.Vehicle;
import com.wilgen.carcenter.service.VehicleService;
import com.wilgen.carcenter.service.impl.VehicleServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }


    public ResponseEntity<Response> findAll() throws Exception {

        List<Vehicle> result = vehicleService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("List", "ok", result));
    }

    @GetMapping
    public ResponseEntity<Response> findAllByUser() throws Exception {
        String userEmail = getEmailUserAuth();

        List<Vehicle> result = vehicleService.findAllByUser(userEmail);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("List", "ok", result));
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO createdVehicle = vehicleService.save(vehicleDTO);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new SuccessResponse<>("Created", "ok", createdVehicle));
        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response(e.getMessage(), "error"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(e.getMessage(), "error"));
        }
    }

    @PutMapping
    public ResponseEntity<Response> update(@RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO updatedVehicle = vehicleService.update(vehicleDTO);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SuccessResponse<>("Updated", "ok", updatedVehicle));

        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response(e.getMessage(), "error"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(e.getMessage(), "error"));
        }
    }

    @DeleteMapping
    public ResponseEntity<Response> delete(@RequestBody VehicleDTO vehicleDTO) {
        try {
            String response = vehicleService.delete(vehicleDTO);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SuccessResponse<>("Deleted", "ok", response));

        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response(e.getMessage(), "error"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(e.getMessage(), "error"));
        }
    }

    private String getEmailUserAuth() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
