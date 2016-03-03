package com.xingfly.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by SuperS on 16/2/28.
 * 访客留言页面 调用多说的API接口
 */
@Controller
@RequestMapping("/leave")
public class LeaveMsgController {
    //显示留言页面
    @RequestMapping(method = RequestMethod.GET)
    public String leavePage(ModelMap model){
        model.addAttribute("mainPage","user/leave/detail.vm");
        return "index";
    }
}
