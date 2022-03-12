package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByUsernameAndActive(String username, boolean active);

    Optional<Owner> findByUsername(String username);

    Optional<Owner> findByEmailAndActive(String email, boolean active);

    Optional<Owner> findByEmail(String email);

    boolean existsByUsernameOrEmail(String username, String email);

    @Query(nativeQuery = true,value = "select * from owner where username=:key or email=:key ")
    Optional<Owner> findOwnerByUsernameOrEmail(String key);
}
