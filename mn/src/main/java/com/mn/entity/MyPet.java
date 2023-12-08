package com.mn.entity;

import com.mn.constant.MyPetCategory;
import com.mn.constant.MyPetGender;
import com.mn.constant.MyPetNeuter;
import com.mn.seoha.dto.MemberFormDTO;
import com.mn.yunhwa.dto.MyPetFormDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="myPet")
@Getter @Setter
@ToString
public class MyPet {

    @Id
    @Column(name="pet_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long petCode;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MyPetCategory myPetCategory;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private String weight;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    @Enumerated(EnumType.STRING)
    private MyPetGender myPetGender;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    @Enumerated(EnumType.STRING)
    private MyPetNeuter myPetNeuter;

    @Column(nullable = false)
    private String kind;


    @JoinColumn(name = "member_code",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public static MyPet createMyPet(MyPetFormDTO myPetFormDTO, MemberFormDTO memberFormDTO){
        MyPet myPet = new MyPet();
        myPet.setMyPetCategory(myPetFormDTO.getMyPetCategory());
        myPet.setName(myPetFormDTO.getName());
        myPet.setBirth(myPetFormDTO.getBirth().toLocalDate());
        myPet.setWeight(myPetFormDTO.getWeight());
        myPet.setMyPetGender(myPetFormDTO.getMyPetGender());
        myPet.setMyPetNeuter(myPetFormDTO.getMyPetNeuter());

        return myPet;
    }
}
