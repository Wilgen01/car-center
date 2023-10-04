package com.wilgen.carcenter.bootstrap;

import com.wilgen.carcenter.model.Brand;
import com.wilgen.carcenter.model.ERole;
import com.wilgen.carcenter.model.Role;
import com.wilgen.carcenter.model.User;
import com.wilgen.carcenter.repository.BrandRepository;
import com.wilgen.carcenter.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BrandRepository brandRepository;


    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, BrandRepository brandRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.brandRepository = brandRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("*************** CARGANDO DATOS BASE PARA PRUEBA ***************");
        createBaseUser();
        createBrands();
    }

    public void createBaseUser() {
        System.out.println("*************** CARGANDO USUARIO BASE ***************");

        Boolean existBaseUser = userRepository.existsByEmail("user@gmail.com");
        if (existBaseUser) return;

        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder()
                .name(ERole.USER).build());

        User user = User.builder()
                .password(passwordEncoder.encode("123456"))
                .name("Usuario Prueba")
                .email("user@gmail.com")
                .roles(roles)
                .build();

        userRepository.save(user);
    }

    public void createBrands() {
        System.out.println("*************** CARGANDO MARCAS ***************");

        if (brandRepository.count() > 0) return;

        Brand brand1 = Brand.builder().name("Chevrolet").build();
        Brand brand2 = Brand.builder().name("Renault").build();
        Brand brand3 = Brand.builder().name("Mazda").build();

        brandRepository.save(brand1);
        brandRepository.save(brand2);
        brandRepository.save(brand3);
    }
}
