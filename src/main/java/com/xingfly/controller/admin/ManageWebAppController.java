package com.xingfly.controller.admin;

import com.xingfly.model.WebApp;
import com.xingfly.model.dto.WebAppDto;
import com.xingfly.service.WebAppService;
import com.xingfly.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by SuperS on 16/3/16.
 */
@Controller
@RequestMapping("/manage/web")
public class ManageWebAppController {
    @Autowired
    private WebAppService webAppService;

    @RequestMapping(method = RequestMethod.GET)
    public String showWebAppPage(ModelMap model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("currentUser"));
        model.addAttribute("mainPage", "admin/home/home.vm");
        WebAppDto webAppDto = webAppService.getWebDtoWebApp(1);
        webAppDto.setArticleViews(webAppService.getArticlesView());
        model.addAttribute("webAppDto", webAppDto);
        model.addAttribute("string","1");
        return "admin/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editWebApp(WebApp webApp, ModelMap model, HttpSession session) {
        webApp.setId(1);
        model.addAttribute("user", session.getAttribute("currentUser"));
        if (webAppService.webAppNotNull()) {
            //表中只有1个webApp配置
            if(StringUtil.isEmpty(webApp.getWebName())){
                webApp.setWebName(webAppService.getWebDtoWebApp(1).getWebName());
            }

            if(StringUtil.isEmpty(webApp.getWebTitle())){
                webApp.setWebTitle(webAppService.getWebDtoWebApp(1).getWebTitle());
            }
            webAppService.updateWebApp(webApp);
        } else {
            webAppService.saveWebApp(webApp);
        }
        return "redirect:/manage/web";
    }
}
