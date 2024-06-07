package com.poscodx.guestbook.repository;

import com.poscodx.guestbook.repository.template.JdbcContext;
import com.poscodx.guestbook.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuestbookRepositoryImpl implements GuestbookRepository{
    private final JdbcContext jdbcContext;

    @Autowired
    public GuestbookRepositoryImpl(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    @Override
    public List<GuestbookVo> findAll() {
        return jdbcContext.query(
                "select no, name, contents, date_format(reg_date, '%Y/%m/%d %H:%i:%s') from guestbook order by reg_date desc",
                (rs, rowNum) -> {
                    GuestbookVo vo = new GuestbookVo();
                    vo.setNo(rs.getLong(1));
                    vo.setName(rs.getString(2));
                    vo.setContents(rs.getString(3));
                    vo.setRegDate(rs.getString(4));
                    return vo;
                });
    }

    public int delete(Long no, String password) {
        return jdbcContext.update("delete from guestbook where no = ? and password = ?", new Object[]{no, password});
    }

    public int insert(GuestbookVo vo) {
        return jdbcContext.update("insert into guestbook (name, password, contents, reg_date) values (?, ?, ?, now())", new Object[]{vo.getName(), vo.getPassword(), vo.getContents()});
    }
}
