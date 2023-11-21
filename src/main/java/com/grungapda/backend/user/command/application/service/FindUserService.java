package com.grungapda.backend.user.command.application.service;

import com.grungapda.backend.user.command.application.dto.login.LoginResponse;
import com.grungapda.backend.user.command.domain.aggregate.entity.Authority;
import com.grungapda.backend.user.command.domain.aggregate.entity.User;
import com.grungapda.backend.user.command.domain.repository.AuthorityRepository;
import com.grungapda.backend.user.command.domain.repository.FindUserRepository;
import com.grungapda.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindUserService {

    private final FindUserRepository findUserRepository;
    private final UserRepository userRepository;

    // 유저 번호로 조회
    @Transactional(readOnly = true)
    public LoginResponse findByUserNo(Long userNo) {

        Optional<User> user = userRepository.findByUserNo(userNo);
        LoginResponse loginResponse = new LoginResponse(user);

        return loginResponse;
    }

    // 토큰으로 유저 조회
    @Transactional(readOnly = true)
    public LoginResponse findByAccessToken(String accessToken) {

        Authority authority = findUserRepository.findByAccessToken(accessToken);
        LoginResponse loginResponse = new LoginResponse(authority.getUser());

        return loginResponse;

    }
}
