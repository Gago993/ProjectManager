package mk.finki.webprogramiranje.projectmanager.repository;

import java.util.List;

import mk.finki.webprogramiranje.projectmanager.model.Project;

public interface ProjectRepositoryCustom {
	
	public List<Project> findProjectsForMember(String memberId);
}