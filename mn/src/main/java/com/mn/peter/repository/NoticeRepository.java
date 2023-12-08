package com.mn.peter.repository;

import com.mn.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long>, NoticeRepositoryCustom{
    Notice findByNoticeId(Long id);
}
