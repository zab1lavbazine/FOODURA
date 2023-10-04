package com.example.serverjava.Repository;


import com.example.serverjava.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    public Optional<User> findById(UUID id);

    public Optional<User> findByEmail(String email);
}
