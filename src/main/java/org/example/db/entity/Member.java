package org.example.db.entity;

// Entity: 데이터베이스 테이블과 매핑되는 클래스 (1:1)

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
// : JDBC 또는 ORM(DB와 Java를 객체화하여 연동하는 체계)에서는
//      , 빈 객체를 먼저 만들고 setter로 값을 넣는 방식 사용
// >> AllArgsConstructor도 NoArgsConstructor가 전제되어야 함!
@Getter
@Setter
@ToString
public class Member {
    private int id;
    private String name;
    private String email;
}
