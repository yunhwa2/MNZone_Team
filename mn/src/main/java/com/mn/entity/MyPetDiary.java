package com.mn.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="my_pet_diary")
@Getter
@Setter
@ToString
public class MyPetDiary extends BaseEntity{

    @Id
    @Column(name="my_pet_diary_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long myPetDiaryId;

    @ManyToOne
    @JoinColumn(name = "my_pet_code")
    private MyPet myPet;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "member_code")
    private Member member;

    private String myPetTitle;
    private String myPetStart;
    private String myPetEnd;
    private String myPetContent;

    public MyPetDiary(){}

    public void updateMyPetDiary(String myPetTitle,String myPetStart, String myPetEnd,String myPetContent) {
        this.myPetTitle = myPetTitle;
        this.myPetStart = myPetStart;
        this.myPetEnd = myPetEnd;
        this.myPetContent = myPetContent;
    }
}
