package handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.jempbox.xmp.Thumbnail;
import org.apache.tika.Tika;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.SelfProject.domain.FileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@AllArgsConstructor
@Component	//실제 파일 객체로 사용하기위해 servlet-context.xml 25 line 참고
public class FileHandler {
	private final String UP_DIR ="D:\\_javaweb\\_java\\fileUpload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files) {	// List, FileVO, MultipartFile import!! 
		LocalDate date = LocalDate.now();
		log.info(">>> date : "+ date);
		String today = date.toString();		//오늘 날짜를 스트링으로 변환"2022-12-27"
		today = today.replace("-", File.separator); //"2022-12-27"의 "-"를 파일 구분자"\"로 변경 (이러려고 폴더명 위처럼 정한것)
													// os 에 따라 "/" 로 쓰는 경우도 있으므로 File.separator 로 사용
		File folders = new File(UP_DIR, today);
		
		//폴더가 있으면 생성X 없으면 생성O
		if(!folders.exists()) {	//폴더가 존재하지 않으면
			folders.mkdirs();	//폴더를 생성하라
		}
		
		//파일 경로설정 완료
		List<FileVO> fList = new ArrayList<FileVO>();
		
		//향상된 for문
		for(MultipartFile file : files) {	//files 를 file 로
			FileVO fvo = new FileVO();
			fvo.setSave_dir(today);				//파일 경로 설정
			fvo.setFile_size(file.getSize());	//파일 사이즈 설정
			
			String originalFilename = file.getOriginalFilename();	//잘되나 체크하려고 
			log.info(">>> ori Filename : "+originalFilename);
			
			String onlyFileName = originalFilename.substring(originalFilename.lastIndexOf("\\")+1);
			log.info(">>> only Filename : "+onlyFileName);			
			fvo.setFile_name(onlyFileName);		//파일 이름 설정
			
			UUID uuid = UUID.randomUUID();		
			fvo.setUuid(uuid.toString());	//uuid 가 string 아니므로
			//여기까지 fvo 에 저장할 파일을 정보생성 -> set
			
			//디스크에 저장할 파일 객체 생성
			String fullFileName = uuid.toString() + "_" + onlyFileName;
			File storeFile = new File(folders, fullFileName);	//fullFileName 을 folders에 담아서 storeFile에 
			
			try {
				file.transferTo(storeFile); 	//원본객체를 저장을 위한 형태로 복사
				if (isImageFile(storeFile)) {	//isImageFile(storeFile) true 라면 (아래 boolean 참고)
					fvo.setFile_type(1);
					File thumbNail = new File(folders, uuid.toString() + "_th_" + onlyFileName);
					Thumbnails.of(storeFile).size(75,75).toFile(thumbNail);	//Thumbnails import
				}
				
			} catch (Exception e) {
				log.info(">>> File 생성 오류 !!");
				e.printStackTrace();
			}
			fList.add(fvo);
		}
		
		return fList;
	}
	//img 파일인지 아닌지 체크
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);	//Tika import!! detect throws IOException!!
		// image/png, image/jpg 형식으로 띄워줌
		return mimeType.startsWith("image")? true : false ;	//앞에 image 값이 붙어있으면 true로 리턴
	}
}