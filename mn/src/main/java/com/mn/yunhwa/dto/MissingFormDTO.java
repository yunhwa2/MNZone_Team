package com.mn.yunhwa.dto;


import com.mn.constant.MissingKind;
import com.mn.entity.Member;
import com.mn.entity.Missing;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;


import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class MissingFormDTO {

    private Long missingId;

    @NotBlank(message = "제목을 입력해주세요.")
    private String missingTitle;

    @NotBlank(message = "내용을 입력해주세요.")
    private String missingContent;

    private Long memberCode;

    private Member member;

    @Column(columnDefinition="default 'DISAPPEAR' not null")
    private MissingKind missingKind;

    private List<MissingImgDTO> missingImgDTOList = new ArrayList<>();

    private List<Long> missingImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Missing createMissing(){
        Missing missing = modelMapper.map(this, Missing.class);
        // Member 객체를 가져와서 Missing에 설정
        Member member = new Member();
        member.setCode(this.getMemberCode());
        missing.setMember(member);

        return missing;
    }

    public static MissingFormDTO of(Missing missing){
        MissingFormDTO missingFormDTO = modelMapper.map(missing, MissingFormDTO.class);
        missingFormDTO.setMemberCode(missing.getMember().getCode());
        return missingFormDTO;
    }
}
