package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class JdbcMemberRepository implements MemberRepository {
    private final DataSource dataSource;
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        } }
    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }

            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    private Connection getConnection() {

        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } try {
        if (pstmt != null) {
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
//package hello.hellospring.repository;
//
//import hello.hellospring.domain.Member;
//import org.springframework.jdbc.datasource.DataSourceUtils;
//
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class JdbcMemberRepository implements  MemberRepository{
//
//    private final DataSource dataSource; //DB에 붙기 위해서는 DataSource 라는게 필요하다, 나중에 Spring으로부터의 주입이 필요함
//
//    public JdbcMemberRepository(DataSource dataSource){ //1.Spring이 application.properties에 있는 DB 정보를 가지고 DB접속정보를 만들어 놓는다.
//        this.dataSource = dataSource;
//        //dataSource.getConnection(); //2.여기서 dataSource가 application.properties에 있는 정보를 기반으로 DB와 연결을 한다.
//    }
//
//    @Override
//    public Member save(Member member) {
//
//        String sql = "insert into member(name) values(?)";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null; //결과를 받는 JDBC
//
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //RETURN_GENERATED_KEY는 순서값을 넣는 역할
//            pstmt.setString(1, member.getName());
//            pstmt.executeUpdate(); //DB에 실제쿼리가 이때 날아
//            rs = pstmt.getGeneratedKeys();//getGeneratedKeys()는 위에서 설정한 pstmt 변수에서 키값(순서값)을 가져와
//            if (rs.next()) {
//                member.setId(rs.getLong(1));
//            } else {
//                throw new SQLException("id 조회 실패");
//            }
//            return member;
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        } finally {
//            close(conn, pstmt, rs);
//        }
//    }
//    @Override
//    public Optional<Member> findById(Long id) {
//        String sql = "select * from member where id = ?";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = getConnection(); //connection을 사용해서 sql날린다.
//            pstmt = conn.prepareStatement(sql);//pst설정, 학교 수업 때 기억해보자 하나의 패킷같은 역할임 선물포장을 하는거 같은은            pstmt.setLong(1, id);
//            rs = pstmt.executeQuery();
//            if(rs.next()) {//값이 있으면 member 객체를 만들어서 optional로 값을 반환한다.
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//                return Optional.of(member);
//            } else {
//                return Optional.empty();
//            }
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        } finally {
//            close(conn, pstmt, rs);
//        } }
//    @Override
//    public List<Member> findAll() {
//        String sql = "select * from member";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//            List<Member> members = new ArrayList<>();
//            while(rs.next()) {
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//                members.add(member);
//            }
//            return members;
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        } finally {
//            close(conn, pstmt, rs);
//        }
//    }
//    @Override
//    public Optional<Member> findByName(String name) {
//        String sql = "select * from member where name = ?";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, name);
//            rs = pstmt.executeQuery();
//            if(rs.next()) {
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//                return Optional.of(member);
//            }
//            return Optional.empty();
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        } finally {
//            close(conn, pstmt, rs);
//        }
//    }
//    //spring frame work를 사용해서 데이터 컨넥을 할 때는 DataSourceUtils를 사용해야함, 근데 이거 사용할 일 없음
//    private Connection getConnection() {
//        return DataSourceUtils.getConnection(dataSource);
//    }
//
//    //resource를 역순으로 close하는 과정
//    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
//    {
//        try {
//            if (rs != null) {
//                rs.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } try {
//        if (pstmt != null) {
//            pstmt.close();
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//        try {
//            if (conn != null) {
//                close(conn);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } }
//    private void close(Connection conn) throws SQLException {
//        DataSourceUtils.releaseConnection(conn, dataSource);
//    }
//}
//
