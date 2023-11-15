package com.grungapda.backend.user.command.application.service;

import com.grungapda.backend.jwt.TokenProvider;
import com.grungapda.backend.user.command.application.dto.sign.SignRequest;
import com.grungapda.backend.user.command.application.dto.update.UpdateInfoRequest;
import com.grungapda.backend.user.command.application.dto.update.UpdatePwdRequest;
import com.grungapda.backend.user.command.domain.aggregate.entity.User;
import com.grungapda.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public void signUp(SignRequest signRequest) throws Exception {
        try {
            User user = User.builder()
                    .userEmail(signRequest.getUserEmail())
                    .userPwd(passwordEncoder.encode(signRequest.getUserPwd()))
                    .userNickName(signRequest.getUserNickname())
                    .userIsDeleted(false)
                    .sessionType(signRequest.getSessionType())
                    .genre(signRequest.getGenre())
                    .mood(signRequest.getMood())
                    .build();

            userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("잘못된 요청입니다.");
        }
    }

    // 이메일 중복 조회
    @Transactional(readOnly = true)
    public void checkUserEmail(String userEmail) {

        if (userEmail == null || userEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 이메일입니다.");
        } else if (userRepository.existsByUserEmail(userEmail)) {
            throw new IllegalArgumentException("사용중인 이메일입니다.");
        }
    }

    // 닉네임 중복 조회
    @Transactional(readOnly = true)
    public void checkUserNickname(String userNickname) {

        if (userNickname == null || userNickname.trim().isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 이메일입니다.");
        } else if (userRepository.existsByUserNickName(userNickname)) {
            throw new IllegalArgumentException("사용중인 닉네임 입니다.");
        }
    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(Long userNo, String accessToken) {

        // 토큰 유효성 검사
        if (!tokenProvider.validateToken(accessToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        // accessToken을 사용하여 사용자를 인증하고 해당 사용자의 정보를 가져옴
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        String userEmail = authentication.getName();
        User user = userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        // 권한 확인
        if (!user.getUserEmail().equals(userEmail)) {
            throw new AccessDeniedException("권한이 없습니다.");
        }

        user.deleteUser(null, "탈퇴한 사용자입니다.", true, null,null, null, null);
    }

    // 비밀번호 변경
    @Transactional
    public void changeUserPwd(UpdatePwdRequest pwdRequest, Long userNo, String accessToken) {

        // 토큰의 유효성 검사
        if (!tokenProvider.validateToken(accessToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        // accessToken을 사용하여 사용자를 인증하고 해당 사용자의 정보를 가져옴
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        String userEmail = authentication.getName();
        User user = userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        // 권한 확인
        if (!user.getUserEmail().equals(userEmail)) {
            throw new AccessDeniedException("권한이 없습니다.");
        }

        if (!passwordEncoder.matches(pwdRequest.getUserPwd(), user.getUserPwd())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        } else if (passwordEncoder.matches(pwdRequest.getChangePassword(), user.getUserPwd())) {
            throw new IllegalArgumentException("같은 비밀번호로는 변경할 수 없습니다.");
        }

        user.setUserPwd(passwordEncoder.encode(pwdRequest.getChangePassword()));
    }

    // 회원 정보 변경
    @Transactional
    public void updateUserInfo(UpdateInfoRequest infoRequest, Long userNo, String accessToken) {

        // 토큰의 유효성 검사
        if (!tokenProvider.validateToken(accessToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        // accessToken을 사용하여 사용자를 인증하고 해당 사용자의 정보를 가져옴
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        String userEmail = authentication.getName();
        User user = userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        // 권한 확인
        if (!user.getUserEmail().equals(userEmail)) {
            throw new AccessDeniedException("권한이 없습니다.");
        }

        user.setUserNickName(infoRequest.getUserNickname());
    }
}
