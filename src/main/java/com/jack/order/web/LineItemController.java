package com.jack.order.web;

import com.jack.order.po.LineItem;
import com.jack.order.po.Menu;
import com.jack.order.service.LineItemService;
import com.jack.order.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LineItemController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private LineItemService lineItemService;

    @PostMapping("/lineItem")
    public String post(LineItem lineItem, Model model){
        Menu menu = menuService.findById(lineItem.getMenuId());
        Long resId = menu.getRestaurant().getId();
        lineItem.setMenu(menu);
        lineItemService.save(lineItem);
        model.addAttribute("message","成功加入");
        return "re :: reloadField";
    }

}
