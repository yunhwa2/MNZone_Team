package com.mn.notice.service;

import com.mn.constant.NoticeStatus;
import com.mn.entity.Notice;
import com.mn.notice.dto.*;
import com.mn.notice.repository.NoticeRepository;
import com.mn.seoha.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;
    private static TreeMap<Long,NoticeDetailPrevNextDTO> noticePrevNextMap = new TreeMap<>();

    public Notice saveNotice(NoticeFormDTO noticeFormDTO){
        Notice notice = new Notice();
        notice.setNoticeContent(noticeFormDTO.getNoticeContent());
        notice.setNoticeTitle(noticeFormDTO.getNoticeTitle());
        notice.setMember(memberRepository.findById(noticeFormDTO.getNoticeAuthor()));
        notice.setNoticeKind(noticeFormDTO.getNoticeKind());
        notice.setNoticeStatus(NoticeStatus.NORMAL);
        notice.setNoticeVisitCount(0);
        notice.setMember(memberRepository.findById(noticeFormDTO.getNoticeAuthor()));
        System.out.println("notice.getTitle : " +notice.getNoticeTitle());
        System.out.println("notice.content : " +notice.getNoticeContent());

        return noticeRepository.save(notice);
    }
    @Transactional
    public void plusVisitCount(Long id){
        Notice notice = noticeRepository.findByNoticeId(id);
        notice.setNoticeVisitCount(notice.getNoticeVisitCount()+1);
        noticeRepository.save(notice);
    }
    @Transactional(readOnly = true)
    public Page<NoticeListFormDTO> getNoticePage(NoticeSearchDTO noticeSearchDTO,
                                                 Pageable pageable){
        List<NoticeDetailPrevNextDTO> list = noticeRepository.getNoticeDetailPrevNextDTOList(noticeSearchDTO);
        for(NoticeDetailPrevNextDTO noticeDetailPrevNextDTO : list){
            noticePrevNextMap.put(noticeDetailPrevNextDTO.getNoticeId(),noticeDetailPrevNextDTO);
        }


        return noticeRepository.getNoticePage(noticeSearchDTO,pageable);

    }
    @Transactional(readOnly = true)
    public Page<Notice> getNoticePage2(NoticeSearchDTO noticeSearchDTO,
                                                 Pageable pageable){
        return noticeRepository.getNoticePage2(noticeSearchDTO,pageable);

    }

    public List methodForPrevNextFromDatabase(){
        List<NoticeDetailPrevNextDTO> list = new ArrayList<>(3);
        return list;
    }
    @Transactional(readOnly = true)
    public NoticeDetailFormDTO getNoticeDetail(Long id){
        NoticeDetailFormDTO noticeDetailFormDTO = new NoticeDetailFormDTO();
        int index = 0;

        try{
            Notice notice = noticeRepository.findByNoticeId(id);
            noticeDetailFormDTO.setNoticeId(notice.getNoticeId());
            noticeDetailFormDTO.setNoticeTitle(notice.getNoticeTitle());
            noticeDetailFormDTO.setNoticeVisitCount(notice.getNoticeVisitCount());
            noticeDetailFormDTO.setNoticeContent(notice.getNoticeContent());
            noticeDetailFormDTO.setNoticeKind(notice.getNoticeKind());
            noticeDetailFormDTO.setCreateBy(notice.getCreateBy());
            noticeDetailFormDTO.setNoticeRegTime(notice.getRegTime());

            if(!noticePrevNextMap.firstKey().equals(id)){
                noticeDetailFormDTO.setNextDTO(noticePrevNextMap.floorEntry(id-1).getValue());
            }
            if(!noticePrevNextMap.lastKey().equals(id)){
                noticeDetailFormDTO.setPrevDTO(noticePrevNextMap.higherEntry(id).getValue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return noticeDetailFormDTO;
    }
}
