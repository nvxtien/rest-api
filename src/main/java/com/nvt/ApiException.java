package com.nvt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApiException extends RuntimeException {
    private String name;
    private String field;
    private Object value;

    public ApiException(String name, String field, Object value) {
        super(String.format("%s not found with %s : '%s'", name, field, value));
        this.name = name;
        this.field = field;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}