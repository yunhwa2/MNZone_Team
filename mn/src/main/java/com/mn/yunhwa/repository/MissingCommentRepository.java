package com.mn.yunhwa.repository;

import com.mn.entity.Missing;
import com.mn.entity.MissingComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MissingCommentRepository extends JpaRepository<MissingComment,Long> ,MissingCommentRepositoryCustom{
    List<MissingComment> findByMissingMissingId(Long missingId);

    @Transactional
    void deleteByMissingCommentId(Long missingCommentId);
}
