package com.admin.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.admin.domain.modle.SysLog;
import com.admin.domain.repository.SysLogRepository;

import java.util.List;


/**
 * @author Jonsy
 *
 */
@Service
public class SysLogService {

    @Autowired
    protected SysLogRepository sysLogRepository;

    public void create(SysLog sysLog){
        sysLogRepository.add(sysLog);
    }

    @CacheEvict(value = "syslog", allEntries = true)
    public void clear(){
        sysLogRepository.clear();
    }

    @Cacheable(value = "syslog")
    public List<SysLog> list(){
        return sysLogRepository.list();
    }
}
