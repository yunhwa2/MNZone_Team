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

//    private Member member;

    @Column(columnDefinition="default 'DISAPPEAR' not null")
    private MissingKind missingKind;

    private List<MissingImgDTO> missingImgDTOList = new ArrayList<>();

    private List<Long> missingImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Missing createMissing(){
        return modelMapper.map(this,Missing.class);
    }

    public static MissingFormDTO of(Missing missing){
        return modelMapper.map(missing,MissingFormDTO.class);
    }
}
