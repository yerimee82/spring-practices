package com.poscodx.guestbook.service;

import com.poscodx.guestbook.repository.GuestbookLogRepository;
import com.poscodx.guestbook.repository.GuestbookRepository;
import com.poscodx.guestbook.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@Service
public class GuestbookService {

    private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;
    private final GuestbookRepository guestbookRepository;
    private final GuestbookLogRepository guestbookLogRepository;

    @Autowired
    public GuestbookService(DataSource dataSource, PlatformTransactionManager transactionManager, GuestbookRepository guestbookRepositoryImpl, GuestbookLogRepository guestbookLogRepository) {
        this.dataSource = dataSource;
        this.transactionManager = transactionManager;
        this.guestbookRepository = guestbookRepositoryImpl;
        this.guestbookLogRepository = guestbookLogRepository;
    }

    public List<GuestbookVo> getContentsList() {
        return guestbookRepository.findAll();
    }

    public void deleteContents(Long no, String password) {
        // TX:BEGIN //////////////
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            guestbookLogRepository.update(no);
            guestbookRepository.delete(no, password);

            // TX:END(SUCCESS) ///////
            transactionManager.commit(status);
        } catch(Throwable e) {
            // TX:END(Fail) ///////
            transactionManager.rollback(status);
        }
    }

    public void addContents(GuestbookVo vo) {
        // 트랜잭션 동기(Connection) 초기화
        TransactionSynchronizationManager.initSynchronization();

        Connection conn = DataSourceUtils.getConnection(dataSource);

        try {
            // TX:BEGIN //////////////
            conn.setAutoCommit(false);

            int count = guestbookLogRepository.update();

            if(count == 0) {
                guestbookLogRepository.insert();
            }

            guestbookRepository.insert(vo);

            // TX:END(SUCCESS) ///////
            conn.commit();
        } catch(Throwable e) {
            // TX:END(FAIL) ///////
            try {
                conn.rollback();
            } catch(SQLException ignored) {
            }
        } finally {
            DataSourceUtils.releaseConnection(conn, dataSource);
        }
    }
}
