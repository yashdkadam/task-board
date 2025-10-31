package com.yashdkadam.board.repository;

import com.yashdkadam.board.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    void deleteByUserId(Long customerId);

    Optional<User> findByEmail(String email);
}
