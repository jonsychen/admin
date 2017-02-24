package com.admin.interfaces.facade.commondobject;


/**
 * @author Jonsy
 *
 */
public class MenuCreateCommand {

    private String id;

    private String label;

    private String path="0";  //父节点的路径与父节点的id路径，用","分开，0表示父节点是根节点

    private int order=1;  //排序

    private String url;

    private int type = 1;//扩展字段。菜单类型，供不同业务区分

    private String style;//样式，方便ui展现

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
