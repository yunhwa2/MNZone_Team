package com.mn.peter.repository;

import com.mn.entity.Notice;
import com.mn.peter.dto.NoticeFormDTO;
import com.mn.peter.dto.NoticeListFormDTO;
import com.mn.peter.dto.NoticeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {
//    Page<Notice> getNoticePage(NoticeFormDTO noticeFormDTO, Pageable pageable);

    Page<NoticeListFormDTO> getNoticePage(NoticeSearchDTO noticeSearchDTO, Pageable pageable);
    Page<Notice> getNoticePage2(NoticeSearchDTO noticeSearchDTO, Pageable pageable);
}
