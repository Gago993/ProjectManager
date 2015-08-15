package mk.finki.webprogramiranje.projectmanager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mk.finki.webprogramiranje.projectmanager.model.Tag;
import mk.finki.webprogramiranje.projectmanager.service.TagService;

@RestController
@RequestMapping(value="/tags")
public class TagController {
	@Autowired
	private TagService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Tag>> getAll(){
		return new ResponseEntity<Iterable<Tag>>(service.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Tag> get(@PathVariable String id){
		Tag tag = service.findById(id);
		if(tag != null){
			return new ResponseEntity<Tag>(tag, HttpStatus.OK);
		}else{
			return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/name/{name}", method=RequestMethod.GET)
	public ResponseEntity<Tag> getByName(HttpSession session, @PathVariable String name){
		Tag tag = service.findByName(name);
		if(tag != null){
			return new ResponseEntity<Tag>(tag, HttpStatus.OK);
		}else{
			return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
		}
	}
}