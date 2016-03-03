package com.xingfly.service;

import com.xingfly.model.Category;
import com.xingfly.util.Pager;
import com.xingfly.model.dto.CategoryDto;

import java.util.List;

/**
 * Created by SuperS on 15/12/9.
 */
public interface CategoryService {
    //获取所有分类
    public List<CategoryDto> getCategories();

    //更新分类
    public void updateCategory(Category category);

    //保存或添加分类
    public void saveCategory(Category category);

    // 删除分类
    public void deleteCategory(Integer categoryId);

    //获取分类
    public Category getCategory(int categoryId);

    //是否存在该categoryId
    public boolean exist(int categoryId);

    //分页查找
    public List<CategoryDto> getPageCategories(Pager pager);

    //获取总数
    public int getCount();
}
