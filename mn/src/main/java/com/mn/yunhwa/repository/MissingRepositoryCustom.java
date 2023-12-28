package com.mn.yunhwa.repository;

import com.mn.entity.Missing;
import com.mn.yunhwa.dto.MissingMainDTO;
import com.mn.yunhwa.dto.MissingSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissingRepositoryCustom {
    Page<Missing> getMissingPage(MissingSearchDTO missingSearchDTO, Pageable pageable);

    Page<MissingMainDTO> getMissingMainPage(MissingSearchDTO missingSearchDTO, Pageable pageable);

}
