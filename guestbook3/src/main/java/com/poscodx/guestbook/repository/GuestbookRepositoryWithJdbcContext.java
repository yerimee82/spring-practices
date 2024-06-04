package com.poscodx.guestbook.repository;

import com.poscodx.guestbook.repository.template.JdbcContext;
import com.poscodx.guestbook.repository.template.StatementStrategy;
import com.poscodx.guestbook.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GuestbookRepositoryWithJdbcContext implements GuestbookRepository{
    private final JdbcContext jdbcContext;

    @Autowired
    public GuestbookRepositoryWithJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public int delete(Long no, String password) {
        return jdbcContext.executeUpdate(connection -> {
            PreparedStatement pstmt = connection.prepareStatement("delete from guestbook where no = ? and password = ?");
            pstmt.setLong(1, no);
            pstmt.setString(2, password);

            return pstmt;
        });
    }

    @Override
    public List<GuestbookVo> findAll() {
        return null;
    }

    public int insert(GuestbookVo vo) {
        return jdbcContext.executeUpdate(new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement("insert into guestbook (name, password, contents, reg_date) values (?, ?, ?, now())");
                pstmt.setString(1, vo.getName());
                pstmt.setString(2, vo.getPassword());
                pstmt.setString(3, vo.getContents());
                return pstmt;
            }
        });
    }
}
