package mk.finki.webprogramiranje.projectmanager.controller;
 
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mk.finki.webprogramiranje.projectmanager.model.Member;
import mk.finki.webprogramiranje.projectmanager.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/login", method=RequestMethod.POST, headers="content-type=application/x-www-form-urlencoded")
	@ResponseBody
	public ResponseEntity<String> doLogin(HttpSession session, @RequestParam(name="email", required=true) String email, @RequestParam(name="password", required=true) String password) {
		//assuming 'password' is password md5 hash (not using https)
		
		if(LoginController.isValidEmailAddress(email) && password.length() == 32){
			Member member = service.findByEmail(email);
			if(member != null && member.getPassword().equals(password)){
				LoginController.setUpSession(session, member);
				return new ResponseEntity<String>("ok", HttpStatus.OK);
			}else{
				return new ResponseEntity<String>("invalid-credentials", HttpStatus.UNAUTHORIZED);
			}
		}else{
			return new ResponseEntity<String>("invalid-credentials", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> doLogout(HttpSession session){
		if(session.getAttribute("id") != null){
			session.invalidate();
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("not-logged-in", HttpStatus.UNAUTHORIZED);
		}
	}
	
	public static boolean isValidEmailAddress(String email) {
		return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
	}
	
	public static void setUpSession(HttpSession session, Member member){
		session.setAttribute("id", member.getId());
		session.setAttribute("firstname", member.getFirstName());
		session.setAttribute("avatar", member.getAvatar());
	}
}