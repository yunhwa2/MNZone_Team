package com.mn.yunhwa.dto;

import com.mn.entity.Member;
import com.mn.entity.Missing;
import com.mn.entity.MissingComment;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class MissingCommentDTO {

    private Long missingCommentId;
    private Missing missing;
    private String missingComment;
    private Member member;
    private Long memberCode;

    private static ModelMapper modelMapper = new ModelMapper();

    public MissingComment createMissingComment(){
        MissingComment missingComment = modelMapper.map(this, MissingComment.class);

        Member member = new Member();
        member.setCode(this.getMemberCode());
        missingComment.setMember(member);

        return missingComment;
    }


}
