package com.course.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeanTree implements Serializable {
    private String id;
    /**
     * parentId为空 或者 -1 即表示 顶级节点
     */
    private String parentId;
    private String parentIds;
    private List<BeanTree> childList = new ArrayList<>();
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }



    public List<BeanTree> getChildList() {
        return childList;
    }

    public void setChildList(List<BeanTree> childList) {
        this.childList = childList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}