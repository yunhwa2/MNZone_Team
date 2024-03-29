package com.mn.entity;

import com.mn.constant.MyPetCategory;
import com.mn.constant.MyPetGender;
import com.mn.constant.MyPetNeuter;
import com.mn.yunhwa.dto.MyPetFormDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="my_pet")
@Getter @Setter
@ToString
public class MyPet {

    @Id
    @Column(name="my_pet_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long myPetId;

    @Column(nullable = false, length = 50)
    private String myPetName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MyPetCategory myPetCategory;

    @Column(nullable = false)
    private LocalDate myPetBirth;

    @Column(nullable = false)
    private String myPetWeight;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    @Enumerated(EnumType.STRING)
    private MyPetGender myPetGender;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    @Enumerated(EnumType.STRING)
    private MyPetNeuter myPetNeuter;

    @Column(nullable = false)
    private String myPetKind;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "member_code")
    private Member member;

    @Column
    private String myPetImgUrl;



    public static MyPet updateMyPet(MyPetFormDTO myPetFormDTO){
        MyPet myPet = new MyPet();
        myPet.setMyPetCategory(myPetFormDTO.getMyPetCategory());
        myPet.setMyPetName(myPetFormDTO.getMyPetName());
        myPet.setMyPetBirth(myPetFormDTO.getMyPetBirth());
        myPet.setMyPetWeight(myPetFormDTO.getMyPetWeight());
        myPet.setMyPetGender(myPetFormDTO.getMyPetGender());
        myPet.setMyPetNeuter(myPetFormDTO.getMyPetNeuter());
        myPet.setMyPetImgUrl(myPetFormDTO.getMyPetImgUrl());
        myPet.setMyPetKind(myPetFormDTO.getMyPetKind());

        return myPet;
    }
}
