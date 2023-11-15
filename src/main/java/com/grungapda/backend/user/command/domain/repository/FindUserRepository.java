package com.grungapda.backend.user.command.domain.repository;

import com.grungapda.backend.user.command.domain.aggregate.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FindUserRepository extends JpaRepository<Authority, Long> {

//    List<User> findAllUsers();

    Authority findByAccessToken(String accessToken);
}
