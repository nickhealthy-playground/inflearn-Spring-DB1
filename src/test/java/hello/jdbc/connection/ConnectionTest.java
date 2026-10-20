package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        // [Test worker] INFO hello.jdbc.connection.ConnectionTest -- connection=conn0: url=jdbc:h2:tcp://localhost/~/Documents/dev/tools/h2/db-files/test user=SA, class=class org.h2.jdbc.JdbcConnection
        // [Test worker] INFO hello.jdbc.connection.ConnectionTest -- connection=conn1: url=jdbc:h2:tcp://localhost/~/Documents/dev/tools/h2/db-files/test user=SA, class=class org.h2.jdbc.JdbcConnection
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

    @Test
    void dataSourceDriverManager() throws SQLException {
        // DriverManagerDataSource - 항상 새로운 커넥션 획득(DataSource 인터페이스(추상화)만 사용하려고 따로 만든 클래스)
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());

    }

}