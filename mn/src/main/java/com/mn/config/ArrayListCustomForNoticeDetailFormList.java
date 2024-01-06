package com.mn.config;

import com.mn.notice.dto.NoticeDetailPrevNextDTO;

import java.util.ArrayList;

public class ArrayListCustomForNoticeDetailFormList<E> extends ArrayList {

    @Override
    public int indexOf(Object o) {

        for(int i = 0 ; i < this.size() ; i++ ){
            NoticeDetailPrevNextDTO noticeDetailPrevNextDTO = (NoticeDetailPrevNextDTO) super.get(i);
            if(noticeDetailPrevNextDTO.getNoticeId().equals(o)){
                return super.indexOf(noticeDetailPrevNextDTO);
            }
        }

        return super.indexOf(null);
    }
}
