package com.mn.entity;


import com.mn.constant.MissingKind;
import com.mn.yunhwa.dto.MissingFormDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="missing")
@Getter
@Setter
public class Missing extends BaseEntity{

    @Id
    @Column(name = "missing_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missingId;

    @Column(nullable = false)
    private String missingTitle;

    @Column(nullable = false)
    private String sightingSpot;

    @Column(nullable = false)
    private String sightingDate;

    @Column(nullable = false)
    private String witnessTel;

    @Column(nullable = false)
    private String feature;

    @Lob
    @Column(nullable = false)
    private String missingContent;

    @Enumerated(EnumType.STRING)
    private MissingKind missingKind;

    private String missingRepImg;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "member_code")
    private Member member;

//    @OneToMany(mappedBy = "missing", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<MissingImg> missingImgs;

    public void updateMissing(MissingFormDTO missingFormDTO){
        this.missingTitle = missingFormDTO.getMissingTitle();
        this.missingContent = missingFormDTO.getMissingContent();
        this.missingKind = missingFormDTO.getMissingKind();
        this.sightingSpot = missingFormDTO.getSightingSpot();
        this.sightingDate = missingFormDTO.getSightingDate();
        this.witnessTel = missingFormDTO.getWitnessTel();
        this.feature = missingFormDTO.getFeature();
        this.missingRepImg=missingFormDTO.getMissingRepImg();
    }


}
