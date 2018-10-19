package net.koreate.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.UserDAO;
import net.koreate.dto.LoginDTO;
import net.koreate.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

  @Inject
  private UserDAO dao;

  @Override
  public UserVO login(LoginDTO dto) throws Exception {

    return dao.login(dto);
  }

  
  @Override
	public UserVO getUser(int uno) throws Exception {
		return dao.getUser(uno);
	}  
}
