package com.mn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="missing_img")
@Getter @Setter
@ToString
public class MissingImg extends BaseEntity{

    @Id
    @Column(name = "missing_img_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missingImgId;

    private String missingImgName;
    private String missingOriImgName;
    private String missingImgUrl;
    private String missingRepImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "missing_code")
    private Missing missing;

    public void updateMissingImg(String missingImgName, String missingOriImgName, String missingImgUrl) {
        this.missingImgName = missingImgName;
        this.missingOriImgName = missingOriImgName;
        this.missingImgUrl = missingImgUrl;
    }
}
