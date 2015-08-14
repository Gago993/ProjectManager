package mk.finki.webprogramiranje.projectmanager.service;

import mk.finki.webprogramiranje.projectmanager.model.Tag;

public interface TagService {
	
	public Iterable<Tag> findAll();
	
	public Iterable<Tag> findAll(Iterable<String> ids);
	
	public Tag findById(String id);
	
	public Tag findByName(String name);
}