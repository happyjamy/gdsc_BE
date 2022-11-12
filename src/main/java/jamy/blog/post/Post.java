package jamy.blog.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data // Getter, Setter 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성해줍니다.
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 생성해줍니다.
@Builder // builder 메서드를 자동 생성해줍니다.
@Entity // 서버 실행 시, Object Relation Mapping(ORM) 이 됨. (즉, 테이블이 DB에 생성 됨)
public class Post {
    @Id // PK를 해당 변수로 하겠다는 뜻.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
// 해당 데이터베이스 번호 증가 전략을 따라가겠다 (H2 DB 전략을 따라감)
    private Long id;
    private String title;
    @Column(length = 50000) // 최대 길이 설정
    private String content;
}