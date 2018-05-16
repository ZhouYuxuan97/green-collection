package com.jingling.basic.vo;

/**
 * <p>key-value</p>
 *
 * @Auther: zyy-finalcola
 * @Date: 2018-01-23 21:38
 */
public class KeyValue {

    private Object key;

    private Object value;

    public KeyValue(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public KeyValue() {
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "KeyValue{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
