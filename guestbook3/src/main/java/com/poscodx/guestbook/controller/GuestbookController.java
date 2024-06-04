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
    private final GuestbookRepository guestbookRepositoryWithJdbcTemplate;

    @Autowired
    public GuestbookController(
            @Qualifier("guestbookRepositoryWithRawJdbc") GuestbookRepository guestbookRepositoryWithRawJdbc,
            @Qualifier("guestbookRepositoryWithJdbcContext") GuestbookRepository guestbookRepositoryWithJdbcContext,
            @Qualifier("guestbookRepositoryWithJdbcTemplate") GuestbookRepository guestbookRepositoryWithJdbcTemplate) {
        this.guestbookRepositoryWithRawJdbc = guestbookRepositoryWithRawJdbc;
        this.guestbookRepositoryWithJdbcContext = guestbookRepositoryWithJdbcContext;
        this.guestbookRepositoryWithJdbcTemplate = guestbookRepositoryWithJdbcTemplate;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<GuestbookVo> list = guestbookRepositoryWithJdbcTemplate.findAll();
        model.addAttribute("list", list);

        return "index";
    }

    @RequestMapping("/add")
    public String add(GuestbookVo vo) {
        guestbookRepositoryWithJdbcTemplate.insert(vo);
        return "redirect:/";
    }

    @RequestMapping(value="/delete/{no}", method= RequestMethod.GET)
    public String delete(@PathVariable("no") Long no, Model model) {
        model.addAttribute("no", no);
        return "delete";
    }

    @RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
    public String delete(@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
        guestbookRepositoryWithJdbcTemplate.delete(no, password);
        return "redirect:/";
    }
}