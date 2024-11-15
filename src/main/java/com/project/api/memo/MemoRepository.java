package com.project.api.memo;

import com.project.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    Optional<Memo> findByMemoId(Long memoId);

    void deleteByMemoId(Long memoId);
}
