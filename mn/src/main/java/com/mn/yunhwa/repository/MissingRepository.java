package com.mn.yunhwa.repository;

import com.mn.entity.Missing;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MissingRepository extends JpaRepository<Missing, Long> , MissingRepositoryCustom{

    Optional<Missing> findByMissingId(Long missingId);


    List<Missing> findByMissingTitle(String missingTitle);

    List<Missing> findByMissingContent (String missingContent);

    List<Missing> findByMissingTitleOrMissingContent (String missingTitle, String missingContent);

    List<Missing> findByRegTimeLessThanOrderByRegTimeDesc (LocalDateTime regTime);

    @Transactional
    void deleteByMissingId(Long missingId);


}
