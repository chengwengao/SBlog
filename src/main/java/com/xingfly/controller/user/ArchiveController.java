package com.xingfly.controller.user;

import com.xingfly.model.dto.ArticleLiteDto;
import com.xingfly.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by SuperS on 16/2/28.
 * 访客 归档页面
 */
@Controller
@RequestMapping("archive")
public class ArchiveController {
    @Autowired
    private ArticleService articleService;
     //显示归档页面
    @RequestMapping(method = RequestMethod.GET)
    public String archive(ModelMap model){
        List<ArticleLiteDto> articleLiteDtos = articleService.getArchive();
        model.addAttribute("articles", articleLiteDtos);
        model.addAttribute("mainPage", "user/archive/detail.vm");
        return "index";
    }
}
