package com.admin.interfaces.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.application.MenuService;
import com.admin.application.UserService;
import com.admin.domain.modle.User;
import com.admin.interfaces.facade.assembler.UserAssembler;
import com.admin.interfaces.facade.commondobject.ProfileCommand;
import com.admin.interfaces.facade.commondobject.UserCommond;
import com.admin.security.SecurityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;


/**
 * @author Jonsy
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected MenuService menuService;

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public String create( UserCommond user){
        userService.create(UserAssembler.commondToDomain(user));
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
    public String modify(@PathVariable("id") String id,  UserCommond user) {
        userService.modify(UserAssembler.commondToDomain(id, user));
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id}/status",method = RequestMethod.PUT)
    @ResponseBody
    public void switchStatus(@PathVariable("id") String id,@RequestParam("disable") boolean disable){
        userService.switchStatus(id,disable);
    }
    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id")String id){
         userService.delete(id);
    }

    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String form(@RequestParam(value = "id",required = false)String id, Model model){
        String api="/user/add";
        if(StringUtils.isNotBlank(id)){
            model.addAttribute("acount",UserAssembler.domainToDto(userService.get(id)));
            api="/user/"+id+"/modify";
        }
        model.addAttribute("api",api);
        return  "user/form";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("list",UserAssembler.domainToDto(userService.list()));
        return "user/list";
    }

    @RequestMapping(value = "/{id}/grant-role",method = RequestMethod.POST)
    public String grantRole(@PathVariable("id") String id,  String[] rid) {
        if(rid==null){
            rid=new String[0];
        }
        userService.grantRole(id, Lists.newArrayList(rid));
        return "redirect:/user";
    }


    @RequestMapping(value = "/{id}/select-role", method = RequestMethod.GET)
    public String selectRole(@PathVariable("id") String id,Model model) {
        model.addAttribute("list",userService.selectRoles(id));
        model.addAttribute("api","/user/"+id+"/grant-role");
        return "user/grant-role";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String myinfo() {
        return "user/profile";
    }

    @RequestMapping(value = "/modify-profile", method = RequestMethod.POST)
    public String modifyProfile( ProfileCommand myInfo) {
        this.userService.modify(UserAssembler.profileToDomain(SecurityUtil.getUid(), myInfo));
        SecurityUtil.getUser().setEmail(myInfo.getEmail());
        return "user/profile";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        return "login";
    }

    @RequestMapping(value = "/selectusername", method = RequestMethod.GET)
    @ResponseBody
    public String selectusername(String username) throws IOException {
        logger.debug("username:{}",username);
        boolean result = false;
        List<User> listuser = userService.getUserByUname(username);
        if (listuser.size() < 1) {
            result = true;
        }
        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    @RequestMapping(value = "/selectuseremail", method = RequestMethod.GET)
    @ResponseBody
    public String selectuseremail(String email) throws IOException {
        logger.debug("email:{}",email);
        boolean result = false;
        List<User> listuser = userService.getUserByEmail(email);
        if (listuser.size() < 1) {
            result = true;
        }
        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }



}
