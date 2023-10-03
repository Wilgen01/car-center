package com.wilgen.carcenter.controller;

import com.wilgen.carcenter.dto.Response;
import com.wilgen.carcenter.dto.SuccessResponse;
import com.wilgen.carcenter.dto.VehicleDTO;
import com.wilgen.carcenter.model.Vehicle;
import com.wilgen.carcenter.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleControllerTest {

    @Mock
    VehicleServiceImpl vehicleService;
    @InjectMocks
    VehicleController vehicleController;
    private VehicleDTO vehicleDTO;

    @BeforeEach
    void setUp() {
        vehicleDTO = VehicleDTO.builder()
                .plate("ABC123")
                .color("black")
                .brand_id(1L)
                .build();
    }

    @Test
    void findAll() throws Exception {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle());
        vehicles.add(new Vehicle());

        Mockito.when(vehicleService.findAll()).thenReturn(vehicles);
        ResponseEntity<Response> response = vehicleController.findAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse<List<Vehicle>> successResponse = (SuccessResponse<List<Vehicle>>) response.getBody();
        assertEquals("List", successResponse.getMessage());
        assertEquals("ok", successResponse.getStatus());
        assertEquals(vehicles, successResponse.getResult());

    }

    @Test
    void create() throws Exception {
        Mockito.when(vehicleService.save(vehicleDTO)).thenReturn(vehicleDTO);
        ResponseEntity<Response> response = vehicleController.create(vehicleDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse<VehicleDTO> successResponse = (SuccessResponse<VehicleDTO>) response.getBody();
        assertEquals("Created", successResponse.getMessage());
        assertEquals("ok", successResponse.getStatus());
        assertEquals(vehicleDTO, successResponse.getResult());

    }

    @Test
    void update() throws Exception {
        Mockito.when(vehicleService.update(vehicleDTO)).thenReturn(vehicleDTO);
        ResponseEntity<Response> response = vehicleController.update(vehicleDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse<VehicleDTO> successResponse = (SuccessResponse<VehicleDTO>) response.getBody();
        assertEquals("Updated", successResponse.getMessage());
        assertEquals("ok", successResponse.getStatus());
        assertEquals(vehicleDTO, successResponse.getResult());
    }

    @Test
    void delete() throws Exception {
        String successMessage = "Vehicle deleted successfully";
        Mockito.when(vehicleService.delete(vehicleDTO)).thenReturn(successMessage);
        ResponseEntity<Response> response = vehicleController.delete(vehicleDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse<String> successResponse = (SuccessResponse<String>) response.getBody();
        assertEquals("Deleted", successResponse.getMessage());
        assertEquals("ok", successResponse.getStatus());
        assertEquals(successMessage, successResponse.getResult());
    }
}