package com.mn.Service;

import com.mn.constant.MissingKind;
import com.mn.entity.Missing;
import com.mn.entity.MissingImg;
import com.mn.yunhwa.dto.MissingFormDTO;
import com.mn.yunhwa.repository.MissingImgRepository;
import com.mn.yunhwa.repository.MissingRepository;
import com.mn.yunhwa.service.MissingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class MissingServiceTest {

    @Autowired
    MissingService missingService;

    @Autowired
    MissingRepository missingRepository;

    @Autowired
    MissingImgRepository missingImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception{
        List<MultipartFile> multipartFileList = new ArrayList<>();

        for(int i=0; i<5;i++){
            String path = "D:/MN/missing";
            String imageName = "image" + i +".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName,"image/jpg", new byte[] {1,2,3,4});
            multipartFileList.add(multipartFile);
        }

        return multipartFileList;
    }

    @Test
    @DisplayName("멍냥실종 글 등록 테스트")
    @WithMockUser(username = "test1", roles = "USER")
    void saveMissing() throws Exception{
        MissingFormDTO missingFormDTO = new MissingFormDTO();
        missingFormDTO.setMissingTitle("테스트 글 제목1");
        missingFormDTO.setMissingContent("테스트중~");
        missingFormDTO.setMissingKind(MissingKind.DISAPPEAR);

        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long missingId = missingService.saveMissing(missingFormDTO,multipartFileList);

        List<MissingImg> missingImgList = missingImgRepository.findByMissingMissingIdOrderByMissingImgIdAsc(missingId);
        Missing missing = missingRepository.findById(missingId).orElseThrow(EntityNotFoundException::new);

        assertEquals(missingFormDTO.getMissingTitle(),missing.getMissingTitle());
        assertEquals(missingFormDTO.getMissingContent(),missing.getMissingContent());
        assertEquals(missingFormDTO.getMissingKind(),missing.getMissingKind());
        assertEquals(multipartFileList.get(0).getOriginalFilename(),missingImgList.get(0).getMissingOriImgName());


    }
}
