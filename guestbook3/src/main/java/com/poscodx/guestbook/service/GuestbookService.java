package com.poscodx.guestbook.service;

import com.poscodx.guestbook.repository.GuestbookLogRepository;
import com.poscodx.guestbook.repository.GuestbookRepository;
import com.poscodx.guestbook.vo.GuestbookVo;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.util.List;


@Service
public class GuestbookService {
    private final GuestbookRepository guestbookRepository;
    private final GuestbookLogRepository guestbookLogRepository;

    @Autowired
    public GuestbookService(GuestbookRepository guestbookRepositoryImpl, GuestbookLogRepository guestbookLogRepository) {
        this.guestbookRepository = guestbookRepositoryImpl;
        this.guestbookLogRepository = guestbookLogRepository;
    }

    public List<GuestbookVo> getContentsList() {
        return guestbookRepository.findAll();
    }

    public void deleteContents(Long no, String password) {
        guestbookLogRepository.update(no);
        guestbookRepository.delete(no, password);
    }

    public void addContents(GuestbookVo vo) {
        TransactionSynchronizationManager.initSynchronization();

        Connection conn = DataSourceUtils.getConnection(da)
        int count = guestbookLogRepository.update();

        if(count == 0) {
            guestbookLogRepository.insert();
        }
        guestbookRepository.insert(vo);

    }
}
