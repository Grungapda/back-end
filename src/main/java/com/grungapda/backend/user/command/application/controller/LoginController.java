package com.grungapda.backend.user.command.application.controller;

import com.grungapda.backend.common.ResponseMessage;
import com.grungapda.backend.user.command.application.dto.login.LoginRequest;
import com.grungapda.backend.user.command.application.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(tags= "Login API")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @ApiOperation(value = "로그인 요청")
    @PostMapping(value = "/authentication/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        try {
            Map<String, Object> loginResult = loginService.login(loginRequest);

            // 리프레시 토큰을 HTTP Only 헤더에 설정
            String refreshToken = (String) loginResult.get("refreshToken");  // loginResult 맵에서 "refreshToken"키를 사용하여 리프레쉬 토큰 추출, 이 토큰은 사용자가 세션을 관리하고 인증할 때 사용
            Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);  // 리프레쉬 토큰을 사용하여 "refreshToken"이라는 새로운 쿠키 객체 생성, 이 쿠키는 클라이언트 측에 저장
            refreshTokenCookie.setHttpOnly(true);  // 이 쿠키를 "HTTP Only"로 설정, JavaScript를 통한 쿠키 액세스 방지 -> 보안 강화
            refreshTokenCookie.setSecure(true);  // 이 쿠키를 안전한 연결에서만 전송할 것임을 나타냄
            refreshTokenCookie.setPath("/");  // 모든 경로에서 이 쿠키 사용 가능
            response.addCookie(refreshTokenCookie);

            // 응답 json맵에서 리프레시 토큰 제거
            loginResult.remove("refreshToken");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(HttpStatus.OK.value(), "로그인 성공", loginResult));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null));
        }
    }

    @ApiOperation(value = "액세스 토큰 재발급 요청")
    @PostMapping("/authentication/new")
    public ResponseEntity<ResponseMessage> newAccessToken(HttpServletRequest request) {

        // 쿠키에서 리프레쉬 토큰을 가져옴
        Cookie[] cookies = request.getCookies();  // 현재 HTTP 요청에서 클라이언트로부터 수신한 모든 쿠키를 가져오는 코드, request 객체는 현재 요청에 대한 정보를 포함하고 있으며 request.getCookies()는 모든 쿠키를 배열로 반환
        String refreshToken = null; // 리프레시 토큰을 저장할 변수를 초기화
        if (cookies !=null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();  // "refreshToken" 쿠키의 값을 refreshToken 변수에 저장
                }
            }
        }

        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), "만료된 정보입니다.", null));
        }
        try {
            // 액세스 토큰 재발급
            Map<String, Object> tokenResult = loginService.newAccessToken(refreshToken);  // 해당 리프레시 토큰을 이용하여 새로운 액세스 토큰을 발급받는 작업
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(HttpStatus.OK.value(), "access 토큰 재발급 완료", tokenResult));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

//    @ApiOperation(value = "로그아웃 요청")  // 쿠키에 refresh토큰 삭제
//    @PostMapping("/authentication/logout")
//    public ResponseEntity<ResponseMessage> logout(HttpServletResponse response, LogoutResponse logoutResponse) {
//        try {
//
//            Map<String, Object> logoutResult = loginService.logout(logoutResponse);
//            // 리프레시 토큰 쿠키 삭제
//            Cookie refreshTokenCookie = new Cookie("refreshToken", null);
//            refreshTokenCookie.setHttpOnly(true);
//            refreshTokenCookie.setSecure(true);
//            refreshTokenCookie.setPath("/");
//            refreshTokenCookie.setMaxAge(0); // 쿠키 만료
//            response.addCookie(refreshTokenCookie);
//
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpStatus.OK.value(),"로그아웃",null));
//        } catch(Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),null));
//        }
//    }


//    public ResponseEntity<ResponseMessage> logout(@RequestBody LogoutResponse logoutResponse,
//                                                  @RequestHeader("Authorization") String refreshToken) {
//
//        try {
//            loginService.logout(logoutResponse, refreshToken);
//
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(new ResponseMessage(HttpStatus.OK.value(), "로그아웃이 정상적으로 되었습니다.", logoutResult));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
//        }
//    }
}
