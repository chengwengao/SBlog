package com.xingfly.dao.impl;

import com.xingfly.dao.ArticleDao;
import com.xingfly.model.Article;
import com.xingfly.model.Category;
import com.xingfly.util.Pager;
import com.xingfly.model.dto.ArticleDto;
import com.xingfly.model.dto.ArticleLiteDto;
import com.xingfly.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuperS on 15/12/11.
 */
@Service("articleDao")
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<ArticleDto> search(Article s_article) throws Exception {
        List<ArticleDto> articles = new ArrayList<>();
        String sql = "select * from t_article t1,t_category t2,t_user t3 where t1.categoryId=t2.categoryId and t1.userId = t3.userId "
                + "and title like '%"
                + s_article.getTitle()
                + "%' "
                + "ORDER BY pubDate DESC";
        try (Connection connection = sql2o.open()) {
            Table table = connection.createQuery(sql).executeAndFetchTable();
            for (Row row : table.rows()) {
                ArticleDto article = new ArticleDto();
                article.setId(row.getInteger("articleId"));
                article.setTitle(row.getString("title"));
                article.setContent(row.getString("content"));
                article.setMarkDown(row.getString("markDown"));
                article.setRemark(row.getString("remark"));
                article.setPubDate(row.getString("pubDate"));
                article.setPicture(row.getString("picture"));
                article.setClicks(row.getInteger("clicks"));
                article.setIsDraft(row.getInteger("isDraft"));
                UserDto userDto = new UserDto();
                userDto.setId(row.getInteger("userId"));
                userDto.setNickname(row.getString("nickname"));
                userDto.setWebsite(row.getString("website"));
                article.setUser(userDto);

                Category category = new Category();
                category.setId(row.getInteger("categoryId"));
                category.setName(row.getString("categoryName"));
                article.setCategory(category);

                articles.add(article);
            }
        }
        return articles;
    }

    @Override
    public List<ArticleDto> pagenation(Pager pageBean) throws Exception {
        String sql = "select * from t_article t1,t_category t2,t_user t3 where" +
                " t1.categoryId=t2.categoryId and t1.userId = t3.userId ORDER BY pubDate DESC limit "
                + pageBean.getStartIndex() + "," + pageBean.getPageSize();
        List<ArticleDto> articles = new ArrayList<>();
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).executeAndFetchTable();

            for (Row row : table.rows()) {
                ArticleDto article = new ArticleDto();
                article.setId(row.getInteger("articleId"));
                article.setTitle(row.getString("title"));
                article.setContent(row.getString("content"));
//                article.setMarkDown(row.getString("markDown"));
                article.setRemark(row.getString("remark"));
                article.setPubDate(row.getString("pubDate"));
                article.setPicture(row.getString("picture"));
                article.setClicks(row.getInteger("clicks"));
                article.setIsDraft(row.getInteger("isDraft"));

                UserDto userDto = new UserDto();
                userDto.setId(row.getInteger("userId"));
                userDto.setNickname(row.getString("nickname"));
                userDto.setWebsite(row.getString("website"));
                article.setUser(userDto);

                Category category = new Category();
                category.setId(row.getInteger("categoryId"));
                category.setName(row.getString("categoryName"));
                article.setCategory(category);
                articles.add(article);
            }
        }
        return articles;
    }

    @Override
    public List<ArticleLiteDto> getRecentArticles() throws Exception {
        List<ArticleLiteDto> articles = new ArrayList<>();
        String sql = "SELECT articleId,title,pubDate FROM t_article WHERE categoryId>0 ORDER BY pubDate desc limit 0,10";
        try (Connection connection = sql2o.open()) {
            Table table = connection.createQuery(sql).executeAndFetchTable();
            for (Row row : table.rows()) {
                ArticleLiteDto article = new ArticleLiteDto();
                article.setId(row.getInteger("articleId"));
                article.setTitle(row.getString("title"));
                article.setPubDate(row.getString("pubDate"));
                articles.add(article);
            }
        }
        return articles;
    }

    @Override
    public ArticleDto get(Integer id) throws Exception {
        String sql = "SELECT * FROM t_article t1,t_category t2,t_user t3 WHERE t1.categoryId=t2.categoryId AND t1.userId = t3.userId and t1.articleId=:id";
        ArticleDto article = new ArticleDto();
        try (Connection connection = sql2o.open()) {
            Table table = connection.createQuery(sql).addParameter("id", id).executeAndFetchTable();
            for (Row row : table.rows()) {
                article.setId(row.getInteger("articleId"));
                article.setTitle(row.getString("title"));
                article.setContent(row.getString("content"));
                article.setMarkDown(row.getString("markDown"));
                article.setRemark(row.getString("remark"));
                article.setPubDate(row.getString("pubDate"));
                article.setPicture(row.getString("picture"));
                article.setClicks(row.getInteger("clicks"));
                article.setIsDraft(row.getInteger("isDraft"));
                UserDto userDto = new UserDto();
                userDto.setId(row.getInteger("userId"));
                userDto.setNickname(row.getString("nickname"));
                userDto.setWebsite(row.getString("website"));
                article.setUser(userDto);

                Category category = new Category();
                category.setId(row.getInteger("categoryId"));
                category.setName(row.getString("categoryName"));
                article.setCategory(category);
            }
        }
        return article;
    }

    @Override
    public ArticleLiteDto getPre(Integer id) throws Exception {
        String sql = "SELECT * FROM t_article WHERE articleId = (SELECT articleId FROM t_article WHERE articleId < :id and categoryId > 0 ORDER BY articleId DESC LIMIT 1);";
        ArticleLiteDto article = new ArticleLiteDto();
        try (Connection connection = sql2o.open()) {
            Table table = connection.createQuery(sql).addParameter("id", id).executeAndFetchTable();
            for (Row row : table.rows()) {
                article.setId(row.getInteger("articleId"));
                article.setTitle(row.getString("title"));
            }

        }
        return article;
    }

    @Override
    public ArticleLiteDto getNext(Integer id) throws Exception {
        String sql = "SELECT * FROM t_article WHERE articleId = (SELECT articleId FROM t_article WHERE articleId > :id and categoryId > 0 ORDER BY articleId asc LIMIT 1);";
        ArticleLiteDto article = new ArticleLiteDto();
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).addParameter("id", id).executeAndFetchTable();
            for (Row row : table.rows()) {
                article.setId(row.getInteger("articleId"));
                article.setTitle(row.getString("title"));
            }
        }
        return article;
    }

    @Override
    public List<ArticleLiteDto> getByCategory(int categoryId) throws Exception {
        List<ArticleLiteDto> articles = new ArrayList<>();
        String sql = "SELECT articleId,title,pubDate FROM t_article WHERE categoryId=:id ORDER BY pubDate desc";
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).addParameter("id", categoryId).executeAndFetchTable();
            for (Row row : table.rows()) {
                ArticleLiteDto article = new ArticleLiteDto();
                article.setId(row.getInteger("articleId"));
                article.setTitle(row.getString("title"));
                article.setPubDate(row.getString("pubDate"));
                articles.add(article);
            }
        }
        return articles;
    }

    @Override
    public List<ArticleLiteDto> archive() throws Exception {
        List<ArticleLiteDto> articles = new ArrayList<>();
        String sql = "SELECT articleId,title,pubDate FROM t_article WHERE categoryId>0 ORDER BY pubDate desc";
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).executeAndFetchTable();
            for (Row row : table.rows()) {
                ArticleLiteDto article = new ArticleLiteDto();
                article.setId(row.getInteger("articleId"));
                article.setTitle(row.getString("title"));
                article.setPubDate(row.getString("pubDate"));
                articles.add(article);
            }
        }
        return articles;
    }


    @Override
    public void updateArticleClicks(Integer clicks, Integer id) throws Exception {
        String sql = "update t_article set clicks=:clicks where articleId=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("clicks", clicks)
                    .addParameter("id", id).executeUpdate();
        }
    }

    @Override
    public void update(Article article) throws Exception {
        String sql = "update t_article set categoryId=:categoryId,title=:title," +
                "content=:content,remark=:remark,markDown=:markDown where articleId=:articleId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("categoryId", article.getCategoryId())
                    .addParameter("title", article.getTitle())
                    .addParameter("content", article.getContent())
                    .addParameter("markDown", article.getMarkDown())
                    .addParameter("remark", article.getRemark())
                    .addParameter("articleId", article.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void save(Article article) throws Exception {
        String sql = "insert into t_article values(null,:categoryId,:userId,:title,:content,:markDown,:pubDate,0," +
                ":remark,:picture,:isDraft)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("categoryId", article.getCategoryId())
                    .addParameter("title", article.getTitle())
                    .addParameter("userId", article.getUserId())
                    .addParameter("content", article.getContent())
                    .addParameter("markDown", article.getMarkDown())
                    .addParameter("pubDate", article.getPubDate())
                    .addParameter("remark", article.getRemark())
                    .addParameter("picture", article.getPicture())
                    .addParameter("isDraft", article.getIsDraft())
                    .executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "delete from t_article where articleId=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("id", id)
                    .executeUpdate();
        }

    }

    @Override
    public int count() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM t_article WHERE categoryId > 0;";
        Integer count = 0;
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).executeAndFetchTable();
            for (Row row : table.rows()) {
                count = row.getInteger("total");
            }
        }
        return count;
    }
}
