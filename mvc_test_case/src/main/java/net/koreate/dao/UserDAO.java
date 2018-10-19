package net.koreate.dao;

import java.util.Date;

import net.koreate.dto.LoginDTO;
import net.koreate.vo.UserVO;

public interface UserDAO {

	public UserVO login(LoginDTO dto)throws Exception;
	UserVO getUser(int uno)throws Exception;
}



