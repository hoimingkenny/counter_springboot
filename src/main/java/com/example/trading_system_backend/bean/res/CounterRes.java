package com.example.trading_system_backend.bean.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

// similar to HKTVgenericResponse
@AllArgsConstructor
public class CounterRes {

    public static final int SUCCESS = 0;
    public static final int RELOGIN = 1;
    public static final int FAIL = 2;

    @Getter
    private int code;

    @Getter
    private String message;

    @Getter
    private Object data;

    public CounterRes(Object data) {
        this(0, "", data);
    }
}
