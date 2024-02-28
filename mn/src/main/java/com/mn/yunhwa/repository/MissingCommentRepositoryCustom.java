package com.mn.yunhwa.repository;

import com.mn.entity.Missing;
import com.mn.entity.MissingComment;
import com.mn.yunhwa.dto.MissingSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MissingCommentRepositoryCustom {

    List<MissingComment> getMissingMissingId(Long missingId);
}
