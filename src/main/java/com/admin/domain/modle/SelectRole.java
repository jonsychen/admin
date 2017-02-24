package com.admin.domain.modle;

/**
 * 用户分配角色功能中列出角色，用户已经具备的角色checked=true
 *
 * @author Jonsy
 */
public class SelectRole {

    private String rid;//role id

    private String name;//role name

    private boolean checked;

    public SelectRole() {
    }

    public SelectRole(String rid, String name, boolean checked) {
        this.rid = rid;
        this.name = name;
        this.checked = checked;
    }

    public String getRid() {
        return rid;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getName() {
        return name;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
