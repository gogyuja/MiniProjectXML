package kr.co.softcampus.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softcampus.beans.ContentBean;
import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor {
	
	@Resource(name="loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	@Autowired
	private BoardService boardService;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//수정하고 삭제에 반응하는 인터셉터다
		//수정 혹은 삭제를 url을 통해 권한없는 사용자가 하려할때 막는 인터셉터이다
		
		String str1=request.getParameter("content_idx");
		int content_idx=Integer.parseInt(str1);
		
		ContentBean currentContentBean=boardService.getContentInfo(content_idx);
		
		//글에 있는 writer_idx와 현재 로그인해 있는 유저의 idx가 다르면 false를 반환하게 한다
		if(currentContentBean.getContent_writer_idx()!=loginUserBean.getUser_idx()){
			String contextPath=request.getContextPath();
			response.sendRedirect(contextPath+"/board/not_writer");
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
