package com.admin.domain.modle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jonsy
 * id   label   path        level order
 * 1    水果      0           1   1
 * 2    苹果      0,1         2   1
 * 3    梨子      0,1         2   2
 * 4    雪梨      0,1,3       3   1
 * 5    鸭梨      0,1,3       3   2
 */
public class TreeModel {

    private String id;

    private String label;

    private String path="0";  //父节点的路径与父节点的id路径，用","分开，0表示父节点是根节点

    private int order=1;  //排序

    private int type;//扩展字段。菜单类型，供不同业务区分

    private String style;//样式，方便ui展现

    /** 状态 是否禁用*/
    private boolean disabled;

    private List<? extends TreeModel> childNodes=new ArrayList<>();

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

    public int getLevel() {
        if(path==null){
            return 1;
        }
        return path.split(",").length;
    }


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TreeModel newChildNode(String nodeId, String label, int order){
        TreeModel node=new TreeModel();
        node.path =this.path +","+this.id;
        node.id=nodeId;
        node.order=order;
        node.label=label;
        return node;
    }

    public List<? extends TreeModel> getChildNodes() {
        return childNodes;
    }

    @Override
    public String toString() {
        return label+"-"+ path +"-"+id+"-"+order;
    }

    /***
     * 以level==1的节点作为开始节点构建树结构
     * @param nodes
     * @return
     */
    public static List<? extends TreeModel> buildTree(List<? extends TreeModel> nodes){
        if (isEmpty(nodes)){
            return null;
        }
        List<? extends TreeModel> firstLevels=nodes.stream().filter(node->!node.isDisabled() && node.getLevel()==1).collect(Collectors.toList());
        sortByOrder(firstLevels);
        firstLevels.stream().forEach(node-> setChildren(node,nodes));
        return firstLevels;
    }


    private static  void setChildren(TreeModel currentNode, List<? extends TreeModel> nodeList){
        List<? extends TreeModel> childrens=nodeList.stream().filter(node->(!node.isDisabled() && node.getPath().equals(currentNode.getPath()+","+currentNode.getId()))).collect(Collectors.toList());
        currentNode.childNodes=childrens;
        if (isEmpty(childrens)){
            return;
        }
        sortByOrder(childrens);
        childrens.stream().forEach(node-> setChildren(node,nodeList));

    }

    private static void sortByOrder(List<? extends TreeModel> firstLevels) {
        firstLevels.sort((node1,node2)->Integer.valueOf(node1.getOrder()).compareTo(Integer.valueOf(node2.getOrder())));
    }


    /***
     * 按数结构给节点排序
     * @param nodes
     */
    public static void sortByTree(List<? extends TreeModel> nodes) {
        if(isEmpty(nodes)){
            return;
        }
        sortByOrder(nodes);
        nodes.sort((o1, o2) -> (o1.getPath()+","+o1.getId()).compareTo(o2.getPath()+","+o2.getId()));
    }

    private static boolean isEmpty(List nodes) {
        return nodes == null || nodes.isEmpty();
    }


    //按节点的父子层次顺序展示
    private static void printTreeToConsole(List<TreeModel> nodes){
        if (isEmpty(nodes)){
            return;
        }

        sortByTree(nodes);

        nodes.stream().forEach(node->{
            if(node.isDisabled()){
                return;
            }
            for(int i=1;i<node.getLevel();i++){
                System.out.print("\t");
            }
            System.out.println(node);
        });
    }


    //以第一层为起点，递归方式展示父子层次树
    private static void printFirstLevelTreeToConsole(List<? extends TreeModel> nodes){
        if (isEmpty(nodes)){
            return;
        }
        nodes.forEach(item->{
            if(item.isDisabled()){
                return;
            }
            for(int i=1;i<item.getLevel();i++){
                System.out.print("\t");
            }
            System.out.println(item);

            printFirstLevelTreeToConsole(item.getChildNodes());
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeModel treeModel = (TreeModel) o;

        return id != null ? id.equals(treeModel.id) : treeModel.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static void main(String[] arg){
        List<TreeModel> nodes=new ArrayList<>();
        TreeModel fruit=new TreeModel();
        fruit.setId("1");
        fruit.setLabel("水果");
        fruit.setOrder(2);
        nodes.add(fruit);

        TreeModel apple=fruit.newChildNode("7","苹果",2);
        nodes.add(apple);
        nodes.add(apple.newChildNode("4","红富士",2));
        nodes.add(apple.newChildNode("15","山东苹果",1));

        TreeModel lizi=fruit.newChildNode("e8","梨子",1);
        nodes.add(lizi);
        nodes.add(lizi.newChildNode("7r7","雪梨",1));
        nodes.add(lizi.newChildNode("t31o","鸭梨",2));

        TreeModel shucai=new TreeModel();
        shucai.setId("a101");
        shucai.setLabel("蔬菜");
        shucai.setOrder(1);
        shucai.setDisabled(true);
         nodes.add(shucai);
        nodes.add(shucai.newChildNode("213","白菜",2));
        printTreeToConsole(nodes);

        System.out.println("====================");
        List<? extends TreeModel> tree=TreeModel.buildTree(nodes);
        printFirstLevelTreeToConsole(tree);






    }

}
