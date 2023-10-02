package com.wilgen.carcenter.controller;

import com.wilgen.carcenter.dto.Response;
import com.wilgen.carcenter.dto.SuccessResponse;
import com.wilgen.carcenter.dto.VehicleDTO;
import com.wilgen.carcenter.service.impl.VehicleServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleServiceImpl vehicleService;

    public VehicleController(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GetMapping
    public ResponseEntity<Response> findAll() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SuccessResponse<>("List", "ok", vehicleService.findAll()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(e.getMessage(), "error"));
        }
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
}
