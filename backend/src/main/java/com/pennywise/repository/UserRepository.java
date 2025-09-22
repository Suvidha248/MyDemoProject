package com.pennywise.repository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.pennywise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

