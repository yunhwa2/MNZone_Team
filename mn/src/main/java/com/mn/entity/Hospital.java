package com.mn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "hospital")
@Getter@Setter
@ToString
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hospital_code")
    private Long hospitalCode;
    @Column(nullable = false)
    private String hospitalName;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String tel;
    @Column(nullable = false)
    private String hospitalAddress;

}
