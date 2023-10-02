package com.wilgen.carcenter.repository;

import com.wilgen.carcenter.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
