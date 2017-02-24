package com.admin.domain.repository;

import java.util.List;

import com.admin.domain.modle.Menu;


/**
 * @author Jonsy
 *
 */
public interface MenuRepository {

    void add(Menu menu);

    void update(Menu menu);

    Menu get(String code);

    boolean contains(String code);

    List<Menu> list();

    void remove(String code);

    void switchStatus(String code,boolean disabled);

    List<Menu> roleMenus(String roleId);

    List<Menu> getNavMenus(String userId);
}
