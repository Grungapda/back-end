package com.grungapda.backend.userListener.command.domain.repository;

import com.grungapda.backend.userMusician.command.domain.aggregate.entity.Authority;
import com.grungapda.backend.userMusician.command.domain.aggregate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByUser(User user);

    Optional<Authority> findByRefreshToken(String refreshToken);

}
