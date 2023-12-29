package com.mn.controller;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Controller
@CrossOrigin
@RequestMapping("/files")
public class CkeditorFileUploadController {

    @Value("${image.upload.path}")
    private String uploadPath;
    @PostMapping("/fileupload.do")

    public ResponseEntity<String> fileUpload(HttpServletRequest request, MultipartHttpServletRequest multiFile) throws IOException {

        // Json 객체 생성
        JsonObject json = new JsonObject();
        // 파일을 가져오기 위해 MultipartHttpServletRequest 의 getFile 메서드 사용
        MultipartFile file = multiFile.getFile("upload");

        // 파일이 비어있지 않고(비어 있다면 null 반환)
        if (file != null) {
            // 파일 사이즈가 0보다 크고, 파일이름이 공백이 아닐 때
            if (file.getSize() > 0 && StringUtils.isNotBlank(file.getOriginalFilename())) {
                if (file.getContentType().toLowerCase().startsWith("image/")) {

                    try {
                        // 파일 이름 설정
                        String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                        // 바이트 타입 설정
                        byte[] bytes;
                        // 파일을 바이트 타입으로 변경
                        bytes = file.getBytes();
                        // 파일이 실제로 저장되는 경로
                        //String uploadPath = request.getServletContext().getRealPath("/resources/ckimage/");
                        String upload = uploadPath;
                        // 저장되는 파일에 경로 설정
                        File uploadFile = new File(upload);
                        if (!uploadFile.exists()) {
                            uploadFile.mkdirs();
                        }
                        // 업로드 경로 + 파일 이름을 주어 데이터를 서버에 전송
                        upload = upload + "/" + fileName;
                        try (OutputStream out = new FileOutputStream(new File(upload))) {
                            out.write(bytes);
                        }

                        // 파일이 연결되는 Url 주소 설정
                        String fileUrl = "/missing/post/image/" + fileName;

                        // 생성된 json 객체를 이용해 파일 업로드 + 이름 + 주소를 CkEditor에 전송
                        json.addProperty("uploaded", 1);
                        json.addProperty("fileName", fileName);
                        json.addProperty("url", fileUrl);

                        // ResponseEntity를 사용하여 JSON 응답 반환
                        return new ResponseEntity<>(json.toString(), HttpStatus.OK);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // 실패할 경우 빈 JSON 응답 반환
        return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
    }



}