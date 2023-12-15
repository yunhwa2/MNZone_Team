package com.mn.CommonService;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(String uploadPath, byte[] fileData,
                             String originalFileName)throws Exception{

        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String remakeFileName = uuid+extension;
        String remakeFileUrl = uploadPath+"/"+remakeFileName;

        FileOutputStream fos = new FileOutputStream(remakeFileUrl);
        fos.write(fileData);

        fos.close();

        return remakeFileName;
    }

    public void deleteFile(String filePath) throws Exception{

        File deleteFile = new File(filePath);

        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일 삭제");
        }else{
            log.info("파일이 존재하지 않습니다.");
        }

    }

}
