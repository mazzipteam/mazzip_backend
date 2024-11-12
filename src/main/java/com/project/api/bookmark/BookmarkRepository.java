package com.project.api.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    Optional<Bookmark> findByBookmarkId(Long bookmarkId);

    void deleteByBookmarkId(Long bookmarkId);
}
