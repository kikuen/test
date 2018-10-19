package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import net.koreate.service.UserService;
import net.koreate.vo.UserVO;

public class CheckCookieInterceptor extends HandlerInterceptorAdapter{
	
	@Inject
	UserService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Checked Cookie preHandle 호출");
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") != null) {
			return true;
		}
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if(loginCookie != null) {
			int uno = Integer.parseInt(loginCookie.getValue());
			UserVO vo = service.getUser(uno);
			if(vo != null) {
				session.setAttribute("userInfo", vo);
			}
		}
		
		System.out.println("Checked Cookie preHandle 종료");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	

}
