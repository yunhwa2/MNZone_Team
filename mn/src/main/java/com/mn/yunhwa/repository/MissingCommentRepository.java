package com.mn.yunhwa.repository;

import com.mn.entity.Missing;
import com.mn.entity.MissingComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissingCommentRepository extends JpaRepository<MissingComment,Long> {
    List<MissingComment> findByMissingMissingId(Long missingId);
}
