package com.ezen.SelfProject.repository;

import com.ezen.SelfProject.domain.UserVO;

public interface UserDAO {

	UserVO getUser(String id);

	int insertUser(UserVO user);

}
