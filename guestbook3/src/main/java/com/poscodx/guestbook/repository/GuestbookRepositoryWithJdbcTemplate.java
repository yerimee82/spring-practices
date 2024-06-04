package com.poscodx.guestbook.repository;

import com.poscodx.guestbook.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GuestbookRepositoryWithJdbcTemplate implements GuestbookRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuestbookRepositoryWithJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GuestbookVo> findAll() {
        return jdbcTemplate.query("select no, name, contents, date_format(reg_date, '%Y-%m-%d %H:%i') as formatted_reg_date from guestbook order by formatted_reg_date",
                new RowMapper<GuestbookVo>() {
                    @Override
                    public GuestbookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        GuestbookVo vo = new GuestbookVo();
                        vo.setNo(rs.getLong(1));
                        vo.setName(rs.getString(2));
                        vo.setContents(rs.getString(3));
                        vo.setRegDate(rs.getString(4));
                        return vo;
                    }
                });
    }

    @Override
    public int insert(GuestbookVo vo) {
        return jdbcTemplate.update("insert into guestbook (name, password, contents, reg_date) values (?, ?, ?, now())",
                vo.getName(), vo.getPassword(), vo.getContents());
    }

    @Override
    public int delete(Long no, String password) {
        return jdbcTemplate.update("delete from guestbook where no = ? and password = ?",
                no, password);
    }
}
