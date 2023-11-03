package com.grungapda.backend.user.command.application.service;

import com.grungapda.backend.jwt.TokenProvider;
import com.grungapda.backend.user.command.application.dto.auth.AuthResponse;
import com.grungapda.backend.user.command.application.dto.login.LoginRequest;
import com.grungapda.backend.user.command.application.dto.login.LoginResponse;
import com.grungapda.backend.user.command.domain.aggregate.entity.Authority;
import com.grungapda.backend.user.command.domain.aggregate.entity.User;
import com.grungapda.backend.user.command.domain.repository.AuthorityRepository;
import com.grungapda.backend.user.command.domain.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    // 로그인
    @Transactional
    public Map<String, Object> login(LoginRequest loginRequest) throws Exception {
        User user = loginRepository.findByUserEmail(loginRequest.getUserEmail()).orElseThrow(
                () -> new BadCredentialsException("잘못된 아이디 혹은 비밀번호입니다."));

        if (!passwordEncoder.matches(loginRequest.getUserPwd(), user.getUserPwd())) {
            throw new BadCredentialsException("잘못된 아이디 혹은 비밀번호입니다.");
        }

        // 멤버 정보를 기반으로 jwt accessToken 생성
        String accessToken = tokenProvider.createAccessToken(
                new UsernamePasswordAuthenticationToken(
                        user.getUserEmail(),
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority(user.getUserNickName()))
                )
        );

        String refreshToken = tokenProvider.createRefreshToken();

        // 초기 로그인
        Authority authority = authorityRepository.findByUser(user)
                .orElseGet(() -> Authority.builder()
                        .user(user)
                        .build());

        authority.updateToken("Bearer", accessToken, refreshToken); // bearer는 jwt 토큰 타입?

        // 생성된 액세스 토큰을 Authority 엔티티에 저장
        authorityRepository.save(authority);

        AuthResponse authResponse = new AuthResponse(accessToken, refreshToken);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("loginResponse", LoginResponse.builder()
                .userNo(user.getUserNo())
                .userNickname(user.getUserNickName())
                .musician(user.getMusician())
                .sessionType(user.getSessionType())
                .genre(user.getGenre())
                .mood(user.getMood())
                .authority(Collections.singletonList(authResponse))
                .build());
        resultMap.put("refreshToken", refreshToken);

        return resultMap;
    }

    // 엑세스 토큰 재발행
    public Map<String, Object> newAccessToken(String refreshToken) {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("accessToken",tokenProvider.newAccessToken(refreshToken));

        return resultMap;
    }

//    @Transactional
//    public void logout(LogoutResponse logoutResponse, String refreshToken) throws Exception {
//
//        if (!tokenProvider.validateToken(refreshToken)) {
//            throw new BadCredentialsException("유효하지 않은 토큰");
//        }
//        Authentication authentication = tokenProvider.getAuthentication(logoutResponse, refreshToken);
//        String refreshToken = authentication.getName();
//        Authority authority = AuthorityRepository.findByRefreshToken(refreshToken)
//                .orElseThrow(() -> new IllegalArgumentException("해당 토큰을 찾을 수 없습니다."));
//
//        authority.deleteRefreshToken(true);
//    }

//    public Map<String, Object> logout(LogoutResponse logoutResponse) throws Exception {
//
//        User user = authorityRepository.findByRefreshToken(logoutResponse.getRefreshTokenIsDeleted()).orElseThrow(
//                () -> new BadCredentialsException("잘못된 토큰입니다.")).getUser();
//
//        String refreshToken = tokenProvider.deleteRefreshToken(String.valueOf(logoutResponse.getRefreshTokenIsDeleted()));
//
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("logout", tokenProvider.deleteRefreshToken(refreshToken));
//
//        return resultMap;
//    }
}
