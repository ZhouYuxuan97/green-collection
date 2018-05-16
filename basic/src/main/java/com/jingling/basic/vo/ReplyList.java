package com.jingling.basic.vo;

import java.util.List;

/**
 * 返回一个list类型的结果的vo
 */
public class ReplyList <T> {

    private List<T> list;

    private int count;

    public ReplyList(List<T> list, int count) {
        this.list = list;
        this.count = count;
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
