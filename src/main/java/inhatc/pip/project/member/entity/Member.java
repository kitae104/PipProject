package inhatc.pip.project.member.entity;

import org.springframework.boot.context.properties.bind.Name;

import inhatc.pip.project.member.constart.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //테이블 만들어줌
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;

	private String address;
	
	@Enumerated(EnumType.STRING)  // 문자열 형태로 사용 원래 enum은 상수임
	@Column(nullable = false, length = 20, columnDefinition = "varchar(20) default 'USER'")
	private Role role;
	
}
