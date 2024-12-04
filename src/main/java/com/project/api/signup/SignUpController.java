package com.project.api.signup;

import com.project.api.restaurant.dto.RestaurantCreateDTO;
import com.project.api.restaurant.dto.RestaurantUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    // 회원가입 대기 생성
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity create(
            @RequestPart SignUpDTO signUpDTO,
            @RequestPart(value = "multipartFileForeground", required = false) MultipartFile multipartFileForeground,
            @RequestPart(value = "multipartFileInterior", required = false) MultipartFile multipartFileInterior,
            @RequestPart(value = "multipartFileMenu", required = false) MultipartFile multipartFileMenu) {

        var signUp = signUpService.create(signUpDTO, multipartFileForeground, multipartFileInterior, multipartFileMenu);

        var response = CommonResponse.builder().code(200).message("회원가입 대기 생성 성공").data(signUp).build();
        return ResponseEntity.ok(response);
    }

    // 회원가입 대기 삭제
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity delete(@RequestParam Long restaurantId) {
        var signUp = signUpService.delete(restaurantId);
        var response = CommonResponse.builder().code(200).message("회원가입 대기 삭제 성공").data(signUp).build();
        return ResponseEntity.ok(response);
    }

    // 회원가입 대기 전체 조회
    @GetMapping("/{userId}")
    public ResponseEntity readAll(@RequestParam Long userId) {
        var signUps = signUpService.readAll(userId);

        var response = CommonResponse.builder().code(200).message("회원가입 대기 전체 조회 성공").data(signUps).build();
        return ResponseEntity.ok(response);
    }
}
