package com.poscodx.guestbook.controller;

import com.poscodx.guestbook.repository.GuestbookRepository;
import com.poscodx.guestbook.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GuestbookController {
    private final GuestbookRepository guestbookRepositoryWithRawJdbc;
    private final GuestbookRepository guestbookRepositoryWithJdbcContext;

    @Autowired
    public GuestbookController(
            @Qualifier("guestbookRepositoryWithRawJdbc") GuestbookRepository guestbookRepositoryWithRawJdbc,
            @Qualifier("guestbookRepositoryWithJdbcContext") GuestbookRepository guestbookRepositoryWithJdbcContext) {
        this.guestbookRepositoryWithRawJdbc = guestbookRepositoryWithRawJdbc;
        this.guestbookRepositoryWithJdbcContext = guestbookRepositoryWithJdbcContext;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<GuestbookVo> list = guestbookRepositoryWithRawJdbc.findAll();
        model.addAttribute("list", list);

        return "index";
    }

    @RequestMapping("/add")
    public String add(GuestbookVo vo) {
        guestbookRepositoryWithJdbcContext.insert(vo);
        return "redirect:/";
    }

    @RequestMapping(value="/delete/{no}", method= RequestMethod.GET)
    public String delete(@PathVariable("no") Long no, Model model) {
        model.addAttribute("no", no);
        return "delete";
    }

    @RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
    public String delete(@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
        guestbookRepositoryWithJdbcContext.delete(no, password);
        return "redirect:/";
    }
}