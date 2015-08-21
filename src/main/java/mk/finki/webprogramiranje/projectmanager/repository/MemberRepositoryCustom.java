package mk.finki.webprogramiranje.projectmanager.repository;

import java.util.List;

import mk.finki.webprogramiranje.projectmanager.model.Member;

public interface MemberRepositoryCustom {
	
	public Member findByEmail(String email);
	
	public List<Member> searchByEmail(String email);
}