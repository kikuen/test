package net.koreate.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import net.koreate.dto.LoginDTO;
import net.koreate.service.UserService;
import net.koreate.vo.UserVO;

@Controller
@RequestMapping("/user/*")
public class UserController {

  @Inject
  private UserService service;

  @RequestMapping("/signIn")
  public void loginGET() {

  }

  // @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
  // public void loginPOST(LoginDTO dto, HttpSession session, Model model)
  // throws Exception {
  //
  // UserVO vo = service.login(dto);
  //
  // if (vo == null) {
  // return;
  // }
  //
  // model.addAttribute("userVO", vo);
  //
  // }

  @RequestMapping(value = "/signInPost", method = RequestMethod.POST)
  public void signInPost(LoginDTO dto, HttpSession session, Model model) throws Exception {

	  model.addAttribute("loginDTO",dto);

  }

  @RequestMapping(value = "/signOut", method = RequestMethod.GET)
  public String signOut(HttpServletRequest request, 
      HttpServletResponse response, HttpSession session) throws Exception {
	  Object obj = session.getAttribute("userInfo");
		if(obj != null) {
			session.removeAttribute("userInfo");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
			}
		}
		return "redirect:/";
  }
  
  @RequestMapping(value = "/signUp", method = RequestMethod.GET)
  public void signInUp(Model model) throws Exception {

  }
  
  @RequestMapping(value = "/signUpPost", method = RequestMethod.POST)
  public void signUpPost(UserVO vo, Model model) throws Exception {
	  System.out.println(vo);
  }


}
