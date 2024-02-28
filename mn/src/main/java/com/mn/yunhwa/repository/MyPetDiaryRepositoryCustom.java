package com.mn.yunhwa.repository;

import com.mn.entity.MissingComment;
import com.mn.entity.MyPetDiary;

import java.util.List;

public interface MyPetDiaryRepositoryCustom {

    List<MyPetDiary> getMyPetMyPetId (Long myPetId);
}
