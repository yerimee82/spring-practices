package com.poscodx.guestbook.repository;

import com.poscodx.guestbook.vo.GuestbookVo;

import java.util.List;

public interface GuestbookRepository {
    int insert(GuestbookVo vo);
    int delete(Long no, String password);
    List<GuestbookVo> findAll();
}
