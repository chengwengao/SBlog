package com.xingfly.service.impl;

import com.xingfly.dao.ArticleDao;
import com.xingfly.model.Article;
import com.xingfly.util.Pager;
import com.xingfly.model.dto.ArticleDto;
import com.xingfly.model.dto.ArticleLiteDto;
import com.xingfly.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SuperS on 15/12/13.
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<ArticleDto> searchArticles(Article article) {
        List<ArticleDto> articleDtoList = null;
        try {
            articleDtoList = articleDao.search(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDtoList;
    }

    @Override
    public List<ArticleDto> getPageArticles(Pager pager) {
        List<ArticleDto> articleDtoList = null;
        try {
            articleDtoList = articleDao.pagenation(pager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDtoList;
    }

    @Override
    public List<ArticleLiteDto> getRecentArticles() {
        List<ArticleLiteDto> articleLiteDtos = null;
        try {
            articleLiteDtos = articleDao.getRecentArticles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleLiteDtos;
    }

    @Override
    public ArticleDto getArticle(Integer id) {
        ArticleDto articleDto = null;
        try {
            articleDto = articleDao.get(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDto;
    }

    @Override
    public ArticleLiteDto getPreArticle(Integer id) {
        ArticleLiteDto articleLiteDto = null;
        try {
            articleLiteDto = articleDao.getPre(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleLiteDto;
    }

    @Override
    public ArticleLiteDto getNextArticle(Integer id) {
        ArticleLiteDto articleLiteDto = null;
        try {
            articleLiteDto = articleDao.getNext(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return articleLiteDto;
    }

    @Override
    public List<ArticleLiteDto> getArticlesByCategory(int categoryId) {
        List<ArticleLiteDto> articleLiteDtos = null;
        try {
            articleLiteDtos = articleDao.getByCategory(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleLiteDtos;
    }

    @Override
    public List<ArticleLiteDto> getArchive() {
        List<ArticleLiteDto> articleLiteDtos = null;
        try {
            articleLiteDtos = articleDao.archive();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleLiteDtos;
    }


    @Override
    public void updateArticle(Article article) {
        try {
            articleDao.update(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveArticle(Article article) {
        try {
            articleDao.save(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteArticle(Integer id) {
        try {
            articleDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int count() {
        int count = 0;
        try {
            count = articleDao.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void updateClicks(Integer clicks, Integer id) {
        try {
            articleDao.updateArticleClicks(clicks, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
