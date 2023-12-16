package com.mn.yunhwa.repository;

import com.mn.entity.Missing;
import com.mn.yunhwa.dto.MissingSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissingRepository extends JpaRepository<Missing, Long> , MissingRepositoryCustom{


}
