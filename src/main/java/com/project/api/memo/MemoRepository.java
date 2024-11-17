package com.project.api.memo;

import com.project.entity.Memo;
import com.project.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    Optional<Memo> findByMemoId(Long memoId);

    void deleteByMemoId(Long memoId);

    Optional<List<Memo>> findByUser(User user);
}
