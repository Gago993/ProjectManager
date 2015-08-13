package mk.finki.webprogramiranje.projectmanager.repository.implementations;

import mk.finki.webprogramiranje.projectmanager.model.Member;
import mk.finki.webprogramiranje.projectmanager.repository.MemberRepositoryExpansion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
class MemberRepositoryExpansionImplementation implements MemberRepositoryExpansion {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Member findByEmail(String email) {
		return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Member.class);
	}
	
}