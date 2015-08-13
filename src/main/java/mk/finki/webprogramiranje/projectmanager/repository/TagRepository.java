package mk.finki.webprogramiranje.projectmanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import mk.finki.webprogramiranje.projectmanager.model.Tag;

public interface TagRepository extends PagingAndSortingRepository<Tag, String> {
	
}