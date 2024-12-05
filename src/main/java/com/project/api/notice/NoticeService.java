package com.project.api.notice;

import com.project.api.bookmark.BookmarkService;
import com.project.api.user.UserService;
import com.project.entity.Notice;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.project.exception.error_code.NoticeErrorCode.NOTICE_NOT_FOUND;
import static com.project.exception.error_code.NoticeErrorCode.NOTICE_NOT_FOUND_BY_USER;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final UserService userService;
    private final NoticeRepository noticeRepository;
    private final BookmarkService bookmarkService;

    public Notice create(NoticeCreateDTO noticeCreateDTO) {
        var notice = Notice.builder()
                .bookmark(noticeCreateDTO.getBookmark())
                .message(noticeCreateDTO.getMessage())
                .build();

        noticeRepository.save(notice);
        return notice;
    }

    public Notice delete(Long noticeId) {
        var notice = getNotice(noticeId);

        noticeRepository.deleteByNoticeId(noticeId);
        return notice;
    }

    public Notice getNotice(Long noticeId) {
        var notice = noticeRepository.findByNoticeId(noticeId)
                .orElseThrow(() -> new ControlledException(NOTICE_NOT_FOUND));

        return notice;
    }

    public List<Notice> getAllNotice(Long userId) {
        var bookmarks = bookmarkService.getAllBookmarkByUser(userId);

        var notices = new ArrayList<Notice>();
        for (var bookmark : bookmarks) {
            var noticeList = noticeRepository.findByUser(bookmark.getUser())
                    .orElseThrow(()-> new ControlledException(NOTICE_NOT_FOUND));
            notices.addAll(noticeList);
        }

        return notices;
    }
}
