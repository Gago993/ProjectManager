package mk.finki.webprogramiranje.projectmanager.controller;
 
import java.util.List;

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

import mk.finki.webprogramiranje.projectmanager.model.Project;
import mk.finki.webprogramiranje.projectmanager.service.ProjectService;

@RestController
@RequestMapping(value="/projects")
public class ProjectController {
	@Autowired
	private ProjectService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Project> get(HttpSession session, @PathVariable String id){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Project project = service.findById(id);
			if(project != null){
				if(project.getManagers().contains(sessionId) || project.getEmployees().contains(sessionId)){
					return new ResponseEntity<Project>(project, HttpStatus.OK);
				}else{
					return new ResponseEntity<Project>(HttpStatus.FORBIDDEN);
				}
			}else{
				return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
			}
		}else{
			return new ResponseEntity<Project>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Project>> getProjectsForMember(HttpSession session){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			List<Project> projects = service.findProjectsForMember(sessionId);
			
			return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
		}else{
			return new ResponseEntity<List<Project>>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<Project> create(HttpSession session){
		String sessionId = (String)session.getAttribute("id");

		if(sessionId != null){
			Project project = new Project();
			project.getManagers().add(sessionId);
			service.save(project);
			
			return new ResponseEntity<Project>(project, HttpStatus.OK);
		}else{
			return new ResponseEntity<Project>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(HttpSession session, @Valid @RequestBody Project jsonProject){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Project project = service.findById(jsonProject.getId());
			if(project != null){
				if(project.getManagers().contains(sessionId)){
					if(jsonProject.getManagers().size() == project.getManagers().size() || (jsonProject.getManagers().size() >= 1 && jsonProject.getManagers().size() == project.getManagers().size() - 1 && !jsonProject.getManagers().contains(sessionId))){
						service.save(jsonProject);
						return new ResponseEntity<Void>(HttpStatus.OK);
					}else{
						return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
					}
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
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
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
	
	@RequestMapping(value="/{id}/logo", method=RequestMethod.POST)
	public ResponseEntity<Void> changeAvatar(HttpSession session, @PathVariable String id, @RequestParam MultipartFile logo){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Project project = service.findById(id);
			if(project != null){
				if(project.getManagers().contains(sessionId)){
					if(!logo.isEmpty() && (logo.getContentType().equals("image/jpeg") || logo.getContentType().equals("image/png"))){
						if(service.saveLogo(project, logo)){
							return new ResponseEntity<Void>(HttpStatus.OK);
						}else{
							return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					}else{
						return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
					}
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