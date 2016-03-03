package com.xingfly.dao;

import com.xingfly.model.Article;
import com.xingfly.util.Pager;
import com.xingfly.model.dto.ArticleDto;
import com.xingfly.model.dto.ArticleLiteDto;

import java.util.List;

/**
 * Created by SuperS on 15/12/9.
 */
public interface ArticleDao {
    //文章分页查询
    public List<ArticleDto> search(Article article) throws Exception;

    //文章分页列表
    public List<ArticleDto> pagenation(Pager pager) throws Exception;

    //最近文章列表 article(id , title)
    public List<ArticleLiteDto> getRecentArticles() throws Exception;

    //具体文章 article(title,content,pubdate,category,clicks,content)
    public ArticleDto get(Integer id) throws Exception;

    //上一篇文章article(title)
    public ArticleLiteDto getPre(Integer id) throws Exception;

    //下一篇文章article(title)
    public ArticleLiteDto getNext(Integer id) throws Exception;

    //获取文章列表 article(title,pubdate)
    public List<ArticleLiteDto> getByCategory(int categoryId) throws Exception;

    //归档文章列表 article(title,pubdate)
    public List<ArticleLiteDto> archive() throws Exception;


    //更新文章点击数
    public void updateArticleClicks(Integer clicks, Integer id) throws Exception;

    //更新文章对象
    public void update(Article article) throws Exception;

    //添加文章对象
    public void save(Article article) throws Exception;

    //删除文章对象
    public void delete(Integer id) throws Exception;

    //获取数值
    public int count() throws Exception;


}
