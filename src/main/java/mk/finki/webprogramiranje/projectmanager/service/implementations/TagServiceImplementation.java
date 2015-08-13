package mk.finki.webprogramiranje.projectmanager.service.implementations;

import mk.finki.webprogramiranje.projectmanager.model.Tag;
import mk.finki.webprogramiranje.projectmanager.repository.TagRepository;
import mk.finki.webprogramiranje.projectmanager.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImplementation implements TagService {
	@Autowired
	private TagRepository repository;

	public Iterable<Tag> findAll() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
	}
	
	public Tag findById(String id) {
		return repository.findOne(id);
	}
}