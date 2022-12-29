package com.heaven.palace.purplecloudpalace.common.constant.state;

/**
 * 数据库排序
 *
 * @author ZhouShengEn
 * @Date 2022年8月25日
 */
public enum Order {

    ASC("asc"), DESC("desc");

    private String des;

    Order(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
