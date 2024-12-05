package com.project.api.notice;

import com.project.entity.Bookmark;
import com.project.entity.Notice;
import com.project.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Optional<Notice> findByNoticeId(Long noticeId);

    void deleteByNoticeId(Long noticeId);

    Optional<List<Notice>> findByUser(User user);

    Optional<List<Notice>> findByBookmark(Bookmark bookmark);
}
