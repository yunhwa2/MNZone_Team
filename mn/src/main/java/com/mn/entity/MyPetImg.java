package com.mn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="my_pet_img")
@Getter @Setter
@ToString
public class MyPetImg extends BaseEntity{
    @Id
    @Column(name="my_pet_img_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long myPetImgId;

    private String myPetImgName;
    private String myPetOriImgName;
    private String myPetImgUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_pet_code")
    private MyPet myPet;

    public void updateMyPetImg(String myPetImgName, String myPetOriImgName, String myPetImgUrl){
        this.myPetImgName = myPetImgName;
        this.myPetOriImgName = myPetOriImgName;
        this.myPetImgUrl = myPetImgUrl;

    }




}
