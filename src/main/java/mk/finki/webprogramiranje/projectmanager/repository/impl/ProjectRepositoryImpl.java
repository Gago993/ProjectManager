package mk.finki.webprogramiranje.projectmanager.repository.impl;

import java.util.List;

import mk.finki.webprogramiranje.projectmanager.model.Project;
import mk.finki.webprogramiranje.projectmanager.repository.ProjectRepositoryCustom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<Project> findProjectsForMember(String memberId) {
		return mongoTemplate.find(new Query(new Criteria().orOperator(Criteria.where("managers").is(memberId), Criteria.where("employees").is(memberId))), Project.class);
	}
}