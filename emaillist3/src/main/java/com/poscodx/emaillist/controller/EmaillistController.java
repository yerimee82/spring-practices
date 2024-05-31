package com.poscodx.emaillist.controller;

import com.poscodx.emaillist.repository.EmaillistRepository;
import com.poscodx.emaillist.vo.EmailListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmaillistController {
    private final EmaillistRepository emaillistRepository;
    @Autowired
    public EmaillistController(EmaillistRepository emaillistRepository) {
        this.emaillistRepository = emaillistRepository;
    }
    @RequestMapping("/")
    public String index(Model model) {
        List<EmailListVo> list = emaillistRepository.findAll();
        model.addAttribute("list", list);
        return "/WEB-INF/views/index.jsp";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/WEB-INF/views/add.jsp";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(EmailListVo vo) {
        emaillistRepository.insert(vo);
        return "redirect:/";
    }
}
