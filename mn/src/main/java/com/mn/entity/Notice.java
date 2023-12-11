package com.mn.entity;

import com.mn.constant.NoticeKind;
import com.mn.constant.NoticeStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "notice")
@Entity
@Getter
@Setter
@ToString
public class Notice extends BaseEntity{

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long noticeId;

    private String noticeTitle;
    private String noticeContent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code")
    private Member member;
    private NoticeKind noticeKind;
    private NoticeStatus noticeStatus;
    private Integer noticeVisitCount;


}
