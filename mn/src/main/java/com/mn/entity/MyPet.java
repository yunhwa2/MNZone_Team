package com.mn.entity;

import com.mn.constant.MyPetCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="mypet")
@Getter @Setter
@ToString
public class MyPet {

    @Id
    @Column(name="pet_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long petCode;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private MyPetCategory myPetCategory;

    @Column(nullable = false)
    private LocalDateTime birth;

    @Column(nullable = false)
    private String weight;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    private boolean gender;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    private boolean neuterIsYn;

    @Column(nullable = false)
    private String kind;

    @JoinColumn(name = "member_code")
    @ManyToOne
    private Member member;
}
