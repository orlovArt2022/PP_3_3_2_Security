package ru.orlov.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.orlov.security.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role, Long> {

    Optional<Role> findByName(String name);
}
