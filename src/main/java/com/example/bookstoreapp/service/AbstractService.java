package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.AbstractBeanEntity;
import com.example.bookstoreapp.service.mapper.BasicMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<T extends AbstractBeanEntity, REQ, RES, MAP
        extends BasicMapper<T, REQ, RES>> implements BasicService<REQ, RES,Long> {
    protected abstract JpaRepository<T, Long> getRepository();

    protected abstract MAP getMapper();

    @Override
    public RES save(REQ request) {
        T entity = getRepository().save(getMapper().toEntity(request));
        return getMapper().toDto(entity);
    }

    @Override
    public List<RES> findAll(Pageable pageable) {
        Page<T> list = (Page<T>) getRepository().findAll(pageable);
        return list.stream().map(getMapper()::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RES get(Long id) {
        return getMapper().toDto(
                getRepository().findById(id)
                        .orElseThrow(() -> new RuntimeException(
                                "Can't get entity by id " + id)));
    }

    @Override
    public void delete(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public RES update(Long id, REQ updateRequest) {
        Optional<T> entityFromDb = getRepository().findById(id);
        T entity = getMapper().toEntity(updateRequest);
        return getMapper().toDto(entityFromDb.map(
                e -> saveAndReturnSavedEntity(entity, e))
                .orElseThrow(
                        () -> new RuntimeException(
                                "Can't update: entity by id " + id)));
    }

    protected T saveAndReturnSavedEntity(T entity, T entityFromDB) {
        entity.setId(entityFromDB.getId());
        return getRepository().save(entity);
    }
}
