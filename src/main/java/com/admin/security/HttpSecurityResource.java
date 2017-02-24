package com.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import com.admin.domain.modle.Resource;
import com.admin.domain.modle.Role;
import com.admin.domain.repository.ResourceRepository;
import com.admin.domain.repository.RoleRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 定义受保护的http资源
 * 默认情况下，需要在配置文件中定义url与所需的权限，不能从数据库加载
 * @author Jonsy
 */
@Component
public class HttpSecurityResource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected ResourceRepository resourceRepository;

    private AntPathMatcher pathMatcher=new AntPathMatcher();

    //访问某个资源object需要什么角色
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Collection<ConfigAttribute> attributes=new HashSet<>();
        FilterInvocation invocation=(FilterInvocation)object;//对于http资源来说，object是FilterInvocation
        List<Role> roles=roleRepository.list();
        if(CollectionUtils.isEmpty(roles)){
            return new HashSet<>();
        }
        String requestUrl=invocation.getRequestUrl();
        roles.stream().forEach(role -> {
            List<Resource> resources=resourceRepository.listByRole(role.getId());
            if(CollectionUtils.isEmpty(resources)){
                return;
            }
            resources.stream().filter(resource -> !resource.isDisabled()).forEach(resource -> {
                if(pathMatcher.match(resource.getUrl(),requestUrl)) {
                    attributes.add(new SecurityConfig(role.getName()));
                    return;
                }
            });

        });

        return attributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<Role> allHttpResources=roleRepository.list();
        Collection<ConfigAttribute> attributes=new HashSet<>();
        allHttpResources.stream().forEach(role -> {
            attributes.add(new SecurityConfig(role.getName()));
        });
        return attributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
