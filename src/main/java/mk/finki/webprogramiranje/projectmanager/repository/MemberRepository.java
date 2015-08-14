package mk.finki.webprogramiranje.projectmanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import mk.finki.webprogramiranje.projectmanager.model.Member;

public interface MemberRepository extends PagingAndSortingRepository<Member, String>, MemberRepositoryCustom {
	
}