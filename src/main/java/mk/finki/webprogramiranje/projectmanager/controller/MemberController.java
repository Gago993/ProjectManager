package mk.finki.webprogramiranje.projectmanager.controller;
 
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mk.finki.webprogramiranje.projectmanager.model.Member;
import mk.finki.webprogramiranje.projectmanager.service.MemberService;

@RestController
@RequestMapping(value="/members")
public class MemberController {
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Member> get(@PathVariable String id){
		Member member = service.findById(id);
		if(member != null){
			return new ResponseEntity<Member>(member, HttpStatus.OK);
		}else{
			return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method={RequestMethod.POST, RequestMethod.PUT}, produces="application/json")
	public ResponseEntity<Void> update(HttpSession session, @PathVariable String id, @RequestBody Member jsonMember){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null && sessionId.equals(id)){
			if(jsonMember.getId().equals(id)){
				service.save(jsonMember);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}else{
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}else{
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}