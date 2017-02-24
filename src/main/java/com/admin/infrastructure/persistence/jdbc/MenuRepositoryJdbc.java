package com.admin.infrastructure.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.admin.domain.modle.Menu;
import com.admin.domain.repository.MenuRepository;

import java.util.List;


/**
 * @author Jonsy
 *
 */
@Repository
public class MenuRepositoryJdbc implements MenuRepository{

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void add(Menu menu) {
        jdbcTemplate.update("INSERT menu (id,label,path,`level`,`order`,`url`,`type`,`style`,`disabled`) VALUES (?,?,?,?,?,?,?,?,?)", menu.getId(), menu.getLabel(), menu.getPath(), menu.getLevel(), menu.getOrder(), menu.getUrl(), menu.getType(), menu.getStyle(), menu.isDisabled() ? 1 : 0);
    }

    @Override
    public void update(Menu menu) {
        jdbcTemplate.update("update menu SET label=?,`order`=?,url=?,disabled=?,`type`=?,`style`=? WHERE id=?", menu.getLabel(), menu.getOrder(), menu.getUrl(), menu.isDisabled() ? 1 : 0, menu.getType(), menu.getStyle(), menu.getId());
    }

    @Override
    public Menu get(String id) {
        return jdbcTemplate.queryForObject("select * from menu where id=?",createMapper(),id);
    }

    @Override
    public boolean contains(String id) {
        return jdbcTemplate.queryForObject("select count(id) from menu where id=?",Integer.class,id)>0;
    }

    @Override
    public List<Menu> list() {
        return jdbcTemplate.query("select * from menu",createMapper());
    }

    @Override
    public void remove(String id) {       //删除父节点没必要删除所有子节点，因为删除父节点后子节点并不会被展示
        jdbcTemplate.update("DELETE FROM menu WHERE id=?",id);
    }

    public void switchStatus(String id,boolean disabled){ //禁用父节点没必要禁用所有子节点，同上
        jdbcTemplate.update("update menu SET disabled=? WHERE id=?",disabled?1:0,id);
    }

    private RowMapper<Menu> createMapper() {
//        return (rs, rowNum) -> {
//            Menu menu = new Menu();
//            menu.setId(rs.getString("id"));
//            menu.setLabel(rs.getString("label"));
//            menu.setUrl(rs.getString("url"));
//            menu.setDisabled(rs.getBoolean("disabled"));
//            menu.setPath(rs.getString("path"));
//            menu.setOrder(rs.getInt("order"));
//            menu.setType(rs.getInt("type"));
//            menu.setStyle(rs.getString("style"));
//            return menu;
//        };
        return BeanPropertyRowMapper.newInstance(Menu.class);
    }

    @Override
    public List<Menu> roleMenus(String roleId) {
        return jdbcTemplate.query("select m.* from menu m join role_menu rm on m.id=rm.menu_id where rm.role_id=?", createMapper(), roleId);
    }

    @Override
    public List<Menu> getNavMenus(String userId) {
        return jdbcTemplate.query("select m.* from menu m join role_menu rm on m.id=rm.menu_id join user_role ur on rm.role_id=ur.role_id where m.disabled=0 and ur.uid=?", createMapper(),userId);
    }
}
