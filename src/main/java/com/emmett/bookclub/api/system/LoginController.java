package com.emmett.bookclub.api.system;

import com.emmett.bookclub.core.security.AuthToken;
import com.emmett.bookclub.core.security.user.UserDto;
import com.emmett.bookclub.core.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class LoginController {
    private final Util util;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthToken> login(@RequestBody UserDto userDto, HttpSession session, HttpServletRequest request) {
        return userService.login(userDto, session, request);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        userService.logout(session);
        return new ResponseEntity<> ("로그아웃 되었습니다.", HttpStatus.OK);
    }
}
