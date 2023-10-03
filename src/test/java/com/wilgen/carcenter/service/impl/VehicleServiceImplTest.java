package com.wilgen.carcenter.service.impl;

import com.wilgen.carcenter.dto.VehicleDTO;
import com.wilgen.carcenter.repository.BrandRepository;
import com.wilgen.carcenter.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    @Mock
    VehicleRepository vehicleRepository;
    @Mock
    BrandRepository brandRepository;
    @InjectMocks
    VehicleServiceImpl vehicleService;

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
    void save_success() throws Exception {
        Mockito.when(vehicleRepository.existsById(vehicleDTO.getPlate())).thenReturn(false);
        Mockito.when(brandRepository.existsById(vehicleDTO.getBrand_id())).thenReturn(true);

        VehicleDTO result = vehicleService.save(vehicleDTO);

        assertNotNull(result);
        assertEquals("ABC123", result.getPlate());
        assertEquals(1L, result.getBrand_id());
        assertEquals("black", result.getColor());
    }

    @Test
    void save_shouldReturnENFE_withDuplicatePlate() throws Exception {
        Mockito.when(vehicleRepository.existsById(vehicleDTO.getPlate())).thenReturn(true);
        assertThrows(EntityNotFoundException.class, () -> vehicleService.save(vehicleDTO));
    }

    @Test
    void save_shouldReturnENFE_withNonExistentBrand() throws Exception {
        Mockito.when(vehicleRepository.existsById(vehicleDTO.getPlate())).thenReturn(false);
        Mockito.when(brandRepository.existsById(vehicleDTO.getBrand_id())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> vehicleService.save(vehicleDTO));
    }

    @Test
    void update() throws Exception {
        Mockito.when(vehicleRepository.existsById(vehicleDTO.getPlate())).thenReturn(true);
        Mockito.when(brandRepository.existsById(vehicleDTO.getBrand_id())).thenReturn(true);

        VehicleDTO result = vehicleService.update(vehicleDTO);

        verify(vehicleRepository).crud_vehicle(
                eq(vehicleDTO.getPlate()),
                eq(vehicleDTO.getBrand_id()),
                eq(vehicleDTO.getColor()),
                eq("U")
        );
        assertNotNull(result);
        assertEquals("ABC123", result.getPlate());
        assertEquals(1L, result.getBrand_id());
        assertEquals("black", result.getColor());
    }

    @Test
    void delete() throws Exception {
        Mockito.when(vehicleRepository.existsById(vehicleDTO.getPlate())).thenReturn(true);

        String result = vehicleService.delete(vehicleDTO);

        verify(vehicleRepository).crud_vehicle(
                eq(vehicleDTO.getPlate()),
                eq(vehicleDTO.getBrand_id()),
                eq(vehicleDTO.getColor()),
                eq("D")
        );

        assertNotNull(result);
        assertEquals("Vehicle deleted successfully", result);
    }
}