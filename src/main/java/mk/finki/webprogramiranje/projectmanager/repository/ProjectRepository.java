package mk.finki.webprogramiranje.projectmanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import mk.finki.webprogramiranje.projectmanager.model.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, String>, ProjectRepositoryCustom {
	
}