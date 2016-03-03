package com.xingfly.controller.admin;

import com.xingfly.model.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by SuperS on 16/2/29.
 * 显示Manage主页
 */
@Controller
@RequestMapping("/manage")
public class ManageController {
    @RequestMapping(method = RequestMethod.GET)
    public String manageHome(ModelMap model, HttpSession session) {
        model.addAttribute("mainPage", "admin/home/home.vm");
        model.addAttribute("user", (UserDto) session.getAttribute("currentUser"));
        return "admin/index";
    }
}
