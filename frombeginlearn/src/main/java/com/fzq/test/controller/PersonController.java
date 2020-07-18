package com.fzq.test.controller;

import com.fzq.test.model.Person;
import com.fzq.test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @description:
 * @author: 范子祺
 **/
@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    // 设置访问路由值为路径
    @RequestMapping("/")
    public ModelAndView index(){
        // 顾名思义 实体和数据 同时返回页面模板和数据
        ModelAndView mav = new ModelAndView("index");
        List<Person> list = personService.getAll();
        System.out.println(list);
        mav.addObject("list",list);
        return mav;
    }
}
