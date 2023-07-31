package com.example.trading_system_backend.util;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseUtil {

    // 如何在靜態調用的工具類中注入Spring管理的對象
    private static DatabaseUtil dbUtil = null;

    private DatabaseUtil() {}

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    private SqlSessionTemplate getSqlSessionTemplate() {
        return this.sqlSessionTemplate;
    }

    @PostConstruct
    private void init() {
        dbUtil = new DatabaseUtil();
        dbUtil.setSqlSessionTemplate(this.sqlSessionTemplate);
    }

    public static long getId() {
        Long res = dbUtil.getSqlSessionTemplate().selectOne(
                "testMapper.queryBalance"
        );
        if (res == null) {
            return -1;
        } else {
            return res;
        }
    }
}
