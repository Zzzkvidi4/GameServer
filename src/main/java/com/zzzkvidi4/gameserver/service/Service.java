package com.zzzkvidi4.gameserver.service;

import com.zzzkvidi4.gameserver.response.Error;

import java.util.List;

public interface Service<T, U> {
    List<T> getAll();
    T findById(U id);
    List<Error> create(T entity);
}
