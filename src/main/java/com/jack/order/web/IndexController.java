package com.jack.order.web;

import com.jack.order.service.ResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    ResService resService;

    @GetMapping("/")
    public String index(@PageableDefault(page=0,size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",resService.findAllByPage(pageable));
        return "Res";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
