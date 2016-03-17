package com.xingfly.controller.user;

import com.xingfly.model.Category;
import com.xingfly.model.dto.ArticleLiteDto;
import com.xingfly.model.dto.CategoryDto;
import com.xingfly.service.ArticleService;
import com.xingfly.service.CategoryService;
import com.xingfly.service.WebAppService;
import com.xingfly.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by SuperS on 16/2/28.
 * 访客 分类页面
 */
@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private WebAppService webAppService;
//    //分页list 需要自己 写分页 的前端页面.
//    @RequestMapping("list/{pageIndex:[0-9]+}")
//    public String listPage(ModelMap model,@PathVariable("pageIndex")Integer pageIndex){
//        Pager pager = new Pager(pageIndex,10,categoryService.getCount());
//        List<CategoryDto> categories = categoryService.getPageCategories(pager);
//        model.addAttribute("mainPage","category/categoryList.vm");
//        model.addAttribute("pager",pager);
//        model.addAttribute("categories",categories);
//        return "index";
//    }

    //全部list
    @RequestMapping("list")
    public String list(ModelMap model) {
        model.addAttribute("webAppDto",webAppService.getWebDtoWebApp(1));
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("mainPage", "user/category/categoryList.vm");
        model.addAttribute("categories", categories);
        return "index";
    }

    //详情
    @RequestMapping("{categoryId:[0-9]+}")
    public String detail(@PathVariable("categoryId") Integer categoryId, ModelMap model) {
        model.addAttribute("webAppDto",webAppService.getWebDtoWebApp(1));

        Category category = categoryService.getCategory(categoryId);
        model.addAttribute("mainPage", "user/category/detail.vm");
        if (StringUtil.isNotEmpty(category.getName())) {
            List<ArticleLiteDto> articles = articleService.getArticlesByCategory(categoryId);
            if (articles.size() == 0) {
                articles = null;
            }
            model.addAttribute("category", category);
            model.addAttribute("articles", articles);
        } else {
            model.addAttribute("error", "找不到该分类");
        }
        return "index";
    }


}
