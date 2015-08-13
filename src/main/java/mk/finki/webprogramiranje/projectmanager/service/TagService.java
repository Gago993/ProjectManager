package mk.finki.webprogramiranje.projectmanager.service;

import mk.finki.webprogramiranje.projectmanager.model.Tag;

public interface TagService {
	
	public Iterable<Tag> findAll();
	
	public Tag findById(String id);
}