package com.xingfly.controller.user;

import com.xingfly.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by SuperS on 16/2/28.
 * 访客About页面
 */
@Controller
public class AboutController {
    @Autowired
    private AboutService aboutService;

    //显示about页面
    @RequestMapping("/about")
    public String aboutPage(ModelMap model){
        model.addAttribute("mainPage","user/about/about.vm");
        if(aboutService.list().size() > 0) {
            model.addAttribute("about", aboutService.getAbout(aboutService.list().get(0).getId()));
        }
        return "index";
    }
}
