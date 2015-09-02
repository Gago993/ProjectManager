package mk.finki.webprogramiranje.projectmanager.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mk.finki.webprogramiranje.projectmanager.model.Project;

public interface ProjectService {

	public Project save(Project project);
	
	public boolean saveLogo(Project project, MultipartFile logo);
	
	public boolean removeLogo(Project project);
	
	public boolean saveAttachment(Project project, MultipartFile attachment, String name, String description, String author);

	public boolean removeAttachment(Project project, int index);

	public boolean saveSnippet(Project project, String snippet, String extension, String name, String description, String author);

	public boolean removeSnippet(Project project, int index);

	public Iterable<Project> findAll();
	
	public Iterable<Project> findAll(Iterable<String> ids);

	public Project findById(String id);
	
	public List<Project> findProjectsForMember(String memberId);

	public void delete(String id);

	public void delete(Project project);
}