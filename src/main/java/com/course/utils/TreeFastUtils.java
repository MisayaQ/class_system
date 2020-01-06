package com.course.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeFastUtils {
    // list以tree归类
    public static <T extends BeanTree> List<T> getTree(List<T> treeList) {
        List<T> result = new ArrayList<>();
        //获取所有顶级节点
        List<T> treeRoot = getTreeRoot(treeList);
        //移除顶级节点
        treeList.removeAll(treeRoot);
        //遍历获取子节点
        treeRoot.forEach(root -> result.add(getChild(treeList, root)));
        return result;
    }

    /**
     * 获取所有顶级节点
     *
     * @param nodeList
     * @return
     */
    private static <T extends BeanTree> List<T> getTreeRoot(List<T> nodeList) {
        return nodeList.stream().filter(menu -> {
            Boolean flag= (StringUtils.isBlank(menu.getParentId()) || "-1".equals(menu.getParentId()));
            if(flag){
                menu.setParentIds("0,");
            }
            return  flag;
        }).collect(Collectors.toList());
    }


    /**
     * 递归过获取无限级子节点
     *
     * @param nodeList
     * @param menu
     * @return
     */
    private static<T extends BeanTree> T getChild(List<T> nodeList, T menu) {
        List<T> childList = getChildList(nodeList, menu);
        if (CollectionUtils.isNotEmpty(childList)) {
            //移除已经遍历过的节点
            nodeList.removeAll(childList);
//            childList.forEach(child -> menu.getChildList().add(getChild(childList, child)));
            childList.forEach(child -> menu.getChildList().add(getChild(nodeList, child)));
        }
        return menu;
    }

    /**
     * 得到子节点列表
     * parentId为空或者Null 或者为-1 表示顶级节点
     * @param nodeList
     * @param menu 父节点
     * @param <T>
     * @return
     */
    private static  <T extends BeanTree> List<T> getChildList(List<T> nodeList, T menu) {
        return nodeList.stream().filter(menu_ -> {
            Boolean flag= StringUtils.isBlank(menu_.getParentId())? false : menu_.getParentId().equals(menu.getId());
            if(flag){
                menu_.setParentIds(menu.getParentIds() + menu.getId() +",");
            }
            return flag;
        }).collect(Collectors.toList());
    }
}
