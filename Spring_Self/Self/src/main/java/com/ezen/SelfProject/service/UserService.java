package com.ezen.SelfProject.service;

import com.ezen.SelfProject.domain.UserVO;

public interface UserService {

	boolean signUp(UserVO user);

	UserVO getUser(String id, String pw);

}
