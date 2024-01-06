package com.mn.notice.repository;

import com.mn.entity.Notice;
import com.mn.notice.dto.NoticeDetailPrevNextDTO;
import com.mn.notice.dto.NoticeListFormDTO;
import com.mn.notice.dto.NoticeSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeRepositoryCustom {
//    Page<Notice> getNoticePage(NoticeFormDTO noticeFormDTO, Pageable pageable);

    Page<NoticeListFormDTO> getNoticePage(NoticeSearchDTO noticeSearchDTO, Pageable pageable);
    Page<Notice> getNoticePage2(NoticeSearchDTO noticeSearchDTO, Pageable pageable);
    List<NoticeDetailPrevNextDTO> getNoticeDetailPrevNextDTOList(NoticeSearchDTO noticeSearchDTO);
}
