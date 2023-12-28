package com.mn.peter.repository;

import com.mn.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice,Long>, NoticeRepositoryCustom{
    Notice findByNoticeId(Long id);

}
