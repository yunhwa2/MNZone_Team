package com.mn.yunhwa.repository;

import com.mn.entity.Member;
import com.mn.entity.MyPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MyPetRepository extends JpaRepository<MyPet, Long> ,MyPetRepositoryCustom{

}

