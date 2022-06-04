package hello.jdbc.connection;

/**
 * jdbc:h2의 h2라는 정보를 보고 라이브러리에 있는 데이터베이스 드라이버를 찾아서 해당 들이버를 제공해준다.
 */
public class ConnectionConst {
    public static final String URL = "jdbc:h2:tcp://localhost/~/TestDataBaseAccess";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";

}
