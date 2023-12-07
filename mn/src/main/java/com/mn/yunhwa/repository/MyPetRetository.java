package com.mn.yunhwa.repository;

import com.mn.entity.MyPet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPetRetository extends JpaRepository<MyPet, Long> {
    List<MyPet> findByPetName(String name);
}

