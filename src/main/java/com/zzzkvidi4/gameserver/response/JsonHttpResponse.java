package com.zzzkvidi4.gameserver.response;

import java.util.LinkedList;
import java.util.List;

public class JsonHttpResponse<T> {
    private T data;
    private List<Error> errors = new LinkedList<>();

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void addError(Error error){
        errors.add(error);
    }

    public void addErrors(List<Error> errors){
        this.errors.addAll(errors);
    }
}
