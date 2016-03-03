package com.xingfly.dao;

import com.xingfly.model.Category;
import com.xingfly.util.Pager;
import com.xingfly.model.dto.CategoryDto;

import java.util.List;

/**
 * Created by SuperS on 15/12/9.
 */
public interface CategoryDao {
    //获取分类列表
    public List<CategoryDto> all() throws Exception;

    //更新分类
    public void update(Category category) throws Exception;

    //保存分类
    public void save(Category category) throws Exception;

    //删除分类
    public void delete(Integer id) throws Exception;

    //获取分类
    public Category get(Integer id) throws Exception;

    //是否存在该categoryId
    public boolean exist(int categoryId) throws Exception;

    //分页查询
    public List<CategoryDto> pagenation(Pager pager) throws Exception;

    //总数
    public int count() throws Exception;

}
