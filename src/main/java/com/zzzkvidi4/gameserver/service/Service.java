package com.zzzkvidi4.gameserver.service;

import java.util.List;

public interface Service<T, U> {
    List<T> getAll();
    T findById(U id);
}
