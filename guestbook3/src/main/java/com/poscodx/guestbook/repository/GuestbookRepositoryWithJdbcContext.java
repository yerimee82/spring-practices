package com.poscodx.guestbook.repository;

import com.poscodx.guestbook.repository.template.JdbcContext;
import com.poscodx.guestbook.repository.template.StatementStrategy;
import com.poscodx.guestbook.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuestbookRepositoryWithJdbcContext implements GuestbookRepository{
    private final JdbcContext jdbcContext;

    @Autowired
    public GuestbookRepositoryWithJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public int delete(Long no, String password) {
        return jdbcContext.executeUpdate("delete from guestbook where no = ? and password = ?", new Object[] {no, password});
    }

    @Override
    public List<GuestbookVo> findAll() {
        return null;
    }

    public int insert(GuestbookVo vo) {
        return jdbcContext.executeUpdate("insert into guestbook (name, password, contents, reg_date) values (?, ?, ?, now())", new Object[]{vo.getName(), vo.getPassword(), vo.getContents()});
    }
}
