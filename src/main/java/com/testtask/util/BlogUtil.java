package com.testtask.util;

import com.testtask.model.BlogEntity;
import com.testtask.to.BlogTo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogUtil {
    public BlogTo asTo(BlogEntity entity) {
        return (entity == null) ? null : new BlogTo(entity.getId(), entity.getTitle(), entity.getContent());
    }

    public List<BlogTo> asTo(List<BlogEntity> entityList) {
        return entityList.stream().map(this::asTo).collect(Collectors.toList());
    }

    public BlogEntity asEntity(BlogTo blogTo) {
        return (blogTo == null) ? null : new BlogEntity(blogTo.getId(), blogTo.getTitle(), blogTo.getContent());
    }
}
