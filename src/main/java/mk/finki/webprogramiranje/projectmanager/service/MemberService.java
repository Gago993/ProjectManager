package mk.finki.webprogramiranje.projectmanager.service;

import mk.finki.webprogramiranje.projectmanager.model.Member;

public interface MemberService {

	public Member save(Member member);

	public Iterable<Member> findAll();

	public Member findById(String id);
	
	public Member findByEmail(String email);

	public void delete(String id);

	public void delete(Member member);
}