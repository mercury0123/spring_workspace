package com.ezen.myProject.repository;

import java.util.List;

import com.ezen.myProject.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);	//insertFile -> FileMapper

	List<FileVO> selectFileList(int bno);

}
