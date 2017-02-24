package com.admin.interfaces.web;

import com.admin.application.RoleService;
import com.admin.domain.modle.Role;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @author Jonsy
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    protected RoleService roleService;

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public String create(Role role){
        roleService.create(role);
        return "redirect:/role";
    }

    @RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
    public String modify(@PathVariable("id") String id, Role role) {
        role.setId(id);
        roleService.modify(role);
        return "redirect:/role";
    }

    @RequestMapping(value = "/{id}/status",method = RequestMethod.PUT)
    @ResponseBody
    public void switchStatus(@PathVariable("id") String id,@RequestParam("disable") boolean disable){
        roleService.switchStatus(id,disable);
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id")String id){
         roleService.delete(id);
    }

    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String toform(@RequestParam(value = "id",required = false)String id, Model model){
        String api="/role/add";
        if(StringUtils.isNotBlank(id)){
            model.addAttribute("role",roleService.get(id));
            api="/role/"+id+"/modify";
        }
        model.addAttribute("api",api);

        return "role/form";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("list",roleService.list());
        return "role/list";
    }

    @RequestMapping(value = "/{id}/select-resource", method = RequestMethod.GET)
    public String selectRole(@PathVariable("id") String id,Model model) {
        model.addAttribute("list",roleService.selectResources(id));
        model.addAttribute("api","/role/"+id+"/grant-resource");
        return "role/grant-resource";
    }

    @RequestMapping(value = "/{id}/select-menu", method = RequestMethod.GET)
    public String selectMenu(@PathVariable("id") String id,Model model) {
        model.addAttribute("list",roleService.selectMenus(id));
        model.addAttribute("api","/role/"+id+"/grant-menu");
        return "role/grant-menu";
    }

    //为角色分配资源
    @RequestMapping(value = "/{id}/grant-resource",method = RequestMethod.POST)
    public String grantResources(@PathVariable("id") String id,  String[] rid) {
        if(rid==null){
           rid=new String[0];
        }
        roleService.grantResource(id, Lists.newArrayList(rid));
        return "redirect:/role";
    }

    //为角色分配菜单
    @RequestMapping(value = "/{id}/grant-menu",method = RequestMethod.POST)
    public String grantMenu(@PathVariable("id") String id, String[] mid ) {
        if(mid==null){
            mid=new String[0];
        }
        roleService.grantMenu(id,Lists.newArrayList(mid));
        return "redirect:/role";
    }

}
