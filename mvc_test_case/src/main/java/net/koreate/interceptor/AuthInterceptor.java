package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.service.BoardService;
import net.koreate.service.UserService;
import net.koreate.vo.ReplyBoardVo;
import net.koreate.vo.UserVO;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Inject
	UserService service;
	
	@Inject
	BoardService boardService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		/*HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String query = request.getQueryString();
		System.out.println("query : " + query);
		
		if(query == null || query.equals("null")) {
			query="".trim();
		}else {
			query= "?"+query;
		}
		
		if(request.getMethod().equals("GET")) {
			System.out.println("요청 : " + uri+query);
			session.setAttribute("dest", uri+query);
		}
		
		if(session.getAttribute("userInfo") != null) {
			if(request.getParameter("bno") != null) {
				int bno = Integer.parseInt(request.getParameter("bno"));
				System.out.println("AuthInterceptor bno : "+bno);
				UserVO vo = (UserVO)session.getAttribute("userInfo");
				System.out.println("Auth : "+vo);
				System.out.println("uno : " + vo.getUno());
				System.out.println("bno : " + bno);
				ReplyBoardVo rbv = boardService.checkAuth(vo.getUno(),bno);
				System.out.println("rbv : "+rbv);
				if(rbv != null) {
					System.out.println("존재합니다. "+ rbv);
					return true;
				}else {
					response.sendRedirect("/board/readPage"+query);
					return false;
				}
			}
		}else {
			response.sendRedirect("/user/signIn");
			return false;
		}
		return true;*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
		
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String query = request.getQueryString();
		System.out.println("query : " + query);
		
		if(query == null || query.equals("null")) {
			query="".trim();
		}else {
			query= "?"+query;
		}
		
		String message = null;
		String page =null;
		
		
		if(request.getMethod().equals("GET")) {
			System.out.println("요청 : " + uri+query);
			session.setAttribute("dest", uri+query);
		}
		
		if(session.getAttribute("userInfo") != null) {
			if(request.getParameter("bno") != null) {
				int bno = Integer.parseInt(request.getParameter("bno"));
				System.out.println("AuthInterceptor bno : "+bno);
				UserVO vo = (UserVO)session.getAttribute("userInfo");
				System.out.println("Auth : "+vo);
				System.out.println("uno : " + vo.getUno());
				System.out.println("bno : " + bno);
				ReplyBoardVo rbv = boardService.checkAuth(vo.getUno(),bno);
				System.out.println("rbv : "+rbv);
				if(rbv == null) {
					page ="/board/readPage"+query;
					message="잘못된 접근 입니다.";
				}
			}
		}else {
			page="/user/signIn";
			message="로그인이후 사용가능합니다.";
			//response.sendRedirect("/user/signIn");
		}
		
		if(page != null) {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			request.setAttribute("message", message);
			rd.forward(request, response);
		}
	}
}
