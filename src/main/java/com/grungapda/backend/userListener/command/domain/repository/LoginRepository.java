package com.grungapda.backend.userListener.command.domain.repository;

import com.grungapda.backend.userMusician.command.domain.aggregate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmail(String userEmail); //아이디를 기준으로 회원정보 조회
}
