package com.xingfly.controller;

import com.xingfly.service.WebAppService;
import com.xingfly.util.Pager;
import com.xingfly.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SuperS on 16/2/26.
 * 网站首页 负责显示文章列表 分页数为4
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private WebAppService webAppService;
    //显示首页 分页文章列表
    @RequestMapping(method = RequestMethod.GET)
    public String home(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex, ModelMap model, HttpServletRequest request) {
        Pager pager = new Pager(pageIndex, webAppService.getWebDtoWebApp(1).getUserPageArticleSize(), articleService.count());
        model.addAttribute("mainPage", "user/article/articlelist.vm");
        model.addAttribute("webAppDto",webAppService.getWebDtoWebApp(1));

        model.addAttribute("articles", articleService.getPageArticles(pager));
        model.addAttribute("pager", pager);
        return "index";
    }
}