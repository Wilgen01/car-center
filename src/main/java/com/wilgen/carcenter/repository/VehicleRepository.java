package com.wilgen.carcenter.repository;

import com.wilgen.carcenter.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    @Procedure("crud_vehiculos")
    Integer crud_vehicle(String plate, Long brand_id, String color, String operation);

}
