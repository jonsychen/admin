package com.admin.interfaces.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.admin.application.MenuService;
import com.admin.interfaces.facade.assembler.MenuAssembler;
import com.admin.interfaces.facade.commondobject.MenuCreateCommand;
import com.admin.interfaces.facade.commondobject.MenuUpdateCommond;


/**
 * @author Jonsy
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    protected MenuService menuService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String create(MenuCreateCommand menu) {
        menuService.create(MenuAssembler.createCommendToDomain(menu));
        return "redirect:/menu";
    }


    @RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
    public String modify(@PathVariable("id") String id, MenuUpdateCommond menu) {
        menuService.modify(MenuAssembler.updateCommendToDomain(id, menu));
        return "redirect:/menu";
    }


    @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
    @ResponseBody
    public void switchStatus(@PathVariable("id") String id, @RequestParam("disable") boolean disable) {
        menuService.switchStatus(id,disable);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") String id) {
         menuService.delete(id);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String toform(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "parent", required = false) boolean parent, Model model) {
        String url = null;
        if (StringUtils.isNotBlank(id) && !parent) {
            model.addAttribute("menu", menuService.get(id));
            url = "/menu/" + id + "/modify";
        } else {
            url = "/menu/add";
            if (StringUtils.isNotBlank(id)) {
                model.addAttribute("parentPath", id);
            }
        }
        model.addAttribute("api", url);
        return "menu/form";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("list", menuService.list());
        return "menu/list";
    }


}
