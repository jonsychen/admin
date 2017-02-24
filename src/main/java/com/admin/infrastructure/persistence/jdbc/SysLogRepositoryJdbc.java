package com.admin.infrastructure.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.admin.domain.modle.SysLog;
import com.admin.domain.repository.SysLogRepository;

import java.util.List;


/**
 * @author Jonsy
 *
 */
@Repository
public class SysLogRepositoryJdbc implements SysLogRepository {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void add(SysLog sysLog) {
        jdbcTemplate.update("INSERT syslog (uid,content,operation,createTime,user) VALUES (?,?,?,?,?)",sysLog.getUid(),sysLog.getContent(),sysLog.getOperation(),sysLog.getCreateTime(),sysLog.getUser());
    }

    @Override
    public List<SysLog> list() {
        return jdbcTemplate.query("select * from syslog", BeanPropertyRowMapper.newInstance(SysLog.class));
    }

    @Override
    public void clear() {
        jdbcTemplate.update("DELETE FROM syslog");
    }
}
