package com.wilgen.carcenter.controller;

import com.wilgen.carcenter.dto.Response;
import com.wilgen.carcenter.dto.SuccessResponse;
import com.wilgen.carcenter.model.Brand;
import com.wilgen.carcenter.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<Response> findAll() {
        try {
            List<Brand> result = brandService.findAll();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SuccessResponse<>("List", "ok", result));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(e.getMessage(), "error"));
        }
    }
}
