package com.grungapda.backend.userMusician.command.application.controller;

import com.grungapda.backend.common.ResponseMessage;
import com.grungapda.backend.userMusician.command.application.dto.sign.SignRequest;
import com.grungapda.backend.userMusician.command.application.dto.update.UpdateInfoRequest;
import com.grungapda.backend.userMusician.command.application.dto.update.UpdatePwdRequest;
import com.grungapda.backend.userMusician.command.application.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags= "User SignUp CRUD API")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final SignService signService;

    @ApiOperation(value = "회원가입")
    @PostMapping(value = "/users")
    public ResponseEntity<ResponseMessage> signUp(@Valid @RequestBody SignRequest signRequest) {
        try {
            signService.signUp(signRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseMessage(HttpStatus.CREATED.value(), "회원가입 완료", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @ApiOperation(value = "이메일 중복 조회")
    @GetMapping(value = "/users/email/{userEmail}/check")
    public ResponseEntity<ResponseMessage> checkUserEmail(@PathVariable String userEmail) {
        try {
            signService.checkUserEmail(userEmail);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(HttpStatus.OK.value(), "사용 가능한 이메일입니다.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @ApiOperation(value = "닉네임 중복 조회")
    @GetMapping(value = "/users/nickname/{userNickname}/check")
    public ResponseEntity<ResponseMessage> checkUserNickname(@PathVariable String userNickname) {
        try {
            signService.checkUserNickname(userNickname);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(HttpStatus.OK.value(), "사용 가능한 닉네임입니다.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @ApiOperation(value = "비밀번호 변경")
    @PutMapping("/users/pwd/{userNo}")
    public ResponseEntity<ResponseMessage> changePwd(@PathVariable Long userNo,
                                                     @RequestBody UpdatePwdRequest pwdRequest,
                                                     @RequestHeader("Authorization") String accessToken) {
        try {
            signService.changeUserPwd(pwdRequest, userNo, accessToken);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(HttpStatus.OK.value(), "비밀번호가 변경되었습니다.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @ApiOperation(value = "회원 정보 변경")
    @PutMapping("/users/info/{userNo}")
    public ResponseEntity<ResponseMessage> updateUserInfo(@PathVariable Long userNo,
                                                          @RequestBody UpdateInfoRequest infoRequest,
                                                          @RequestHeader("Authorization") String accessToken) {
        try {
            signService.updateUserInfo(infoRequest, userNo, accessToken);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(HttpStatus.OK.value(), "회원 정보가 변경되었습니다.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping(value = "/users/{userNo}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable Long userNo,
                                                      @RequestHeader("Authorization") String accessToken) {
        try {
            signService.deleteUser(userNo, accessToken);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(HttpStatus.OK.value(), "회원 탈퇴가 정상적으로 처리되었습니다.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }
}
