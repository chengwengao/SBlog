package com.xingfly.dao;

import com.xingfly.model.About;

import java.util.List;

/**
 * Created by SuperS on 16/1/7.
 */
public interface AboutDao {
    public About getAbout(Integer id) throws Exception;

    public void upDate(About about) throws Exception;

    public void save(About about) throws Exception;

    public void delete(Integer id) throws Exception;

    public int count() throws Exception;

    public List<About> list() throws Exception;
}
