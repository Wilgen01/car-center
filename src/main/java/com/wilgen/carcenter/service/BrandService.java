package com.wilgen.carcenter.service;

import com.wilgen.carcenter.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll() throws Exception;
}
