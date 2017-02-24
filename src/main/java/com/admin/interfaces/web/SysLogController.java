package com.admin.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.application.SysLogService;


/**
 * @author Jonsy
 *
 */
@Controller
@RequestMapping("/syslog")
public class SysLogController {

    @Autowired
    protected SysLogService sysLogService;

    

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public void clear(){
         sysLogService.clear();
    }

   
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model)
    {
        model.addAttribute("list",sysLogService.list());
        return "/syslog/list";
    }


}
