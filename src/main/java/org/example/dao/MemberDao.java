package org.example.dao;

import org.example.db.DBConnection;
import org.example.db.entity.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// DAO는 데이터베이스와 연결되어 데이터를 조회하거나 저장하는 역할
// JDBC나 MyBatis JPA 등을 통해 DB와 직접 통신

public class MemberDao {
    public void addMember(Member newMember) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO members (name, email) VALUES (?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, newMember.getName());
        pstmt.setString(2, newMember.getEmail());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public List<Member> findAll() throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM members";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        List<Member> members = new ArrayList<>();
        while (rs.next()) {
            Member member = new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
            );
            members.add(member);
        }
        rs.close();
        pstmt.close();
        conn.close();

        return members;
    }

    public Member getMemberById(int id) throws SQLException { // get + 사용자 정보 + By + 찾고자하는 컬럼명
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM members where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        Member member = null;

        if (rs.next()) {
            member = new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
            );
        }

        rs.close();
        pstmt.close();
        conn.close();

        return member;
    }

    public void updateMember(Member member) {
        // 자원 해제가 필요한 아이는 try 옆에 () 안에 
        try (Connection conn = DBConnection.getConnection();)
        
        {   // 업데이트 할 이름과 이메일이 존재하는지 확인
            boolean updateName = member.getName() != null && !member.getName().isEmpty();
            boolean updateEmail = member.getEmail() != null && !member.getEmail().isEmpty();
            
            // SQL 쿼리 작성
            // cf) StringBuilder
            // : 자바에서 가변 문자열을 만드는 클래스 - 하나의 객체에 문자열을 추가하거나 수정 가능
            
            // +) 자바 String 타입 문자열은 "불변"
            String str = "Hello";
            str += " JDBC"; // str에 " JDBC"추가가 아니라 기존 문자열은 버려지고 새로운 String 객체 저장

            StringBuilder sql = new StringBuilder("UPDATE members SET ");

            if (updateName) {
                sql.append("name = ?, "); // ,(콤마)는 이후의 Email 추가 여부가 미정
            }

            if (updateEmail) {
                sql.append("email = ?, ");
            }

            // 마지막에 붙는 콤마와 공백 제거
            sql.deleteCharAt(sql.length() - 2); // 콤마 까지 제거

            sql.append("WHERE id = ?");

            // UPDATE members SET name = ?, email = ? WHERE id = ?

            // SQL 쿼리 실행
            PreparedStatement pstmt = conn.prepareStatement(sql.toString()); // sql은 StringBuilder 여서 toString() 해야함.

            // 파라미터 인덱스 설정 (1부터 시작)
            int parameterIndex = 1;

            if (updateName) {
                pstmt.setString(parameterIndex++, member.getName());
            }

            if (updateEmail) {
                pstmt.setString(parameterIndex++, member.getEmail());
            }

            pstmt.setInt(parameterIndex, member.getId());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            // try-with-resources 구문: JAVA 7부터 도입
            // >> try 블럭의 () 괄호 내에 자원 객체를 선언하면 try 블럭 종료 시 해당 자원 자동 해제

            String sql = "DELETE FROM members WHERE id = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
