package com.xingfly.dao;

import com.xingfly.model.User;
import com.xingfly.model.dto.UserDto;
import com.xingfly.util.Pager;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SuperS on 16/3/8.
 */
@Repository
public interface UserDao {
    // 用户登录
    public UserDto login(User user) throws Exception;

    // 添加或保存用户
    public void save(User user) throws Exception;

    // 更新用户
    public void update(User user) throws Exception;

    // 获取用户列表
    public List<User> getUsers() throws Exception;

    // 删除用户
    public void delete(Integer id) throws Exception;

    // 获取用户
    public User getUser(Integer id) throws Exception;

    // 分页获取用户
    public List<User> pagenation(Pager pager) throws Exception;

    // 是否存在用户
    public int userIsNotEmpty(String name) throws Exception;
}
