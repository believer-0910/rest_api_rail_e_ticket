package com.example.rail_e_ticket_api.component;

import com.example.rail_e_ticket_api.entity.Owner;
import com.example.rail_e_ticket_api.repository.OwnerRepository;
import com.example.rail_e_ticket_api.util.enums.RolePermissionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    String action;

    private final PasswordEncoder passwordEncoder;

    private final OwnerRepository ownerRepository;

    @Override
    public void run(String... args) {
        if (action.equals("create")){
            AtomicInteger permissions = new AtomicInteger();
            Arrays.stream(RolePermissionEnum.values()).forEach(role-> permissions.addAndGet(role.getCode()));
            Owner owner = new Owner("Bekzod", "bekdev", "Bek@gmail.com", passwordEncoder.encode("1"), permissions.get(),true);
            ownerRepository.save(owner);
        }
    }
}
