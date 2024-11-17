package com.project.api.clothes;

import com.project.api.clothes.dto.ClothesCreateDTO;
import com.project.api.clothes.dto.ClothesUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clothes")
@RequiredArgsConstructor
public class ClothesController {
    private final ClothesService clothesService;

    // TODO: Category 반환하는 API도 있어야 할듯

    // 의상 생성
    @PostMapping
    public ResponseEntity create(@RequestBody ClothesCreateDTO clothesCreateDTO) {
        var clothes = clothesService.create(clothesCreateDTO);
        var response = CommonResponse.builder().code(200).message("의상 생성 성공").data(clothes).build();
        return ResponseEntity.ok(response);
    }

    // 의상 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody ClothesUpdateDTO clothesUpdateDTO) {
        var clothes = clothesService.update(clothesUpdateDTO);
        var response = CommonResponse.builder().code(200).message("의상 수정 성공").data(clothes).build();
        return ResponseEntity.ok(response);
    }

    // 의상 삭제
    @DeleteMapping("/{clothesId}")
    public ResponseEntity delete(@PathVariable Long clothesId) {
        var clothes = clothesService.delete(clothesId);
        var response = CommonResponse.builder().code(200).message("의상 삭제 성공").data(clothes).build();
        return ResponseEntity.ok(response);
    }

    // 의상 조회
    @GetMapping("/{clothesId}")
    public ResponseEntity get(@PathVariable Long clothesId) {
        var clothes = clothesService.getClothes(clothesId);
        var response = CommonResponse.builder().code(200).message("의상 조회 성공").data(clothes).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        var allClothes = clothesService.getAllClothes();
        var response = CommonResponse.builder().code(200).message("의상 조회 성공").data(allClothes).build();
        return ResponseEntity.ok(response);
    }
}
