package com.mn.yunhwa.service;

import com.mn.entity.Missing;
//import com.mn.entity.MissingImg;
import com.mn.entity.MissingComment;
import com.mn.yunhwa.dto.MissingCommentDTO;
import com.mn.yunhwa.dto.MissingFormDTO;
//import com.mn.yunhwa.dto.MissingImgDTO;
import com.mn.yunhwa.dto.MissingMainDTO;
import com.mn.yunhwa.dto.MissingSearchDTO;
//import com.mn.yunhwa.repository.MissingImgRepository;
import com.mn.yunhwa.repository.MissingCommentRepository;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class MissingService {
    private final MissingRepository missingRepository;
    private final MissingCommentRepository missingCommentRepository;

    public String saveMissing(MissingFormDTO missingFormDTO) throws Exception {
        Missing missing = missingFormDTO.createMissing();
        String input = missing.getMissingContent();
        Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String firstImgTag = matcher.group(1);
            System.out.println(firstImgTag);
            missing.setMissingRepImg(firstImgTag);
        }else{
            return "N";
        }

        missingRepository.save(missing);

            return "Y";
    }

    public String updateMissing(MissingFormDTO missingFormDTO) throws Exception{
        Missing missing =missingRepository.findById(missingFormDTO.getMissingId()).orElseThrow(EntityNotFoundException::new);
        String input = missingFormDTO.getMissingContent();
        Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String firstImgTag = matcher.group(1);
            System.out.println(firstImgTag);
            missingFormDTO.setMissingRepImg(firstImgTag);
        }else{
            return "N";
        }
        missing.updateMissing(missingFormDTO);


        return "Y";
    }

    public long countMissing() {
        return missingRepository.count();
    }

    @Transactional(readOnly = true)
    public MissingFormDTO getMissingDtl(Long missingId){
        Missing missing = missingRepository.findById(missingId).orElseThrow(EntityNotFoundException::new);
        MissingFormDTO missingFormDTO = MissingFormDTO.of(missing);
        return missingFormDTO;
    }


    @Transactional(readOnly = true)
    public Page<Missing> getMissingPage(MissingSearchDTO missingSearchDTO, Pageable pageable){
        return missingRepository.getMissingPage(missingSearchDTO,pageable);
    }

    @Transactional(readOnly = true)
    public Page<MissingMainDTO> getMissingMainPage(MissingSearchDTO missingSearchDTO, Pageable pageable){
        return missingRepository.getMissingMainPage(missingSearchDTO,pageable);
    }

    public void deleteByMissingId(Long missingId) {
        missingRepository.deleteByMissingId(missingId);
    }

    public List<MissingComment> getCommentsByMissingId(Long missingId) {
        return missingCommentRepository.getMissingMissingId(missingId);
    }

    public void saveComment(MissingCommentDTO missingCommentDTO) {
        MissingComment missingComment = missingCommentDTO.createMissingComment();
        missingCommentRepository.save(missingComment);
    }

    public Missing getMissingByMissingId(Long missingId) {
        return missingRepository.findByMissingId(missingId)
                .orElseThrow(() -> new EntityNotFoundException("Missing not found with id: " + missingId));
    }

    public void deleteByMissingCommentId(Long missingCommentId) {
        missingCommentRepository.deleteByMissingCommentId(missingCommentId);
    }

    public void updateByMissingCommentId(Long missingCommentId,String comment) {
        MissingComment missingComment =missingCommentRepository.findById(missingCommentId).orElseThrow(EntityNotFoundException::new);

        missingComment.updateComment(comment);



    }

}
