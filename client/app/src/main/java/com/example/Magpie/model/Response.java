package com.example.Magpie.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Response<T> {

    @Getter
    public class Error {
        private Date timestamp;
        private String message;
    }

    private String status;
    private T payload;
    private Error error;
    private Object metadata;
}
