package com.project.api.bookmark;

import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    // 북마크 생성
    @PostMapping
    public ResponseEntity create(@RequestBody BookmarkCreateDTO bookmarkCreateDTO) {
        var bookmark = bookmarkService.create(bookmarkCreateDTO);
        var response = CommonResponse.builder().code(200).message("북마크 생성 성공").data(bookmark).build();
        return ResponseEntity.ok(response);
    }

    // 북마크 삭제
    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity delete(@PathVariable Long bookmarkId) {
        var bookmark = bookmarkService.delete(bookmarkId);
        var response = CommonResponse.builder().code(200).message("북마크 삭제 성공").data(bookmark).build();
        return ResponseEntity.ok(response);
    }

    // 북마크 조회
    @GetMapping("/{bookmarkId}")
    public ResponseEntity get(@PathVariable Long bookmarkId) {
        var bookmark = bookmarkService.getBookmark(bookmarkId);
        var response = CommonResponse.builder().code(200).message("북마크 조회 성공").data(bookmark).build();
        return ResponseEntity.ok(response);
    }
}
