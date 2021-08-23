package kr.co.softcampus.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.softcampus.beans.UserBean;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean) target;
		// validate 검사를 할때 사용되는 빈을 확인한다.
		// 회원가입시에는 joinUserBean 로그인시에는 tempLoginUserBean이 사용된다
		// 이를 식별하는 이유는 로그인시에는 DontCheckUserIdExist를 검사하지 말아야 하기 때문이다.
		String beanName = errors.getObjectName();

		if (beanName.equals("joinUserBean")) {
			if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}

			if (userBean.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}
		}
	}

}
