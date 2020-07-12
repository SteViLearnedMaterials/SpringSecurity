package com.example.security.repository;

import com.example.security.dto.dictionary.Role;
import com.example.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByRole(Role role);

    Optional<User> findUserByRoleAndId(Role role, Long id);

}
