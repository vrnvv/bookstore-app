package com.example.bookstoreapp.service;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BasicService<REQ, RES, ID> {

    RES save(REQ book);

    List<RES> findAll(Pageable pageable);

    RES get(ID id);

    RES update(ID id, REQ updatedBookDto);

    void delete(ID id);
}
