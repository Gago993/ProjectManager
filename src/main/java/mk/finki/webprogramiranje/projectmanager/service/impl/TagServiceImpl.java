package mk.finki.webprogramiranje.projectmanager.service.impl;

import mk.finki.webprogramiranje.projectmanager.model.Tag;
import mk.finki.webprogramiranje.projectmanager.repository.TagRepository;
import mk.finki.webprogramiranje.projectmanager.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private TagRepository repository;

	public Iterable<Tag> findAll() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
	}
	
	public Iterable<Tag> findAll(Iterable<String> ids) {
		return repository.findAll(ids);
	}
	
	public Tag findById(String id) {
		return repository.findOne(id);
	}
	
	public Tag findByName(String name) {
		return repository.findByName(name);
	}
}