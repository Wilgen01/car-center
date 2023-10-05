package com.wilgen.carcenter.service.impl;

import com.wilgen.carcenter.model.Brand;
import com.wilgen.carcenter.repository.BrandRepository;
import com.wilgen.carcenter.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAll() throws Exception {
        try {
            return brandRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Ha ocurrido un error inesperado", e);
        }
    }
}
