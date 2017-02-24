package com.admin.infrastructure;


import org.springframework.beans.BeanUtils;


/**
 * @author Jonsy
 *
 */
public class BeanUtil {

    public static void copeProperties(Object from,Object dest){
        try {
            BeanUtils.copyProperties(from, dest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
