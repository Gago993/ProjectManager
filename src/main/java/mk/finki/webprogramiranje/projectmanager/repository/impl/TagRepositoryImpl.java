package mk.finki.webprogramiranje.projectmanager.repository.impl;

import mk.finki.webprogramiranje.projectmanager.model.Tag;
import mk.finki.webprogramiranje.projectmanager.repository.TagRepositoryCustom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepositoryImpl implements TagRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Tag findByName(String name) {
		return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), Tag.class);
	}
}