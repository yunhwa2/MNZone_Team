package com.mn.entity;

import com.mn.constant.MissingKind;
import com.mn.yunhwa.dto.MissingFormDTO;
import com.mn.yunhwa.dto.MissingImgDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="missing")
@Getter @Setter
public class Missing extends BaseEntity{

    @Id
    @Column(name = "missing_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missingId;

    @Column(nullable = false)
    private String missingTitle;

    @Lob
    @Column(nullable = false)
    private String missingContent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_code")
    private Member member;

    @Enumerated(EnumType.STRING)
    private MissingKind missingKind;

    @OneToMany(mappedBy = "missing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissingImg> missingImgs;

    public void updateMissing(MissingFormDTO missingFormDTO){
        this.missingTitle = missingFormDTO.getMissingTitle();
        this.missingContent = missingFormDTO.getMissingContent();
        this.missingKind = missingFormDTO.getMissingKind();
    }


}
