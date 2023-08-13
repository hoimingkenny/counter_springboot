package com.example.trading_system_backend.bean.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Position
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class PositionInfo {

    private int id;
    private long uid;
    private int code;
    private String name;
    private long cost;
    private long count;

}