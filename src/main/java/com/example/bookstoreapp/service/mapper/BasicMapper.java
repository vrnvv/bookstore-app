package com.example.bookstoreapp.service.mapper;

import com.example.bookstoreapp.model.AbstractBeanEntity;
import org.mapstruct.Mapping;

public interface BasicMapper<T extends AbstractBeanEntity, REQ, RES> {
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "id", ignore = true)
    T toEntity(REQ request);

    RES toDto(T entity);
}
