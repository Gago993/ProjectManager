package mk.finki.webprogramiranje.projectmanager.repository;

import mk.finki.webprogramiranje.projectmanager.model.Member;

public interface MemberRepositoryExpansion {
	
	public Member findByEmail(String email);
}