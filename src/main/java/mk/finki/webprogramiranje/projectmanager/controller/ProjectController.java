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

import mk.finki.webprogramiranje.projectmanager.model.Member;
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
	public ResponseEntity<Project> update(HttpSession session, @Valid @RequestBody Project jsonProject){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Project project = service.findById(jsonProject.getId());
			if(project != null){
				if(project.getManagers().contains(sessionId) || project.getEmployees().contains(sessionId)){
					jsonProject = service.save(jsonProject);
					return new ResponseEntity<Project>(jsonProject, HttpStatus.OK);
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
	
	@RequestMapping(value="/{id}/change-logo", method=RequestMethod.POST)
	public ResponseEntity<Void> changeLogo(HttpSession session, @PathVariable String id, @RequestParam MultipartFile logo){
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
	
	@RequestMapping(value="/{id}/remove-logo", method=RequestMethod.DELETE)
	public ResponseEntity<Void> removeLogo(HttpSession session, @PathVariable String id){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Project project = service.findById(id);
			if(project != null){
				if(project.getManagers().contains(sessionId)){
					if(service.removeLogo(project)){
						return new ResponseEntity<Void>(HttpStatus.OK);
					}else{
						return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	@RequestMapping(value="/{id}/attachment", method=RequestMethod.POST)
	public ResponseEntity<Project> uploadAttachment(HttpSession session, @PathVariable String id, @RequestParam MultipartFile attachment, @RequestParam String name, @RequestParam String description){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Project project = service.findById(id);
			if(project != null){
				if(project.getManagers().contains(sessionId) || project.getEmployees().contains(sessionId)){
					if(!attachment.isEmpty()){
						if(service.saveAttachment(project, attachment, name, description, sessionId)){
							return new ResponseEntity<Project>(project, HttpStatus.OK);
						}else{
							return new ResponseEntity<Project>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					}else{
						return new ResponseEntity<Project>(HttpStatus.BAD_REQUEST);
					}
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
	
	@RequestMapping(value="/{id}/attachment/{index}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> removeAttachment(HttpSession session, @PathVariable String id, @PathVariable String index){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Project project = service.findById(id);
			if(project != null){
				if(project.getManagers().contains(sessionId) || project.getEmployees().contains(sessionId)){
					try{
						int parsedIndex = Integer.parseInt(index);
						if(parsedIndex >= 0 && parsedIndex < project.getAttachments().size()){
							if(project.getManagers().contains(sessionId) || project.getCodeSnippets().get(parsedIndex).getAuthor().equals(sessionId)){
								if(service.removeAttachment(project, parsedIndex)){
									return new ResponseEntity<Void>(HttpStatus.OK);
								}else{
									return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
								}
							}else{
								return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
							}
						}else{
							return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
						}
					}catch(NumberFormatException exception){
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
	
	@RequestMapping(value="/{id}/snippet", method=RequestMethod.POST)
	public ResponseEntity<Project> uploadCodeSnippet(HttpSession session, @PathVariable String id, @RequestParam String snippet, @RequestParam String extension, @RequestParam String name, @RequestParam String description){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Project project = service.findById(id);
			if(project != null){
				if(project.getManagers().contains(sessionId) || project.getEmployees().contains(sessionId)){
					if(!snippet.isEmpty()){
						if(service.saveSnippet(project, snippet, extension, name, description, sessionId)){
							return new ResponseEntity<Project>(project, HttpStatus.OK);
						}else{
							return new ResponseEntity<Project>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					}else{
						return new ResponseEntity<Project>(HttpStatus.BAD_REQUEST);
					}
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
	
	@RequestMapping(value="/{id}/snippet/{index}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> removeSnippet(HttpSession session, @PathVariable String id, @PathVariable String index){
		String sessionId = (String)session.getAttribute("id");
		if(sessionId != null){
			Project project = service.findById(id);
			if(project != null){
				if(project.getManagers().contains(sessionId) || project.getEmployees().contains(sessionId)){
					try{
						int parsedIndex = Integer.parseInt(index);
						if(parsedIndex >= 0 && parsedIndex < project.getCodeSnippets().size()){
							if(project.getManagers().contains(sessionId) || project.getCodeSnippets().get(parsedIndex).getAuthor().equals(sessionId)){
								if(service.removeSnippet(project, parsedIndex)){
									return new ResponseEntity<Void>(HttpStatus.OK);
								}else{
									return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
								}
							}else{
								return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
							}
						}else{
							return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
						}
					}catch(NumberFormatException exception){
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