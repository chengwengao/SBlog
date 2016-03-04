package com.xingfly.dao.impl;

import com.xingfly.dao.AboutDao;
import com.xingfly.model.About;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuperS on 16/1/7.
 */
@Repository
public class AboutDaoImpl implements AboutDao {
    @Autowired
    private Sql2o sql2o;

    @Override
    public About getAbout(Integer id) throws Exception {
        String sql = "select * from t_about where aboutId = :id";
        About about = null;
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).addParameter("id", id).executeAndFetchTable();
            Row row = table.rows().get(0);
            about = new About();
            about.setId(row.getInteger("aboutId"));
            about.setContent(row.getString("content"));
            about.setMarkDown(row.getString("markDown"));
        }
        return about;
    }

    @Override
    public void upDate(About about) throws Exception {
        String sql = "update t_about set content=:content,markDown=:markDown WHERE aboutId=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("id", about.getId())
                    .addParameter("content", about.getContent())
                    .addParameter("markDown", about.getMarkDown()).executeUpdate();
        }
    }

    @Override
    public List<About> list() throws Exception {
        String sql = "select * from t_about";
        List<About> list = new ArrayList<>();
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).executeAndFetchTable();
            for (Row row : table.rows()) {
                About about = new About();
                about.setId(row.getInteger("aboutId"));
                about.setContent(row.getString("content"));
                about.setMarkDown(row.getString("markDown"));
                list.add(about);
            }
        }
        return list;
    }

    @Override
    public void save(About about) throws Exception {
        String sql = "insert into t_about values(null,:markDown,:content)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("content", about.getContent())
                    .addParameter("markDown", about.getMarkDown())
                    .executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "delete from t_about where aboutId=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
    }

    @Override
    public int count() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM t_about";
        Integer count = 0;
        try (Connection con = sql2o.open()) {
            Row row = con.createQuery(sql).executeAndFetchTable().rows().get(0);
            count = row.getInteger("total");
        }
        return count;
    }
}
