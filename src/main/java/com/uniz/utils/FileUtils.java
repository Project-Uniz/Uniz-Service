package com.uniz.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.uniz.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Component("fileUtils")
public class FileUtils {
	
	String uploadPath = "C:\\mp\\file";
	
	
	public List<Map<String, Object>> parseInsertFileInfo(BoardVO boardVO, 
			MultipartFile[] file) throws Exception{
		
		String postSN = String.valueOf(boardVO.getPostSN());
        
        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
 
        File target = new File(uploadPath);
        if(target.exists() == false){
        	target.mkdirs(); 
        	}

        if(file.length >= 1) {
        	try {
       
        for(int i=0; i<file.length; i++) {
        	log.info("file length : " + file.length);
            String orgFileName = file[i].getOriginalFilename();
            log.info("test========== " + orgFileName);
            String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
            String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
            Long saveFileSize = file[i].getSize();
            
            log.debug("================== file start ==================");
            log.debug("파일 실제 이름: "+orgFileName);
            log.debug("파일 저장 이름: "+saveFileName);
            log.debug("파일 크기: "+saveFileSize);
            log.debug("content type: "+file[i].getContentType());
            log.debug("================== file   END ==================");
 
            target = new File(uploadPath, saveFileName);
            file[i].transferTo(target);
            
            Map<String, Object> fileInfo = new HashMap<String, Object>();
 
            fileInfo.put("postSN", postSN);
            fileInfo.put("orgName", orgFileName);
            fileInfo.put("changeName", saveFileName);
            fileInfo.put("fileSize", saveFileSize);
            fileList.add(fileInfo);
            
        		}
        	}catch(Exception e) {
        		e.printStackTrace();
        		
        	}
        }
        
        return fileList;
    }
}


