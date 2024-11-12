package com.project.api.my_clothes;

import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/myClothes")
@RequiredArgsConstructor
public class MyClothesController {
    private final MyClothesService myClothesService;

    // 의상 생성
    @PostMapping
    public ResponseEntity create(@RequestBody MyClothesCreateDTO myClothesCreateDTO) {
        var myClothes = myClothesService.create(myClothesCreateDTO);
        var response = CommonResponse.builder().code(200).message("내 의상 생성 성공").data(myClothes).build();
        return ResponseEntity.ok(response);
    }

    // 의상 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody MyClothesUpdateDTO myClothesUpdateDTO) {
        var myClothes = myClothesService.update(myClothesUpdateDTO);
        var response = CommonResponse.builder().code(200).message("내 의상 수정 성공").data(myClothes).build();
        return ResponseEntity.ok(response);
    }

    // 의상 삭제
    @DeleteMapping("/{myClothesId}")
    public ResponseEntity delete(@PathVariable Long myClothesId) {
        var myClothes = myClothesService.delete(myClothesId);
        var response = CommonResponse.builder().code(200).message("내 의상 삭제 성공").data(myClothes).build();
        return ResponseEntity.ok(response);
    }

    // 의상 조회
    @GetMapping("/{myClothesId}")
    public ResponseEntity get(@PathVariable Long myClothesId) {
        var myClothes = myClothesService.getMyClothes(myClothesId);
        var response = CommonResponse.builder().code(200).message("내 의상 조회 성공").data(myClothes).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/{avatarId}")
    public ResponseEntity getAll(@PathVariable Long avatarId) {
        var allMyClothes = myClothesService.getAllMyClothes(avatarId);
        var response = CommonResponse.builder().code(200).message("내 의상 조회 성공").data(allMyClothes).build();
        return ResponseEntity.ok(response);
    }
}
