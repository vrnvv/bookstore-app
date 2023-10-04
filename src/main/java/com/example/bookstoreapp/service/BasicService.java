package com.example.bookstoreapp.service;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BasicService<REQ, RES, ID> {

    RES save(REQ req);

    List<RES> findAll(Pageable pageable);

    RES get(ID id);

    RES update(ID id, REQ req);

    void delete(ID id);
}
