package com.fzq.test.dao;

import com.fzq.test.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @description:
 * @author: 范子祺
 */
@Mapper
public interface PersonDao {
//    查所有

    List<Person> getAll();

    /*
    根据ID查询
    {id} 要查询人员的 id
     */
    Person getPersonById(@Param("id") int id);

    /*
    删除
    {id} 要删除人员的 id
     */
        void delete(@Param("id") int id);

    /*
    更新
    {p} 要更新的Person实例
     */
        void update(Person p);

    /*
    增加
    {p} 要新增的Person实例
     */
        void newp(Person p);

}

