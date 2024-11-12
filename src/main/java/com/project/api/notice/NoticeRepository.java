package com.project.api.notice;

import com.project.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Optional<Notice> findByNoticeId(Long noticeId);

    void deleteByNoticeId(Long noticeId);
}
