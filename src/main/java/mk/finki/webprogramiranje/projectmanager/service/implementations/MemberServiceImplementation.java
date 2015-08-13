package mk.finki.webprogramiranje.projectmanager.service.implementations;

import mk.finki.webprogramiranje.projectmanager.model.Member;
import mk.finki.webprogramiranje.projectmanager.repository.MemberRepository;
import mk.finki.webprogramiranje.projectmanager.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImplementation implements MemberService {
	@Autowired
	private MemberRepository repository;

	public Member save(Member member) {
		return repository.save(member);
	}

	public Iterable<Member> findAll() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
	}

	public Member findById(String id) {
		return repository.findOne(id);
	}

	public void delete(String id) {
		repository.delete(id);
	}

	public void delete(Member member) {
		repository.delete(member);
	}

	public Member findByEmail(String email) {
		return repository.findByEmail(email);
	}
}