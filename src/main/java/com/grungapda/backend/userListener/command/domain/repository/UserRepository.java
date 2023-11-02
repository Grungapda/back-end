package com.grungapda.backend.userListener.command.domain.repository;

import com.grungapda.backend.userMusician.command.domain.aggregate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUsersByUserNo(Long userNo); // 회원 번호로 조회
    Optional<User> findUsersByUserEmail(String userEmail); // 이메일로 조회

    boolean existsByUserEmail(String userEmail); // 이메일 중복 조회
    boolean existsByUserNickName(String userNickName); // 닉네임 중복 조회
    boolean deleteByUserNo(Long userNo); // 회원 삭제
}
