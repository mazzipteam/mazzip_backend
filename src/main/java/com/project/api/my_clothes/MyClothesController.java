package com.project.api.my_clothes;

import com.project.api.avatar.AvatarService;
import com.project.api.clothes.ClothesService;
import com.project.api.my_clothes.dto.MyClothesCreateDTO;
import com.project.api.my_clothes.dto.MyClothesUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/myClothes")
@RequiredArgsConstructor
public class MyClothesController {
    private final MyClothesService myClothesService;
    private final AvatarService avatarService;
    private final ClothesService clothesService;

    // 내 의상 생성
    @PostMapping
    public ResponseEntity create(@RequestBody MyClothesCreateDTO myClothesCreateDTO) {
        var avatar = avatarService.getAvatar(myClothesCreateDTO.getAvatarId());
        var myClothes = myClothesService.create(myClothesCreateDTO, avatar);
        var response = CommonResponse.builder().code(200).message("내 의상 생성 성공").data(myClothes).build();
        return ResponseEntity.ok(response);
    }

    // 내 의상 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody MyClothesUpdateDTO myClothesUpdateDTO) {
        var myClothes = myClothesService.update(myClothesUpdateDTO);
        var response = CommonResponse.builder().code(200).message("내 의상 수정 성공").data(myClothes).build();
        return ResponseEntity.ok(response);
    }

    // 내 의상 삭제
    @DeleteMapping("/{myClothesId}")
    public ResponseEntity delete(@PathVariable Long myClothesId) {
        var myClothes = myClothesService.delete(myClothesId);
        var response = CommonResponse.builder().code(200).message("내 의상 삭제 성공").data(myClothes).build();
        return ResponseEntity.ok(response);
    }

    // 내 의상 조회
    @GetMapping("/{myClothesId}")
    public ResponseEntity get(@PathVariable Long myClothesId) {
        var myClothes = myClothesService.getMyClothes(myClothesId);
        var response = CommonResponse.builder().code(200).message("내 의상 조회 성공").data(myClothes).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/{avatarId}")
    public ResponseEntity getAll(@PathVariable Long avatarId) {
        var avatar = avatarService.getAvatar(avatarId);
        var allMyClothes = myClothesService.getAllMyClothes(avatar);
        var response = CommonResponse.builder().code(200).message("내 의상 조회 성공").data(allMyClothes).build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/wear/{myClothesId}")
    public ResponseEntity wear(@PathVariable Long myClothesId) {
        var wantClothes = myClothesService.getMyClothes(myClothesId);
        var myClothes = myClothesService.wear(wantClothes);
        var response = CommonResponse.builder().code(200).message("내 의상 착용 성공").data(myClothes).build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/wear/{clothesId}/{avatarId}")
    public ResponseEntity wear2(@PathVariable Long clothesId, @PathVariable Long avatarId) {
        var clothes = clothesService.getClothes(clothesId);
        var avatar = avatarService.getAvatar(avatarId);
        var wantClothes = myClothesService.getMyClothes(clothes, avatar);

        var myClothes = myClothesService.wear(wantClothes);
        var response = CommonResponse.builder().code(200).message("내 의상 착용 성공").data(myClothes).build();
        return ResponseEntity.ok(response);
    }
}
