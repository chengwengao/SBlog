package com.xingfly.dao;

import com.xingfly.model.WebApp;
import com.xingfly.model.dto.WebAppDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SuperS on 16/3/16.
 */
@Repository
public interface WebAppDao {
    public void save(WebApp webApp) throws Exception;
    public void update(WebApp webApp) throws Exception;
    public WebAppDto getWebDto(Integer id) throws Exception;
    public Integer getArticlesView() throws Exception;
    public Integer count() throws Exception;
    public List<WebApp> getWebDtos() throws Exception;
}
