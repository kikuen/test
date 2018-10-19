package net.koreate.service;

import java.util.Date;

import net.koreate.dto.LoginDTO;
import net.koreate.vo.UserVO;

public interface UserService {

  public UserVO login(LoginDTO dto) throws Exception;

  public UserVO getUser(int uno) throws Exception;
 
}
