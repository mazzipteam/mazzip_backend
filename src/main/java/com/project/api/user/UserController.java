package com.project.api.user;

import com.project.api.user.dto.UserCreateDTO;
import com.project.api.user.dto.UserUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 유저 생성
    @PostMapping
    public ResponseEntity create(@RequestBody UserCreateDTO userCreateDTO) {
        var user = userService.create(userCreateDTO);
        var response = CommonResponse.builder().code(200).message("유저 생성 성공").data(user).build();
        return ResponseEntity.ok(response);
    }

    // 유저 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody UserUpdateDTO userUpdateDTO) {
        var user = userService.update(userUpdateDTO);
        var response = CommonResponse.builder().code(200).message("유저 수정 성공").data(user).build();
        return ResponseEntity.ok(response);
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity delete(@RequestParam Long userId) {
        var user = userService.delete(userId);
        var response = CommonResponse.builder().code(200).message("유저 삭제 성공").data(user).build();
        return ResponseEntity.ok(response);
    }

    // 유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity get(@PathVariable Long userId) {
        var user = userService.getUser(userId);
        var response = CommonResponse.builder().code(200).message("유저 조회 성공").data(user).build();
        return ResponseEntity.ok(response);
    }
}
