package com.mn.yunhwa.service;

import com.mn.entity.Missing;
import com.mn.entity.MissingImg;
import com.mn.yunhwa.dto.MissingFormDTO;
import com.mn.yunhwa.repository.MissingImgRepository;
import com.mn.yunhwa.repository.MissingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MissingService {
    private final MissingRepository missingRepository;
    private final MissingImgRepository missingImgRepository;
    private final MissingImgService missingImgService;

    public Long saveMissing(MissingFormDTO missingFormDTO, List<MultipartFile> missingImgFileList) throws Exception {
        Missing missing = missingFormDTO.createMissing();
        missingRepository.save(missing);

        for (int i = 0; i < missingImgFileList.size(); i++) {
            MissingImg missingImg = new MissingImg();
            missingImg.setMissing(missing);
            if (i == 0) {
                missingImg.setMissingRepImgYn("Y");
            } else {
                missingImg.setMissingRepImgYn("N");
            }
            missingImgService.saveMissingImg(missingImg, missingImgFileList.get(i));
        }
            return missing.getMissingId();
    }
}
