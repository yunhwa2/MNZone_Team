package com.mn.yunhwa.repository;

import com.mn.entity.MissingComment;
import com.mn.entity.MyPetDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPetDiaryRepository extends JpaRepository<MyPetDiary, Long> , MyPetDiaryRepositoryCustom{

    List<MyPetDiary> findByMyPetMyPetId(Long myPetId);
}
