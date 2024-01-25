package com.mn.entity;

import com.mn.yunhwa.dto.MissingFormDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="missing_comment")
@Getter @Setter
public class MissingComment extends BaseEntity{

    @Id
    @Column(name="missing_comment_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missingCommentId;

    @ManyToOne
    @JoinColumn(name = "missing_code")
    private Missing missing;

    private String missingComment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "member_code")
    private Member member;

    public MissingComment(){}

    public MissingComment(String missingComment) {
        this.missingComment = missingComment;
    }

    public void updateComment(String missingComment) {
        this.missingComment = missingComment;
    }
}
