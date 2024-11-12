package com.project.api.notice;

import com.project.api.bookmark.BookmarkService;
import com.project.api.user.UserService;
import com.project.entity.Notice;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.exception.error_code.NoticeErrorCode.NOTICE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final UserService userService;
    private final BookmarkService bookmarkService;
    private final NoticeRepository noticeRepository;

    public Notice create(NoticeCreateDTO noticeCreateDTO) {
        var user = userService.getUser(noticeCreateDTO.getUserId());
        var bookmark = bookmarkService.getBookmark(noticeCreateDTO.getBookmarkId());

        var notice = Notice.builder()
                .user(user)
                .bookmark(bookmark)
                .message(noticeCreateDTO.getMessage())
                .build();

        noticeRepository.save(notice);
        return notice;
    }

    public Notice delete(Long noticeId) {
        var notice = noticeRepository.findByNoticeId(noticeId)
                .orElseThrow(() -> new ControlledException(NOTICE_NOT_FOUND));

        noticeRepository.deleteByNoticeId(noticeId);
        return notice;
    }

    public Notice getNotice(Long noticeId) {
        var notice = noticeRepository.findByNoticeId(noticeId)
                .orElseThrow(() -> new ControlledException(NOTICE_NOT_FOUND));

        return notice;
    }
}
