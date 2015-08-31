package mk.finki.webprogramiranje.projectmanager.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mk.finki.webprogramiranje.projectmanager.model.Project;

public interface ProjectService {

	public Project save(Project project);
	
	public boolean saveLogo(Project project, MultipartFile logo);
	
	public boolean removeLogo(Project project);

	public Iterable<Project> findAll();
	
	public Iterable<Project> findAll(Iterable<String> ids);

	public Project findById(String id);
	
	public List<Project> findProjectsForMember(String memberId);

	public void delete(String id);

	public void delete(Project project);
}