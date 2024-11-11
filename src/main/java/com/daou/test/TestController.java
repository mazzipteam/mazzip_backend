package com.daou.test;

import com.daou.response.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test/")
@RequiredArgsConstructor // 자동 DI
public class TestController {
    private final TestService testService;

    @GetMapping("test")
    public ResponseEntity test() {
        return ResponseEntity.builder().status(200).message("테스트 성공").data("테스트 성공").build();
    }

    @PostMapping("signup")
    public ResponseEntity testSignup(@RequestBody Signup signup) {
        var user = testService.signup(signup);

        return ResponseEntity.builder().status(200).message("계정 생성 성공").data(user).build();
    }

    @PostMapping("login")
    public ResponseEntity testLogin(@RequestBody Login login) {
        var user = testService.login(login);

        return ResponseEntity.builder().status(200).message("로그인 성공").data(user).build();
    }
}
