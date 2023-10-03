package com.wilgen.carcenter.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleDTO {
    private String plate;
    private String color;
    private Long brand_id;
}
