package com.project.api.avatar;

import com.project.api.avatar.dto.AvatarCreateDTO;
import com.project.api.avatar.dto.AvatarUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/avatar")
@RequiredArgsConstructor
public class AvatarController {
    private final AvatarService avatarService;

    // 아바타 생성
    @PostMapping
    public ResponseEntity create(@RequestBody AvatarCreateDTO avatarCreateDTO) {
        var avatar = avatarService.create(avatarCreateDTO);
        var response = CommonResponse.builder().code(200).message("아바타 생성 성공").data(avatar).build();
        return ResponseEntity.ok(response);
    }

    // 아바타 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody AvatarUpdateDTO avatarUpdateDTO) {
        var avatar = avatarService.update(avatarUpdateDTO);
        var response = CommonResponse.builder().code(200).message("아바타 수정 성공").data(avatar).build();
        return ResponseEntity.ok(response);
    }

    // 아바타 삭제
    @DeleteMapping("/{avatarId}")
    public ResponseEntity delete(@PathVariable Long avatarId) {
        var avatar = avatarService.delete(avatarId);
        var response = CommonResponse.builder().code(200).message("아바타 삭제 성공").data(avatar).build();
        return ResponseEntity.ok(response);
    }

    // 아바타 조회
    @GetMapping("/{avatarId}")
    public ResponseEntity get(@PathVariable Long avatarId) {
        var avatar = avatarService.getAvatar(avatarId);
        var response = CommonResponse.builder().code(200).message("아바타 조회 성공").data(avatar).build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/expUp/{avatarId}/{exp}}")
    public ResponseEntity expUp(@PathVariable Long avatarId, @PathVariable Integer exp) {
        var avatar = avatarService.expUp(avatarId, exp);
        var response = CommonResponse.builder().code(200).message("아바타 경험치 획득 성공").data(avatar).build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/eat/{avatarId}")
    public ResponseEntity eat(@PathVariable Long avatarId) {
        var avatar = avatarService.eat(avatarId);
        var response = CommonResponse.builder().code(200).message("아바타 식사 성공").data(avatar).build();
        return ResponseEntity.ok(response);
    }
}
