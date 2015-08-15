package mk.finki.webprogramiranje.projectmanager.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mk.finki.webprogramiranje.projectmanager.model.Member;
import mk.finki.webprogramiranje.projectmanager.service.MemberService;

@RestController
@RequestMapping(value="/members")
public class MemberController {
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Member> getById(HttpSession session, @PathVariable String id){
		Member member = service.findById(id);
		if(member != null){
			member.setEmail(null);
			member.setPassword(null);
			
			return new ResponseEntity<Member>(member, HttpStatus.OK);
		}else{
			return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/email", method=RequestMethod.POST)
	public ResponseEntity<Member> getByEmail(HttpSession session, @RequestParam String email){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Member member = service.findByEmail(email);
			if(member != null){
				member.setEmail(null);
				member.setPassword(null);
				
				return new ResponseEntity<Member>(member, HttpStatus.OK);
			}else{
				return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
			}
		}else{
			return new ResponseEntity<Member>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	//logged in member:
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<Member> get(HttpSession session){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Member member = service.findById(sessionId);
			return new ResponseEntity<Member>(member, HttpStatus.OK);
		}else{
			return new ResponseEntity<Member>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(HttpSession session, @RequestBody @Valid Member jsonMember){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			if(jsonMember.getId().equals(sessionId)){
				service.save(jsonMember);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}else{
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}else{
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> changePicture(HttpSession session, @RequestParam MultipartFile picture){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			if(!picture.isEmpty() && (picture.getContentType().equals("image/jpeg") || picture.getContentType().equals("image/png"))){
				Member member = service.findById(sessionId);
				if(service.savePicture(member, picture)){
					return new ResponseEntity<Void>(HttpStatus.OK);
				}else{
					return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}else{
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}else{
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
}