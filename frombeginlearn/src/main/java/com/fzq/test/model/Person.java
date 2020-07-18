package com.fzq.test.model;

/**
 * @description:
 * @author: 范子祺
 **/
public class Person {
    /*
    {id} 自增主键
    {name} 人员姓名
    {mobile} 人员电话
     */
    private int id;
    private String name;
    private String mobile;

    // 右键 Generate -> Setter and Getter -> Shift全选 -> ok 生成如下代码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // 右键 Generate -> toString() -> 全选 -> ok 生成如下代码

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
