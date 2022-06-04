package hello.jdbc.connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DBConnectionUtilTest {
    @Test
    public void connection() throws Exception{
        //given
        Connection connection = DBConnectionUtil.getConnection();
        //when
        //then
        assertThat(connection).isNotNull();
    }

}