package com.admin.infrastructure.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.admin.domain.modle.Resource;
import com.admin.domain.repository.ResourceRepository;

import java.util.List;


/**
 * @author Jonsy
 *
 */
@Repository
public class ResourceRepositoryJdbc implements ResourceRepository{

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void add(Resource resource) {
        jdbcTemplate.update("INSERT INTO resource (id,title,disabled,url,description) VALUES (?,?,?,?,?)",resource.getId(),resource.getTitle(),resource.isDisabled()?1:0,resource.getUrl(),resource.getDescription());
    }

    @Override
    public void update(Resource resource) {
        jdbcTemplate.update("UPDATE resource SET title=?,disabled=?,url=?,description=? WHERE `id`=?",resource.getTitle(),resource.isDisabled()?1:0,resource.getUrl(),resource.getDescription(),resource.getId());
    }

    @Override
    public Resource get(String id) {
        return jdbcTemplate.queryForObject("select * from resource where id=?",BeanPropertyRowMapper.newInstance(Resource.class),id);
    }

    @Override
    public List<Resource> list() {
        return jdbcTemplate.query("select * from resource",BeanPropertyRowMapper.newInstance(Resource.class));
    }

    @Override
    public void remove(String id) {
        jdbcTemplate.update("DELETE FROM resource WHERE id=?",id);
    }

    public void switchStatus(String id,boolean disabled){
        jdbcTemplate.update("update resource SET disabled=? WHERE id=?",disabled?1:0,id);
    }


    @Override
    public List<Resource> listByRole(String roleId) {
        return jdbcTemplate.query("select re.* from  role_resource rr  join resource re on re.id=rr.resource_id where rr.role_id=?", BeanPropertyRowMapper.newInstance(Resource.class), roleId);
    }


//    @Override
//    public List<Resource> getUrlResources(String userId) {
//        return jdbcTemplate.query("select r.* from resource r join role_resource rr on r.id=rr.resource_id join user_role ur on rr.role_id=ur.role_id where ur.uid=?",BeanPropertyRowMapper.newInstance(Resource.class),userId);
//    }

    @Override
    public List<Resource> getEnableResources() {
        return jdbcTemplate.query("select * from resource WHERE disabled=0",BeanPropertyRowMapper.newInstance(Resource.class));

    }
}
