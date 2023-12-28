package com.example.serverjava.Repository;

import com.example.serverjava.Entity.Enum.Role;
import com.example.serverjava.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

    public Optional<List<User>> findAllByRolesIn(Set<Role> roles);
}
