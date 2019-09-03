package com.temptation.util;

import com.temptation.pojo.GroupManagement;
import com.temptation.pojo.ListManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/3.
 * 树状json工具类
 */
public class TreeUtil {
    /**
     * 分组
     * @param treeNodes
     * @return
     */
    public static List<GroupManagement> RecursiveAddress(List<GroupManagement> treeNodes) {
        List<GroupManagement> trees = new ArrayList<GroupManagement>();
        for (GroupManagement treeNode : treeNodes) {
            if ("".equals(treeNode.getParentId()) || null == treeNode.getParentId() || -1==treeNode.getParentId()) {
                trees.add(findAddressChildren(treeNode, treeNodes));
            }
            }
            return trees;
        }


/** 
      * 递归查找地址子节点 
      * @param treeNodes 
      * @return 
      */
    public static GroupManagement findAddressChildren(GroupManagement treeNode, List<GroupManagement> treeNodes) {
        for (GroupManagement it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<GroupManagement>());
                }
                treeNode.getChildren().add(findAddressChildren(it, treeNodes));
            }
        }
        return treeNode;
 }




    /**
     * 列表
     * @param treeNodes
     * @return
     */
    public static List<ListManagement> queryListManagement(List<ListManagement> treeNodes) {
        List<ListManagement> trees = new ArrayList<ListManagement>();
        for (ListManagement treeNode : treeNodes) {
            if ("".equals(treeNode.getParentId()) || null == treeNode.getParentId() || -1==treeNode.getParentId()) {
                trees.add(findListManagementChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /** 
          * 递归查找地址子节点 
          * @param treeNodes 
          * @return 
          */
    public static ListManagement findListManagementChildren(ListManagement treeNode, List<ListManagement> treeNodes) {
        for (ListManagement it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<ListManagement>());
                }
                treeNode.getChildren().add(findListManagementChildren(it, treeNodes));
            }
        }
        return treeNode;
    }


    /**
     * 根据id递归查找所有子节点的数据
     */
    public static List<ListManagement> queryChildListManagement(List<ListManagement> treeNodes) {
        List<ListManagement> trees = new ArrayList<ListManagement>();
        for (ListManagement treeNode : treeNodes) {
            if ("".equals(treeNode.getParentId()) || null == treeNode.getParentId() || -1==treeNode.getParentId()) {
                trees.add(findListManagementChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }



}

