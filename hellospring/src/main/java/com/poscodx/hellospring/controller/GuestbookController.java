package com.poscodx.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* @RequestMapping 클래스 단독 매핑
* Spring MVC 4.x 지원
* */
@RequestMapping("/guestbook/*")
public class GuestbookController {

    @ResponseBody
    @RequestMapping
    public String list() {
        return "GuestbookController.list()";
    }

    @ResponseBody
    @RequestMapping
    public String delete() {
        return "GuestbookController.delete()";
    }

}
