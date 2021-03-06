package mk.finki.webprogramiranje.projectmanager.repository.impl;

import java.util.List;

import mk.finki.webprogramiranje.projectmanager.model.Member;
import mk.finki.webprogramiranje.projectmanager.repository.MemberRepositoryCustom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Member findByEmail(String email) {
		return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Member.class);
	}

	public List<Member> searchByEmail(String email) {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(Criteria.where("email").regex(email)), Member.class);
	}
}