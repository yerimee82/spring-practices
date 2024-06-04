package com.poscodx.guestbook.repository;

import com.poscodx.guestbook.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestbookRepositoryWithRawJdbc implements GuestbookRepository{
    private final DataSource dataSource;

    @Autowired
    public GuestbookRepositoryWithRawJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<GuestbookVo> findAll() {
        List<GuestbookVo> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select no, name, contents, DATE_FORMAT(reg_date, '%Y-%m-%d %H:%i') as formatted_reg_date from guestbook order by no");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                GuestbookVo vo = new GuestbookVo();
                vo.setNo(rs.getLong(1));
                vo.setName(rs.getString(2));
                vo.setContents(rs.getString(3));
                vo.setRegDate(rs.getString(4));
                result.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insert(GuestbookVo vo) {
        int result = 0;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("insert into guestbook (name, password, contents, reg_date) values (?, ?, ?, now())")) {
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getPassword());
            pstmt.setString(3, vo.getContents());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Long no, String password) {
        int result = 0;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where no = ? and password = ?")) {
            pstmt.setLong(1, no);
            pstmt.setString(2, password);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
