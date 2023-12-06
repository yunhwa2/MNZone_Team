package com.mn.entity;

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
    @Column(name = "member_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long noticeId;

    private String noticeTitle;
    private String noticeContent;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
