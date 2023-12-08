package com.mn.peter.service;

import com.mn.constant.NoticeStatus;
import com.mn.entity.Notice;
import com.mn.peter.dto.NoticeFormDTO;
import com.mn.peter.dto.NoticeListFormDTO;
import com.mn.peter.dto.NoticeSearchDTO;
import com.mn.peter.repository.NoticeRepository;
import com.mn.seoha.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    public Notice saveNotice(NoticeFormDTO noticeFormDTO){
        System.err.println("noticeService.saveNotice 진입");
        System.err.println("noteiceFormDTO.gettitle : " + noticeFormDTO.getNoticeTitle());
        Notice notice = new Notice();
        notice.setNoticeContent(noticeFormDTO.getNoticeContent());
        notice.setNoticeTitle(noticeFormDTO.getNoticeTitle());
        notice.setMember(memberRepository.findById(noticeFormDTO.getNoticeAuthor()));
        notice.setNoticeKind(noticeFormDTO.getNoticeKind());
        notice.setNoticeStatus(NoticeStatus.NORMAL);
        notice.setMember(memberRepository.findById(noticeFormDTO.getNoticeAuthor()));
        System.out.println("notice.getTitle : " +notice.getNoticeTitle());
        System.out.println("notice.content : " +notice.getNoticeContent());

        return noticeRepository.save(notice);
    }
    @Transactional(readOnly = true)
    public Page<NoticeListFormDTO> getNoticePage(NoticeSearchDTO noticeSearchDTO,
                                                 Pageable pageable){
        System.err.println("NoticeService.getNoticePage");
        return noticeRepository.getNoticePage(noticeSearchDTO,pageable);

    }
    @Transactional(readOnly = true)
    public Page<Notice> getNoticePage2(NoticeSearchDTO noticeSearchDTO,
                                                 Pageable pageable){
        System.err.println("NoticeService.getNoticePage");
        return noticeRepository.getNoticePage2(noticeSearchDTO,pageable);

    }


}
