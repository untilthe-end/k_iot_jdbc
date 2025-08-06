package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DBConnection 클래스
// : DB 연결을 관리하는 역할
public class DBConnection {
    // DB 연결 URL을 상수로 선언: MySQL DB 연결
    // +) localhost(로컬 컴퓨터), 3306(MySQL 서버 기본 포트 번호)
    private static final String URL = "jdbc:mysql://localhost:3306/k3_iot_jdbc";

    // DB 사용자 이름 / 비밀번호 상수 선언
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // DB 연결 상태 반환
    // : SQL 관련 예외 발생 방지                // 예외처리 해주세요~
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
