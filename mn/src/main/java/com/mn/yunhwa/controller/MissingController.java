package com.mn.yunhwa.controller;

import com.mn.yunhwa.dto.MissingFormDTO;
import com.mn.yunhwa.dto.MissingMainDTO;
import com.mn.yunhwa.dto.MissingSearchDTO;
import com.mn.yunhwa.service.MissingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MissingController {

    private final MissingService missingService;

    @GetMapping("/missing/write")
    public String missingForm(Model model, HttpSession session){
        model.addAttribute("missingFormDTO", new MissingFormDTO());
        Object memberCode = session.getAttribute("memberCode");

        if (memberCode != null) {
            model.addAttribute("memberCode", memberCode);
        } else {
            return "/members/login";
        }

        return "yunhwa/missingForm";
    }

    @PostMapping("/missing/write")
    public String missingNew(@Valid MissingFormDTO missingFormDTO, BindingResult bindingResult, Model model){
            if(bindingResult.hasErrors()){
            return "yunhwa/missingForm";
        }


        try{
            Long missingId = missingService.saveMissing(missingFormDTO);
            return "redirect:/missing";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMessage","실종글 등록 중에 에러가 발생하였습니다.");
            return "yunhwa/missingForm";
        }
    }

    @GetMapping("/missing/write/{missingId}")
    public String missingDtl(@PathVariable("missingId") Long missingId, Model model){

        try{
            MissingFormDTO missingFormDTO = missingService.getMissingDtl(missingId);
            model.addAttribute("missingFormDTO",missingFormDTO);
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage","존재하지 않는 글입니다.");
            model.addAttribute("missingFormDTO",new MissingFormDTO());
            return "yunhwa/missingForm";
        }
        return "yunhwa/missingForm";
    }

    @PostMapping("missing/write/{missingId}")
    public String missingUpdate(@Valid MissingFormDTO missingFormDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "yunhwa/missingForm";
        }

        try {
            long updateMissingId = missingService.updateMissing(missingFormDTO);
            return "redirect:/missing";
        }catch (Exception e){
            model.addAttribute("errorMessage","글 수정 중 에러가 발생하였습니다.");
            return "yunhwa/missingForm";
        }
    }

    @GetMapping(value = "/missing")
    public String missingMain(MissingSearchDTO missingSearchDTO, Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,9);
        Page<MissingMainDTO> missingMainDTOS = missingService.getMissingMainPage(missingSearchDTO,pageable);
        model.addAttribute("missings",missingMainDTOS);
        model.addAttribute("missingSearchDTO",missingSearchDTO);

        long missingCount = missingService.countMissing();
        model.addAttribute("missingCount", missingCount);
        model.addAttribute("maxPage",5);

        return "yunhwa/missing";
    }

    @GetMapping("/missing/{missingId}")
    public String missingDtl(Model model, @PathVariable("missingId") Long missingId){
        MissingFormDTO missingFormDTO = missingService.getMissingDtl(missingId);
        model.addAttribute("missing",missingFormDTO);
        return "yunhwa/missingDtl";
    }

    @DeleteMapping("/missing")
    public String deleteMissing() {
//        missingService.deleteByMissingId(id);
        System.err.println("된당");
        return "redirect:/yunhwa/missing"; // 삭제 후 리다이렉트
    }




//    @PostMapping("/missing/write/upload")
//    public ResponseEntity<String> handleFileUpload(@RequestParam("upload") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
//        try {
//            String missingOriImgName = file.getOriginalFilename();
//            String missingImgName = "";
//            String missingImgUrl = "";
//
//            if (!StringUtils.isEmpty(missingOriImgName)) {
//                missingImgName = fileService.uploadFile(missingImgLocation, missingOriImgName, file.getBytes());
//                missingImgUrl = "/images/missing/" + missingImgName;
//            }
//
//            JsonObject outData = new JsonObject();
//            outData.addProperty("uploaded", true);
//            outData.addProperty("url", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/getImage?fileNm=" + missingImgName);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().print(outData.toString());
//            return ResponseEntity.ok("파일 '" + missingImgName + "'이(가) 성공적으로 업로드되었습니다.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드에 실패했습니다.");
//        }
//    }

//    @RequestMapping("/getImage")
//    public void getImageForContents(ModelMap model, @RequestParam Map<String, Object> commandMap, HttpServletResponse response) throws Exception {
//        String fileNm = (String)commandMap.get("fileNm");
//        String fileStr = missingImgLocation;
//
//        File tmpDir = new File(fileStr);
//        if(!tmpDir.exists()) {
//            tmpDir.mkdirs();
//        }
//
//        FileInputStream fis = null;
//        BufferedInputStream in = null;
//        ByteArrayOutputStream bStream = null;
//
//        try {
//
//            fis = new FileInputStream(new File(fileStr, fileNm));
//            in = new BufferedInputStream(fis);
//            bStream = new ByteArrayOutputStream();
//
//            int imgByte;
//            while ((imgByte = in.read()) != -1) {
//                bStream.write(imgByte);
//            }
//
//            String type = "";
//            String ext = fileNm.substring(fileNm.lastIndexOf(".") + 1).toLowerCase();
//
//            if ("jpg".equals(ext)) {
//                type = "image/jpeg";
//            } else {
//                type = "image/" + ext;
//            }
//
//            response.setHeader("Content-Type", type);
//            response.setContentLength(bStream.size());
//
//            bStream.writeTo(response.getOutputStream());
//
//            response.getOutputStream().flush();
//            response.getOutputStream().close();
//
//        } finally {
//            //   EgovResourceCloseHelper.close(bStream, in, fis);
//        }
//    }

}
