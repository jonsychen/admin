package com.admin.domain.repository;

import java.util.List;

import com.admin.domain.modle.Role;

/**
 * @author Jonsy
 *
 */
public interface RoleRepository {

    void add(Role role);

    void update(Role role);

    void updateMenus(String rid, List<String> mids);

    void updateResources(String rid, List<String> resources);

    boolean contains(String roleName);

    Role get(String id);

    List<Role> list();

    void remove(String id);

    void removeRoleMenuByMenuId(String menuId);

    void removeRoleResourceByResourceId(String resourceId);

    void switchStatus(String id,boolean disabled);

    List<Role> getRoles(String userId);


}
