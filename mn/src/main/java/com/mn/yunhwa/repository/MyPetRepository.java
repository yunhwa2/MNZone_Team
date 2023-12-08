package com.mn.yunhwa.repository;

import com.mn.entity.Member;
import com.mn.entity.MyPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MyPetRepository extends JpaRepository<MyPet, Long> {

    List<MyPet> findByMember(Member member);

    @Query(value = "SELECT * FROM my_pet pet , member m "
            + " WHERE m.member_code = :member_code  AND pet.member_code = m.member_code"
            + " ORDER BY pet.my_pet_name", nativeQuery = true)
    List<MyPet> findByMyPetByNative(@Param("member_code")Member member);

}

