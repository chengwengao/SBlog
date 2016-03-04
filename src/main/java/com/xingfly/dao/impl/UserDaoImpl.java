package com.xingfly.dao.impl;

import com.xingfly.dao.UserDao;
import com.xingfly.model.User;
import com.xingfly.model.dto.UserDto;
import com.xingfly.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuperS on 15/12/9.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private Sql2o sql2o;

    @Override
    public boolean userIsNotEmpty(String name) throws Exception {
        boolean flag = false;
        String sql = "SELECT * FROM t_user WHERE username=:username";
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).addParameter("username", name).executeAndFetchTable();
            if (table.rows().size() == 0) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public List<User> pagenation(Pager pager) throws Exception {
        String sql = "select * from t_user ORDER BY userId DESC limit :start,:size;";
        List<User> users = new ArrayList<>();
        try (Connection connection = sql2o.open()) {
            Table table = connection.createQuery(sql).addParameter("start", pager.getStartIndex())
                    .addParameter("size", pager.getPageSize()).executeAndFetchTable();
            for (Row row : table.rows()) {
                User user = new User();
                user.setId(row.getInteger("userId"));
                user.setEmail(row.getString("email"));
                user.setImagePath(row.getString("imageName"));
                user.setNickname(row.getString("nickname"));
                user.setUsername(row.getString("username"));
                user.setPassword(row.getString("password"));
                user.setWebsite(row.getString("website"));
                user.setState(row.getInteger("state"));
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public UserDto login(User user) throws Exception {
        String sql = "select * from t_user where username=:username and password=:password";
        UserDto u = null;
        try (Connection con = sql2o.open()) {
            Table t = con.createQuery(sql).addParameter("username", user.getUsername()).
                    addParameter("password", /*MD5Util.EncoderPwdByMd5(user.getPassword())*/user.getPassword())
                    .executeAndFetchTable();
            for (Row row : t.rows()) {
                u = new UserDto();
                u.setId(row.getInteger("userId"));
                u.setState(row.getInteger("state"));
                u.setNickname(row.getString("nickname"));
                u.setEmail(row.getString("email"));
                u.setImagePath(row.getString("imageName"));
                u.setWebsite(row.getString("website"));
            }
        }
        return u;
    }

    @Override
    public void save(User user) throws Exception {
        String sql = "insert into t_user values(null,0,:username,:password,:nickname,:email,:website,:imagePath)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("username", user.getUsername())
                    .addParameter("password", user.getPassword())
                    .addParameter("nickname", user.getNickname())
                    .addParameter("email", user.getEmail())
                    .addParameter("imagePath", user.getImagePath())
                    .addParameter("website", "www.xingfly.com").executeUpdate();
        }
    }

    @Override
    public void update(User user) throws Exception {
        String sql = "update t_user set nickname=:nickname,username=:username,password=:password,email=:email where userId=:userId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("nickname", user.getNickname())
                    .addParameter("username", user.getUsername())
                    .addParameter("password", user.getPassword())
                    .addParameter("email", user.getEmail())
                    .addParameter("userId", user.getId())
                    .executeUpdate();
        }
    }

    @Override
    public User getUser(Integer id) throws Exception {
        String sql = "SELECT * FROM t_user WHERE userId=:id";
        User u = null;
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).addParameter("id", id).executeAndFetchTable();
            for (Row row : table.rows()) {
                u = new User();
                u.setId(row.getInteger("userId"));
                u.setState(row.getInteger("state"));
                u.setUsername(row.getString("username"));
                u.setPassword(row.getString("password"));
                u.setNickname(row.getString("nickname"));
                u.setEmail(row.getString("email"));
                u.setWebsite(row.getString("website"));
                u.setImagePath(row.getString("imageName"));
            }
        }
        return u;
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql = "DELETE from t_user where userId = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
    }

    @Override
    public List<User> getUsers() throws Exception {
        String sql = "SELECT * FROM t_user";
        List<User> users = new ArrayList<>();
        try (Connection con = sql2o.open()) {
            Table table = con.createQuery(sql).executeAndFetchTable();
            for (Row row : table.rows()) {
                User user = new User();
                user.setId(row.getInteger("userId"));
                user.setState(row.getInteger("state"));
                user.setUsername(row.getString("username"));
                user.setPassword(row.getString("password"));
                user.setNickname(row.getString("nickname"));
                user.setEmail(row.getString("email"));
                user.setWebsite(row.getString("website"));
                user.setImagePath(row.getString("imageName"));
                users.add(user);
            }
        }
        return users;
    }
}
