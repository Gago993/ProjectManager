package mk.finki.webprogramiranje.projectmanager.repository;

import mk.finki.webprogramiranje.projectmanager.model.Tag;

public interface TagRepositoryCustom {
	
	public Tag findByName(String name);
}