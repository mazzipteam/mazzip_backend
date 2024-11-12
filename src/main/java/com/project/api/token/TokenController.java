package com.project.api.token;

import com.project.api.token.dto.TokenCreateDTO;
import com.project.api.token.dto.TokenUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    // 토큰 생성
    @PostMapping
    public ResponseEntity create(@RequestBody TokenCreateDTO tokenCreateDTO) {
        var memo = tokenService.create(tokenCreateDTO);
        var response = CommonResponse.builder().code(200).message("토큰 생성 성공").data(memo).build();
        return ResponseEntity.ok(response);
    }

    // 토큰 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody TokenUpdateDTO tokenUpdateDTO) {
        var memo = tokenService.update(tokenUpdateDTO);
        var response = CommonResponse.builder().code(200).message("토큰 수정 성공").data(memo).build();
        return ResponseEntity.ok(response);
    }

    // 토큰 삭제
    @DeleteMapping("/{tokenId}")
    public ResponseEntity delete(@PathVariable Long tokenId) {
        var token = tokenService.delete(tokenId);
        var response = CommonResponse.builder().code(200).message("토큰 삭제 성공").data(token).build();
        return ResponseEntity.ok(response);
    }

    // 토큰 조회
    @GetMapping("/{tokenId}")
    public ResponseEntity get(@PathVariable Long tokenId) {
        var token = tokenService.getToken(tokenId);
        var response = CommonResponse.builder().code(200).message("토큰 조회 성공").data(token).build();
        return ResponseEntity.ok(response);
    }
}
