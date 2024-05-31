package com.poscodx.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @ResponseBody
    @RequestMapping("/board/write")
    public String write() {
        return "BoardController.write()";
    }

    @ResponseBody
    @RequestMapping("/board/view/{no}")
    public String view(@PathVariable("no") Long number) {
        return "BoardController.view(" + number +")";
    }

}