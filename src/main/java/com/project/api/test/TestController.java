package com.project.api.test;

import com.project.api.test.dto.TestAccessDTO;
import com.project.api.test.dto.TestCreateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test/")
@RequiredArgsConstructor // 자동 DI
public class TestController {
    private final TestService testService;

    // RestAPI 기초
    @GetMapping("test1")
    public ResponseEntity test() {
        return ResponseEntity.ok(CommonResponse.builder().code(200).message("테스트 성공").data("테스트 성공").build());
    }

    // Test 생성
    @PostMapping("testCreate")
    public ResponseEntity testCreate(@RequestBody TestCreateDTO testCreateDTO) {
        var test = testService.create(testCreateDTO);

        return ResponseEntity.ok(CommonResponse.builder().code(200).message("계정 생성 성공").data(test).build());
    }

    // Test 조회
    @PostMapping("testAccess")
    public ResponseEntity testAccess(@RequestBody TestAccessDTO testAccessDTO) {
        var test = testService.access(testAccessDTO);

        return ResponseEntity.ok(CommonResponse.builder().code(200).message("로그인 성공").data(test).build());
    }
}
