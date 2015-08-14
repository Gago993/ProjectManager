package mk.finki.webprogramiranje.projectmanager.service;

import java.util.List;

import mk.finki.webprogramiranje.projectmanager.model.Project;

public interface ProjectService {

	public Project save(Project project);

	public Iterable<Project> findAll();
	
	public Iterable<Project> findAll(Iterable<String> ids);

	public Project findById(String id);
	
	public List<Project> findProjectsForMember(String memberId);

	public void delete(String id);

	public void delete(Project project);
}