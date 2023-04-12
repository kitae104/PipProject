package inhatc.pip.project.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import inhatc.pip.project.member.entity.Member;

//타입은 테이블 명, 아이디는 아이디의 타입
//얘가 하는 일은 테이블에 가서 가져오는 일을 함

public interface MemberRepository extends JpaRepository<Member, Long>, 
	QuerydslPredicateExecutor<Member>{
	
		
	
}
