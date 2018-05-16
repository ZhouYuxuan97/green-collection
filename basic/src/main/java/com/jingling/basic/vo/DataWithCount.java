package com.jingling.basic.vo;

import java.util.List;

/**
 * <p>用于携带列表信息以及结果总数</p>
 *
 * @Auther: zyy-finalcola
 * @Date: 2018-01-29 19:05
 */
public class DataWithCount {

    private List<? extends Object> data;  //数据列表
    private long count; //数据总条数

    public DataWithCount(List<? extends Object> data, long count) {
        this.data = data;
        this.count = count;
    }

    public DataWithCount() {
    }

    public List<? extends Object> getData() {
        return data;
    }

    public void setData(List<? extends Object> data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("count:").append(count).append("\n").append("data:\n");
        data.forEach(d->{
            stringBuilder.append(d.toString()).append(" ");
        });
        return stringBuilder.toString();
    }
}
