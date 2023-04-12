package inhatc.pip.project.member.repository;

import static inhatc.pip.project.member.entity.QMember.member;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import inhatc.pip.project.member.constart.Role;
import inhatc.pip.project.member.entity.Member;
import inhatc.pip.project.member.entity.QMember;
import jakarta.persistence.EntityManager;

@SpringBootTest
class MemberRepositoryTest {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private EntityManager em;
	
	@Test
	void inserttest() {
		for (int i = 0; i < 100; i++) {			
			Member member = Member.builder()
					.name("홍길동"+i)
					.email("hong"+i+"@test.com")
					.password("1234")
					.address("인천 미추홀구 10"+i)
					.role(Role.USER)
					.build();
			memberRepository.save(member);
			
		}
	}
	
	@Test	
	@DisplayName("queryDsl 테스트")
	public void queryDsl1() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
			
		
		List<Member> list = queryFactory
			.select(member)
			.from(member)			
			.where(member.name.like("%" +"홍길동1" + "%"))
			.fetch();
		
		System.out.println("결과 : " + list.size());
	}

	@Test	
	@DisplayName("queryDsl 테스트2")
	public void queryDsl2() {
		
		String strRole = "USER";
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(member.name.like("%" +"홍길동1" + "%"));
		builder.and(member.address.like("%" +"01" + "%"));
		
		if(StringUtils.equals(strRole, Role.USER)) {
			builder.and(member.role.eq(Role.USER));
		}
		
		Pageable pageable = PageRequest.of(1, 4);
		Page<Member> result = memberRepository.findAll(builder, pageable);
		
		System.out.println("전체 데이터 수 : " + result.getTotalElements());
		System.out.println("전체 페이지 수 : " + result.getTotalPages());
		System.out.println("현재 페이지 번호 : " + result.getNumber());		
		
		List<Member> content = result.getContent();
		for (Member m : content)
		{
			System.out.println(m);
		}
		
	}
}
