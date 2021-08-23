package kr.co.softcampus.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.softcampus.beans.UserBean;

@Controller
public class HomeController {
	
	//세션스코프는 브라우저가 최초로 요청을 발생시킨 시점에 생성된다.
	//자바로 설정할 경우 브라우저에서 최초로 요청이 들어올 때 생성되는데 XML설정은 서버를 가동할때 자동으로 주입하려 시도한다.
	//자동으로 주입하려 하는데 브라우저에서 요청이 있어야 되는 세션이라 에러가 뜬다
	//그래서 Lazy 어노테이션을 통해 서버가 가동될때 생성되지 않고 브라우저가 요청할때 가동되도록한다
	//@Resource(name="loginUserBean")
	//@Lazy
	//private UserBean loginUserBean;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		//System.out.println(loginUserBean);
		return "redirect:/main";
	}
}

