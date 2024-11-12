package com.project.api.memo;

import com.project.api.memo.dto.MemoCreateDTO;
import com.project.api.memo.dto.MemoUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/memo")
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    // 메모 생성
    @PostMapping
    public ResponseEntity create(@RequestBody MemoCreateDTO memoCreateDTO) {
        var memo = memoService.create(memoCreateDTO);
        var response = CommonResponse.builder().code(200).message("메모 생성 성공").data(memo).build();
        return ResponseEntity.ok(response);
    }

    // 메모 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody MemoUpdateDTO memoUpdateDTO) {
        var memo = memoService.update(memoUpdateDTO);
        var response = CommonResponse.builder().code(200).message("메모 수정 성공").data(memo).build();
        return ResponseEntity.ok(response);
    }

    // 메모 삭제
    @DeleteMapping("/{memoId}")
    public ResponseEntity delete(@PathVariable Long memoId) {
        var memo = memoService.delete(memoId);
        var response = CommonResponse.builder().code(200).message("메모 삭제 성공").data(memo).build();
        return ResponseEntity.ok(response);
    }

    // 메모 조회
    @GetMapping("/{memoId}")
    public ResponseEntity get(@PathVariable Long memoId) {
        var memo = memoService.getMemo(memoId);
        var response = CommonResponse.builder().code(200).message("메모 조회 성공").data(memo).build();
        return ResponseEntity.ok(response);
    }
}
