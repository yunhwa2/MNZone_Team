package com.mn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "hospital_img")
@Getter@Setter
@ToString
public class HospitalImg {
    @Id
    @Column(name = "hospital_img_id")
    @GeneratedValue
    private Long hospitalImgId;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_name")
    private Hospital hospital;

    public void updateHospitalImg(String oriImgName,String imgName, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }

}
