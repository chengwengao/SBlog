package com.xingfly.service;

import com.xingfly.model.About;
import java.util.List;
/**
 * Created by SuperS on 16/1/7.
 */
public interface AboutService {
    public About getAbout(Integer id);
    public void upDate(About about);
    public void save(About about);
    public void delete(Integer id);
    public int count();
    public List<About> list();
}
