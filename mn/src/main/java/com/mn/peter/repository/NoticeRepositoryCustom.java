package com.mn.peter.repository;

import com.mn.entity.Notice;
import com.mn.peter.dto.NoticeFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {
    Page<Notice> getNoticePage(NoticeFormDTO noticeFormDTO, Pageable pageable);
}
