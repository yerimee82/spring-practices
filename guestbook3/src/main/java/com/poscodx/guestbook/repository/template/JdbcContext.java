package com.poscodx.guestbook.repository.template;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T executeQueryForObject(String sql) {
        return null;
    }

    public <T> List<T> executeQueryForObject(String sql, Object[] parameter) {
        return null;
    }

    public int executeUpdate(String sql) {
        return executeUpdateWithStatementStrategy(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            return pstmt;
        });
    }

    public int executeUpdate(String sql, Object[] parameters) {
        return executeUpdateWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                for (int i = 0; i < parameters.length; i++) {
                    pstmt.setObject(i+1, parameters[i]);
                }
                return pstmt;
            }
        });
    }

    private int executeUpdateWithStatementStrategy (StatementStrategy statementStrategy) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            pstmt = statementStrategy.makeStatement(conn);



//        PreparedStatement pstmt = conn.prepareStatement("insert into guestbook values(null, ?, ?, ?, now())");
//                PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
//            pstmt1.setString(1, vo.getName());
//            pstmt1.setString(2, vo.getPassword());
//            pstmt1.setString(3, vo.getContents());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        } finally {
            try {
                if(pstmt != null) {
                    pstmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            }
            catch(SQLException e) {
                System.out.println("Error" + e);
            }
        }

        return result;
    }
}
