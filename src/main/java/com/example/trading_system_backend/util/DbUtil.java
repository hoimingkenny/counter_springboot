package com.example.trading_system_backend.util;

import com.example.trading_system_backend.bean.res.Account;
import com.google.common.collect.ImmutableMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Immutable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbUtil
{

    // 如何在靜態調用的工具類中注入Spring管理的對象
    private static DbUtil dbUtil = null;

    private DbUtil() {}

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
        dbUtil = new DbUtil();
        dbUtil.setSqlSessionTemplate(this.sqlSessionTemplate);
    }

    // Authentication
    public static Account queryAccount(long uid, String password) {
        return dbUtil.getSqlSessionTemplate().selectOne("userMapper.queryAccount",
                ImmutableMap.of("UId", uid, "Password", password));
    }

    public static void updateLoginTime(long uid, String nowDate, String nowTime) {
        dbUtil.getSqlSessionTemplate().update(
                "userMapper.updateAccountLoginTime",
                ImmutableMap.of(
                        "UId", uid,
                        "ModifyDate", nowDate,
                        "ModifyTime", nowTime
                )
        );
    }



//    public static String getName() {
//        String res = dbUtil.getSqlSessionTemplate().selectOne(
//                "testMapper.queryBalance"
//        );
//
//        if (res == null) {
//            return "N/A";
//        } else {
//            return res;
//        }
//    }
}
