package com.poscodx.guestbook.repository;

import com.poscodx.guestbook.repository.template.JdbcContext;
import org.springframework.stereotype.Repository;

@Repository
public class GuestbookLogRepository {
    private final JdbcContext jdbcContext;

    public GuestbookLogRepository(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public int insert() {
        return jdbcContext.update("insert into guestbook_log values (current_date(), 1)");
    }

    public int update() {
        return jdbcContext.update("update guestbook_log set count = count + 1 where date = current_date()");
    }

    public int update(Long no) {
        return jdbcContext.update("update guestbook_log set count = count - 1 where date = (select date(reg_date) from guestbook where no = ?)", new Object[]{no});
    }
}
