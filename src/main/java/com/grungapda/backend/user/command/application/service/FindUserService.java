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

    // 전체 유저 조회
//    @Transactional(readOnly = true)
//    public List<LoginResponse> findAllUsers() {
//
//        List<User> users = findUserRepository.findAll();
//        List<LoginResponse> userList = new ArrayList<>();
//
//        for (User user : users) {
//            LoginResponse loginResponse = new LoginResponse(user);
//            userList.add(loginResponse);
//        }
//
//        return userList;
//    }

    // 토큰으로 유저 조회
    @Transactional(readOnly = true)
    public LoginResponse findByAccessToken(String accessToken) {

        Authority authority = findUserRepository.findByAccessToken(accessToken);
        LoginResponse loginResponse = new LoginResponse(authority.getUser());

        return loginResponse;

    }
}
