package mk.finki.webprogramiranje.projectmanager.service.impl;

import java.util.List;

import mk.finki.webprogramiranje.projectmanager.model.Project;
import mk.finki.webprogramiranje.projectmanager.repository.ProjectRepository;
import mk.finki.webprogramiranje.projectmanager.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository repository;

	public Project save(Project project) {
		return repository.save(project);
	}

	public Iterable<Project> findAll() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
	}
	
	public Iterable<Project> findAll(Iterable<String> ids) {
		return repository.findAll(ids);
	}

	public Project findById(String id) {
		return repository.findOne(id);
	}
	
	public List<Project> findProjectsForMember(String memberId) {
		return repository.findProjectsForMember(memberId);
	}

	public void delete(String id) {
		repository.delete(id);
	}

	public void delete(Project project) {
		repository.delete(project);
	}
}