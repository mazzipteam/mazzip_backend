package com.project.api.notice;

import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    // 알림 생성
    @PostMapping
    public ResponseEntity create(@RequestBody NoticeCreateDTO noticeCreateDTO) {
        var notice = noticeService.create(noticeCreateDTO);
        var response = CommonResponse.builder().code(200).message("알림 생성 성공").data(notice).build();
        return ResponseEntity.ok(response);
    }

    // 알림 삭제
    @DeleteMapping("/{noticeId}")
    public ResponseEntity delete(@PathVariable Long noticeId) {
        var notice = noticeService.delete(noticeId);
        var response = CommonResponse.builder().code(200).message("알림 삭제 성공").data(notice).build();
        return ResponseEntity.ok(response);
    }

    // 알림 조회
    @GetMapping("/{noticeId}")
    public ResponseEntity get(@PathVariable Long noticeId) {
        var notice = noticeService.getNotice(noticeId);
        var response = CommonResponse.builder().code(200).message("알림 조회 성공").data(notice).build();
        return ResponseEntity.ok(response);
    }
}
