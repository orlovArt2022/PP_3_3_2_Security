package ru.orlov.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.orlov.security.model.User;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
