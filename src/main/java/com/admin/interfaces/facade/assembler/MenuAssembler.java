package com.admin.interfaces.facade.assembler;

import com.admin.domain.modle.Menu;
import com.admin.infrastructure.BeanUtil;
import com.admin.interfaces.facade.commondobject.MenuCreateCommand;
import com.admin.interfaces.facade.commondobject.MenuUpdateCommond;


/**
 * @author Jonsy
 *
 */
public class MenuAssembler {

    public static Menu updateCommendToDomain(String id, MenuUpdateCommond updateCommond) {
        Menu menu=new Menu();
      BeanUtil.copeProperties(updateCommond,menu);
        menu.setId(id);
        return menu;
    }

    public static Menu createCommendToDomain(MenuCreateCommand creteCommand){
        Menu menu=new Menu();
        BeanUtil.copeProperties(creteCommand,menu);
        return menu;
    }
}
