package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV0 {
    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values(?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            /**
             * 문자이므로 setString 사용
             */
            pstmt.setString(1, member.getMemberId());
            /**
             * 숫자이므로 setInt 사용
             */
            pstmt.setInt(2, member.getMoney());
            /**
             * 1.executeUpdate를 통해 DB에 쿼리를 전송한다.
             * 2.executeUpdate는 반환값으로 조회한 자료의 수를 반환한다.
             */
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            /**
             * connection, preparestatement, resultSet 순으로 열어줬기 때문에 닫을때는 반대로
             * resultSet, prparestatement, connection 순으로 닫아야 한다.
             */
            close(con, pstmt, null);
        }
    }

    public Member findById(String memberId) throws SQLException{
        String sql = "select * from member where member_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,memberId);
            /**
             * 데이터를 조회할때는 executeQuery, 업데이트할 때는 executeUpdate
             */
            rs = pstmt.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            }else{
                throw new NoSuchElementException("member not found memberId=" + memberId);
            }
        }catch (SQLException e){
            log.error("db error", e);
            throw e;
        }finally {
            close(con, pstmt, rs);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }catch (SQLException e){
                log.info("error", e);
            }
        }

        if(stmt != null){
            try{
                stmt.close();
            }catch (SQLException e){
                log.info("error", e);
            }
        }

        if(con != null){
            try{
                con.close();
            }catch (SQLException e){
                log.info("error", e);
            }
        }
    }

    public void update(String memberId, int money) throws SQLException {
        String sql = "update member set money=? where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            int retSize = pstmt.executeUpdate();
            log.info("resultSize={}", retSize);
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            close(con, pstmt, null);
        }
    }

    public void delete(String memberId) throws SQLException {
        String sql = "delete from member where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            int retSize = pstmt.executeUpdate();
            log.info("resultSize={}", retSize);
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            close(con, pstmt, null);
        }
    }
    private Connection getConnection(){
        return DBConnectionUtil.getConnection();
    }
}
