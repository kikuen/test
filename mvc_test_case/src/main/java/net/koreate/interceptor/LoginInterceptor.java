package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.dto.LoginDTO;
import net.koreate.service.UserService;
import net.koreate.vo.UserVO;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private String userInfo ="userInfo";
	
	@Inject
	UserService service;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("Login pre handle");
		HttpSession session = request.getSession();
		if(session.getAttribute(userInfo) != null) {
			System.out.println("data clear");
			session.removeAttribute(userInfo);
			session.invalidate();
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Login post handle");
		
		HttpSession session  = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		LoginDTO loginDto  = (LoginDTO)modelMap.get("loginDTO");
		
		UserVO vo = service.login(loginDto);
		
		System.out.println("postHandle : " + vo);
		
		if(vo != null) {
			session.setAttribute("userInfo", vo);
			
			if(loginDto.isUseCookie()) {
				System.out.println("cookie 생성");
				Cookie cookie = new Cookie("loginCookie",String.valueOf(vo.getUno()));
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);
				System.out.println("세션  ID : " + session.getId());
				System.out.println("회원번호 : " + String.valueOf(vo.getUno()));
				System.out.println("쿠키 생성 완료");
			}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/user/signIn");
			request.setAttribute("message", "FAILED");
			rd.forward(request, response);
			//response.sendRedirect("/user/signIn");
			return;
		}
		
		Object dest = session.getAttribute("dest");
		response.sendRedirect(dest != null ? (String)dest : "/");
	}
}

