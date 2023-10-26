package com.wilgen.carcenter.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class VehicleDTO {
    private String plate;
    private String color;
    private Long brand_id;
    private List<MultipartFile> photos;
}
