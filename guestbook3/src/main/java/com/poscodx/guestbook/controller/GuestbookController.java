package com.poscodx.guestbook.controller;

import com.poscodx.guestbook.service.GuestbookService;
import com.poscodx.guestbook.vo.GuestbookVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GuestbookController {
    private final GuestbookService guestbookService;

    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }


    @RequestMapping("/")
    public String index(Model model) {
        List<GuestbookVo> list = guestbookService.findAll();
        model.addAttribute("list", list);

        return "index";
    }

    @RequestMapping("/add")
    public String add(GuestbookVo vo) {
        guestbookService.insert(vo);
        return "redirect:/";
    }

    @RequestMapping(value="/delete/{no}", method= RequestMethod.GET)
    public String delete(@PathVariable("no") Long no, Model model) {
        model.addAttribute("no", no);
        return "delete";
    }

    @RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
    public String delete(@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
        guestbookService.deleteContents(no, password);
        return "redirect:/";
    }
}