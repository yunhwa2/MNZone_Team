package com.mn.yunhwa.service;

import com.mn.entity.Missing;
import com.mn.entity.MissingImg;
import com.mn.yunhwa.dto.MissingFormDTO;
import com.mn.yunhwa.dto.MissingImgDTO;
import com.mn.yunhwa.dto.MissingSearchDTO;
import com.mn.yunhwa.repository.MissingImgRepository;
import com.mn.yunhwa.repository.MissingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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
            if (i == 0)
                missingImg.setMissingRepImgYn("Y");
             else
                missingImg.setMissingRepImgYn("N");

            missingImgService.saveMissingImg(missingImg, missingImgFileList.get(i));
        }
            return missing.getMissingId();
    }

    public Long updateMissing(MissingFormDTO missingFormDTO, List<MultipartFile> missingImgFileList) throws Exception{
        Missing missing =missingRepository.findById(missingFormDTO.getMissingId()).orElseThrow(EntityNotFoundException::new);
        missing.updateMissing(missingFormDTO);

        List<Long> missingImgIds = missingFormDTO.getMissingImgIds();

        for(int i=0;i<missingImgFileList.size();i++){
            missingImgService.updateMissingImg(missingImgIds.get(i),missingImgFileList.get(i));
        }
        return missing.getMissingId();
    }

    @Transactional(readOnly = true)
    public MissingFormDTO getMissingDtl(Long missingId){
        List<MissingImg> missingImgList = missingImgRepository.findByMissingMissingIdOrderByMissingImgIdAsc(missingId);
        List<MissingImgDTO> missingImgDTOList = new ArrayList<>();

        for(MissingImg missingImg : missingImgList){
            MissingImgDTO missingImgDTO = MissingImgDTO.of(missingImg);
            missingImgDTOList.add(missingImgDTO);
        }

        Missing missing = missingRepository.findById(missingId).orElseThrow(EntityNotFoundException::new);
        MissingFormDTO missingFormDTO = MissingFormDTO.of(missing);
        missingFormDTO.setMissingImgDTOList(missingImgDTOList);
        return missingFormDTO;
    }


    @Transactional(readOnly = true)
    public Page<Missing> getMissingPage(MissingSearchDTO missingSearchDTO, Pageable pageable){
        return missingRepository.getMissingPage(missingSearchDTO,pageable);
    }

}
