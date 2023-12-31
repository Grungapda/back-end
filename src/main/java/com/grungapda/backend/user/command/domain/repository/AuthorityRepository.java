package com.grungapda.backend.user.command.domain.repository;

import com.grungapda.backend.user.command.domain.aggregate.entity.Authority;
import com.grungapda.backend.user.command.domain.aggregate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByUser(User user);
}
