package com.ezen.SelfProject.repository;

import java.util.List;

import com.ezen.SelfProject.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> selectFileList(int bno);

}
