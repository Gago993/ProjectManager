package mk.finki.webprogramiranje.projectmanager.controller;
 
import javax.servlet.http.HttpSession;

import mk.finki.webprogramiranje.projectmanager.model.Member;
import mk.finki.webprogramiranje.projectmanager.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RegisterController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/register", method=RequestMethod.POST, headers="content-type=application/x-www-form-urlencoded")
	@ResponseBody
	public String doRegister(HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
		//assuming 'password' is password hash (not using https)
		
		if(LoginController.isValidEmailAddress(email) && password.length() == 32 && !firstname.isEmpty() && !lastname.isEmpty()){
			LoginController.setUpSession(session, service.save(new Member(email, password, firstname, lastname)));
			return "ok";
		}else{
			return "invalid-data";
		}
	}
}