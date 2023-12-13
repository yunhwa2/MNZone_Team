package com.mn.yunhwa.repository;

import com.mn.entity.MissingImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissingImgRepository extends JpaRepository<MissingImg, Long> {
    List<MissingImg> findByMissingIdOrderByAsc(Long missingId);

}
