package com.mn.entity;

import com.mn.constant.MissingKind;
import com.mn.yunhwa.dto.MissingFormDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="missing")
@Getter @Setter
public class Missing extends BaseEntity{

    @Id
    @Column(name = "missing_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missingId;

    @NotBlank
    private String missingTitle;

    @NotBlank
    private String missingContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code")
    private Member member;

    @Enumerated(EnumType.STRING)
    private MissingKind missingKind;

    public void updateMissing(MissingFormDTO missingFormDTO){
        this.missingTitle = missingFormDTO.getMissingTitle();
        this.missingContent = missingFormDTO.getMissingContent();
        this.missingKind = missingFormDTO.getMissingKind();
    }
}
