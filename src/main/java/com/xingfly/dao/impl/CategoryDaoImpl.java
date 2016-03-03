package com.xingfly.dao.impl;

import com.xingfly.dao.CategoryDao;
import com.xingfly.model.Category;
import com.xingfly.util.Pager;
import com.xingfly.model.dto.CategoryDto;
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
@Service("categoryDao")
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private Sql2o sql2o;

    @Override
    public List<CategoryDto> all() throws Exception {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        String sql = "SELECT t_category.categoryId,t_category.categoryName,COUNT(articleId) AS categoryCount FROM t_article RIGHT JOIN t_category ON t_article.categoryId = t_category.categoryId GROUP BY t_category.categoryId;";
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).executeAndFetchTable();
            for (Row row : table.rows()) {
                CategoryDto category = new CategoryDto();
                category.setId(row.getInteger("categoryId"));
                category.setName(row.getString("categoryName"));
                category.setCount(row.getInteger("categoryCount"));
                categoryDtoList.add(category);
            }
        }
        return categoryDtoList;
    }

    @Override
    public void update(Category category) throws Exception {
        String sql = "update t_category set categoryName=:name where categoryId=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("name", category.getName())
                    .addParameter("id", category.getId()).executeUpdate();
        }
    }

    @Override
    public void save(Category category) throws Exception {
        String sql = "insert into t_category values(null,:name)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("name", category.getName()).executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "delete from t_category where categoryId=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
    }

    @Override
    public Category get(Integer id) throws Exception {
        String sql = "select * from t_category where categoryId=:id";
        Category category = new Category();
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).addParameter("id", id).executeAndFetchTable();
            for (Row row : table.rows()) {
                category.setId(row.getInteger("categoryId"));
                category.setName(row.getString("categoryName"));
            }
        }
        return category;
    }

    @Override
    public boolean exist(int categoryId) throws Exception {
        String sql = "SELECT * from t_article where categoryId=:id";
        boolean resulet;
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).addParameter("id", categoryId).executeAndFetchTable();
            resulet = false;
            if (table.rows().size() > 0) {
                resulet = true;
            }
        }
        return resulet;
    }

    @Override
    public List<CategoryDto> pagenation(Pager pager) throws Exception {
        String sql = "select * from t_category ORDER BY categoryId DESC limit :start,:size;";
        List<CategoryDto> categoryDtos = new ArrayList<>();
        try (Connection connection = sql2o.open()) {
            Table table = connection.createQuery(sql).addParameter("start", pager.getStartIndex())
                    .addParameter("size", pager.getPageSize()).executeAndFetchTable();
            for (Row row : table.rows()) {
                CategoryDto category = new CategoryDto();
                category.setId(row.getInteger("categoryId"));
                category.setName(row.getString("categoryName"));
                categoryDtos.add(category);
            }
        }
        return categoryDtos;
    }

    @Override
    public int count() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM t_category WHERE categoryId > 0";
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
