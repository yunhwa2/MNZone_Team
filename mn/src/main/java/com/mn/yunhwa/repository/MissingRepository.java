package com.mn.yunhwa.repository;

import com.mn.entity.Missing;
import com.mn.yunhwa.dto.MissingSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface MissingRepository extends JpaRepository<Missing, Long> , MissingRepositoryCustom{

    List<Missing> findByMissingTitle(String missingTitle);

    List<Missing> findByMissingContent (String missingContent);

    List<Missing> findByMissingTitleOrMissingContent (String missingTitle, String missingContent);

    List<Missing> findByRegTimeLessThanOrderByRegTimeDesc (LocalDateTime regTime);

    @Transactional
    void deleteByMissingId(Long missingId);

//    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
//    List<Missing> findByItemDetail (@Param("itemDetail") String itemDetail);
//
//    @Query(value = "select * from Item i where i.item_Detail like %:itemDetail% order by i.price desc", nativeQuery = true)
//    List<Missing> findByItemDetailByNative (@Param("itemDetail") String itemDetail);


}
