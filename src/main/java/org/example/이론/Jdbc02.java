package org.example.이론;

/*
    === JDBC 환경 설정 ===

    1. 필요한 준비물
        1) JDK (JAVA - JDK 17)
        2) IDE (IntelliJ, Eclipse 등)
        3) MySQL (MySQL Server 8.x 버전 이상 권장)
        4) JDBC driver
            : mysql-connector-java-x.x.xx.jar
            > 자바가 DB와 통신하도록 도와주는 드라이버

        +) SQL 스키마, JAVA 프로젝트
        
    2. JDBC 연결 시 자주 발생하는 오류 & 해결법
        1) ClassNotFoundException
            : 외부 라이브러리 sql connector가 프로젝트에 추가되지 않음
        2) Access denied for user
            : DB 사용자 ID/PW 오타 또는 권한 없음
        3) Communications link failure
            : MySQL 서버가 실행 중인지 확인 (포트번호 3306 확인)
        4) Unknown database
            : DB 이름 오타 또는 미생성
            
    3. JDBC 연결 흐름
        JDBC = JAVA + DB 연결하는 다리
        
        1) JDBC 드라이버 로드
            - 자바가 DB와 통신하기 위해서는 JDBC 드라이버가 필요!
            - JDBC 드라이버는 DBMS 별로 제공 (MySQL - com.mysql.cj.jdbc.Driver)
            - Class.forName()을 사용하여 메모리에 적재
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            >> Java는 다양한 DB와 연결할 수 있어야 함! - 표준 인터페이스만 제공 
                : 실제 동작은 DB 벤더가 만든 드라이버 구현체가 처리
        
        2) 데이터베이스 연결 (Connection)
            - 드라이버가 로드되면, DB에 연결 가능
            - DriverManager.getConnection()을 통해 Connection 객체 생성
            
            cf) Connection 객체
                - 실제로 DB 서버와 연결된 통로를 의미
                - 해당 객체를 통해 SQL을 전달하고, 결과를 받을 수 있음 
                >> DB URL(서버 주소 + 연결 커넥션), 사용자, 비밀번호 전달 - DB 접속 정보 설정

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/k3_iot_jdbc,
                "root",
                "root"
            );

        3) SQL 실행 준비 (Statement OR PreparedStatement)
            - Connection 객체를 이용해 SQL 실행 준비 객체 생성
            - Statement: 문자열로 SQL을 직접 작성
            - PreparedStatement: 미리 컴파일 된 SQL 사용 (보안, 성능 향상)

            String sql = "SELECT * FROM users WHERE age > ?";
            PreparedStatement pstmt= conn.prepareStatement(sql);
            pstmt.setInt(1, 20);

        4) SQL 실행 (Execute)
            - SQL 문장 실행 후 결과를 받음
            - 종류에 따라 다른 메서드 사용
                > executeQuery(); - 조회(SELECT)
                > executeUpdate(); - 삽입/수정/삭제(INSERT,UPDATE,DELETE)

            ResultSet rs = pstmt.executeQuery(); // SELECT

        5) 결과 처리 및 자원 정리
            - SELECT 문이라면 ResultSet을 통해 결과를 받음
            - 반복문을 통해 결과 처리

            rs.close();
            pstmt.close();
            conn.close();
            >> 메모리 누수 방지 (모든 자원 해제)
 */
public class Jdbc02 {
}
