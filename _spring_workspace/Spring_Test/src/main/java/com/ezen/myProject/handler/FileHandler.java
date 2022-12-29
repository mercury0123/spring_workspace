package com.ezen.myProject.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.myProject.domain.FileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@AllArgsConstructor
@Component
public class FileHandler {
   private final String UP_DIR = "D:\\_javaweb\\_java\\fileUpload";
   
   public List<FileVO> uploadFiles(MultipartFile[] files){
      LocalDate date = LocalDate.now();
      log.info("date : "+date);
      String today = date.toString(); // 2022-12-27
      // - -> \ 파일 구분자로 변경
      // 2022-12-27 -> 2022\12\27
      today = today.replace("-", File.separator);
      
      File folders = new File(UP_DIR, today);
      
      // 없으면 생성, 있으면 있는 곳에서 저장
      if(!folders.exists()) {
         folders.mkdirs(); // 폴더 생성
      }
      // 폴더 생성
      
      List<FileVO> fileList = new ArrayList<FileVO>();
      for(MultipartFile file : files) {
         FileVO fvo = new FileVO();
         fvo.setSave_dir(today); // 파일 경로 설정
         fvo.setFile_size(file.getSize()); // 파일 사이즈 설정
         
         String originalFileName = file.getOriginalFilename();
         log.info("fileName : "+originalFileName);
         
         String onlyFileName = originalFileName
               .substring(originalFileName.lastIndexOf("\\")+1);
         // 실제 파일명만 추출
         
         fvo.setFile_name(onlyFileName); // 파일 이름 설정
         
         UUID uuid = UUID.randomUUID();
         fvo.setUuid(uuid.toString());
         
         // 여기까지 fvo에 저장할 파일의 정보 생성 -> set
         
         String fullFileName = uuid.toString()+"_"+onlyFileName;
         File storeFile = new File(folders, fullFileName);
         
         try {
            file.transferTo(storeFile); // 원본 객체를 저장을 위한 형태의 객체로 복사
            if(isImageFile(storeFile)) {
               fvo.setFile_type(1);
               File thumbNail = new File(folders, uuid.toString()+"_th_"+onlyFileName);
               Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
         fileList.add(fvo);
      }
      
      return fileList;
   }
   private boolean isImageFile(File storeFile) throws IOException {
      String mimeType = new Tika().detect(storeFile);
      // image/png, image/jpg
      
      return mimeType.startsWith("image")? true: false; // 앞에 "image"가 포함되어있다면
   }
}