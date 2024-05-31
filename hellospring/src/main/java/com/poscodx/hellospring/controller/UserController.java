package com.poscodx.hellospring.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.poscodx.hellospring.vo.UserVo;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/user")
public class UserController {
    @ResponseBody
    @RequestMapping(value = "/joinform", method = RequestMethod.GET)
    public String joinform() {
        return "/WEB-INF/views/join.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(UserVo vo) {
        System.out.println("vo = " + vo);
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping( "/update")
    public String update(@RequestParam("n") String name) {
        // 만일 n 이라는 이름의 파라미터가 없으면 400 Bad Request Error 발생
        return "UserController.update(" + name + ")";
    }

    @ResponseBody
    @RequestMapping("/update2")
    public String update2(@RequestParam(value="n", required=true, defaultValue="") String name) {
        return "UserController.update(" + name + ")";
    }

    @ResponseBody
    @RequestMapping("/list")
    public String list(@RequestParam(value="p", required=true, defaultValue="1") int pageNo) {
        return "UserController.list(" + pageNo + ")";
    }
}