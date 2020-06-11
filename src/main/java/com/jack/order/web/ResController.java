package com.jack.order.web;

import com.jack.order.po.Menu;
import com.jack.order.po.Restaurant;
import com.jack.order.service.MenuService;
import com.jack.order.service.ResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResController {

    @Autowired
    private ResService resService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/res/input")
    public String inputPage(Model model) {
        model.addAttribute("re", new Restaurant());
        return "input";
    }

    @GetMapping("/res/input/{id}")
    public String inputId(@PathVariable Long id, Model model) {
        model.addAttribute("re", resService.findById(id));
        model.addAttribute("menus",menuService.findByResId(id));
        return "input";
    }


    @GetMapping("/res/{id}")
    public String resPage(@PathVariable Long id, Model model, @RequestParam(defaultValue = "") String type) {
        model.addAttribute("re", resService.findById(id));
        model.addAttribute("menus",menuService.findByResIdAndType(id,type));
        model.addAttribute("activeType",type);
        return "re";
    }



    //新方法
    @GetMapping("/res")
    //@PageableDefault 前端傳來的自動匹配成pageable，並設定好預設值
    //當page<0時會自動判定返回0
    public String list2(@PageableDefault(page=0,size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<Restaurant> page1 = resService.findAllByPage(pageable);
        model.addAttribute("page", page1);
        return "Res";
    }

    @PostMapping("/res")
    //前端post時formdata若符合Book2成員 可自動匹配參數
    //RedirectAttributes 才能跨過redirect傳值
    public String post(Restaurant re, final RedirectAttributes attributes) {

        String s = re.getMenuStrings();
        System.out.println("------------");
        System.out.println(s);
        System.out.println("------------");
        if (!"".equals(s) && s != null) {
            String[] menuStrings = s.split("/");
            List<Menu> menuList = new ArrayList<>();
            for(String menuString : menuStrings){
                if ("".equals(menuString) || menuString==null){
                    break;
                }
                String[] tmp = menuString.split(",");
                Menu menu = new Menu();
                if(Long.valueOf(tmp[0]) != 0){
                    menu.setId(Long.valueOf(tmp[0]));
                }
                menu.setType(tmp[1]);
                menu.setName(tmp[2]);
                menu.setPrice(Integer.valueOf(tmp[3]));
                menu.setRestaurant(re);
                menuList.add(menu);
            }
            re.setMenus(menuList);
        }

        Restaurant re1 = resService.save(re);
        if (re1 != null) {
            attributes.addFlashAttribute("message", "<" + re1.getName() + ">" + "新增成功");
        }
        return "redirect:/res";
    }

}
