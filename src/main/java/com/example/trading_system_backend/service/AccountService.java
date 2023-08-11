package com.example.trading_system_backend.service;

import com.example.trading_system_backend.bean.res.Account;

public interface AccountService {

    // login
    Account login(long uid, String password, String captcha, String captchaId) throws Exception;

    /**
     * see if login information exist in cache
     * @param token
     * @return
     */
    boolean accountExistInCache(String token);
}
