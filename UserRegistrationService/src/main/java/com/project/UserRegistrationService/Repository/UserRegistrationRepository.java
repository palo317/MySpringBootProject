package com.project.UserRegistrationService.Repository;

import com.project.UserRegistrationService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRegistrationRepository extends JpaRepository<User, Integer> {

    boolean existsByEmailId(String email);
    Optional<User> findByEmailId(String email);
}
