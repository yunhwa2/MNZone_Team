package com.mn.yunhwa.repository;

import com.mn.entity.MyPet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPetRetository extends JpaRepository<MyPet, Long> {
    MyPet findByPetCode(Long petCode);
}
