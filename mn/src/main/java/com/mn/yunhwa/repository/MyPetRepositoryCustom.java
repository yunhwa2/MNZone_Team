package com.mn.yunhwa.repository;

import com.mn.yunhwa.dto.MyPetMainDTO;
import com.mn.yunhwa.dto.MyPetSearchDTO;

import java.util.List;

public interface MyPetRepositoryCustom {

    List<MyPetMainDTO> getAllMyPets(MyPetSearchDTO myPetSearchDTO, Long memberCode);
}
