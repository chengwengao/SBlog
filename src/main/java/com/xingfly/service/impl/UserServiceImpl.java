package com.xingfly.service.impl;

import com.xingfly.dao.UserDao;
import com.xingfly.model.User;
import com.xingfly.model.dto.UserDto;
import com.xingfly.service.UserService;
import com.xingfly.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 15/12/13.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public boolean userIsNotEmpty(String name) {
        int total = 0;
        try {
            total = userDao.userIsNotEmpty(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total > 0 ? true : false;
    }

    @Override
    public List<User> getPageUsers(Pager pager) {
        List<User> users = null;
        try {
            users = userDao.pagenation(pager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public UserDto login(User user) {
        UserDto userDto = null;
        try {
            userDto = userDao.login(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDto;
    }

    @Override
    public void saveUser(User user) {
        try {
            userDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(Integer id) {
        User u = null;
        try {
            u = userDao.getUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            userDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> users = null;
        try {
            users = userDao.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
