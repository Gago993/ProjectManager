package mk.finki.webprogramiranje.projectmanager.controller;
 
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mk.finki.webprogramiranje.projectmanager.model.Project;
import mk.finki.webprogramiranje.projectmanager.service.ProjectService;

@RestController
@RequestMapping(value="/projects")
public class ProjectController {
	@Autowired
	private ProjectService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Project> get(@PathVariable String id){
		Project project = service.findById(id);
		if(project != null){
			return new ResponseEntity<Project>(project, HttpStatus.OK);
		}else{
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/member", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<Project>> getProjectsForMember(HttpSession session){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			List<Project> projects = service.findProjectsForMember(sessionId);
			
			return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
		}else{
			return new ResponseEntity<List<Project>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method={RequestMethod.POST, RequestMethod.PUT}, produces="application/json")
	public ResponseEntity<Void> saveOrUpdate(HttpSession session, @PathVariable String id, @RequestBody Project jsonProject){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			if(jsonProject.getManagers().contains(sessionId)){
				service.save(jsonProject);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}else{
				return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
			}
		}else{
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<Void> delete(HttpSession session, @PathVariable String id){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Project project = service.findById(id);
			if(project != null){
				if(project.getManagers().contains(sessionId)){
					service.delete(project);
					return new ResponseEntity<Void>(HttpStatus.OK);
				}else{
					return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
				}
			}else{
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else{
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
}