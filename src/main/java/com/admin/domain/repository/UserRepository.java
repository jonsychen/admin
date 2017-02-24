package com.admin.domain.repository;

import java.util.List;

import com.admin.domain.modle.User;


/**
 * @author Jonsy
 *
 */
public interface UserRepository {

    void add(User user);

    void update(User user);

    void updateRoles(String uid, List<String> rids);

    User get(String id);

    boolean contains(String name);

    List<User> list();

    boolean hasResourcePermission(String userId,String resourceCode);

    void remove(String id);

    void switchStatus(String id,boolean disabled);

    User findByUserName(String username);

    //判断用户名称是否重复
    List<User> getUserByUname(String username);

    //判断email是否重复
    List<User> getUserByEmail(String email);

}
