package com.example.trading_system_backend.service;

import com.example.trading_system_backend.bean.res.Account;
import com.example.trading_system_backend.cache.CacheType;
import com.example.trading_system_backend.cache.RedisStringCache;
import com.example.trading_system_backend.util.DbUtil;
import com.example.trading_system_backend.util.JsonUtil;
import com.example.trading_system_backend.util.TimeformatUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import thirdparty.uuid.SnowflakeUuid;

import java.util.Date;

@Component
public class AccountServiceImpl implements IAccountService {
    @Override
    public Account login(long uid, String password, String captcha, String captchaId) throws Exception {
        // 1. check parameters
        if (StringUtils.isAnyBlank(password, captcha, captchaId)) {
            return null;
        }

        // 2. compare verification code in cache
        String captchaCache = RedisStringCache.get(captchaId, CacheType.CAPTCHA);

        if (StringUtils.isEmpty(captchaCache)){
            return null;
        } else if(!StringUtils.equalsAnyIgnoreCase(captcha, captchaCache)) {
            return null;
        }
        RedisStringCache.remove(captchaId, CacheType.CAPTCHA);

        // 3. compare username and password in DB
        Account account = DbUtil.queryAccount(uid, password);

        if (account == null) {
            return null;
        } else {
            // set UUID as identity
            account.setToken(String.valueOf(SnowflakeUuid.getInstance().getUUID()));

            // store in cache
            RedisStringCache.cache(String.valueOf(
                    account.getToken()), JsonUtil.toJson(account),
                    CacheType.ACCOUNT
            );

            // update login time
            Date date = new Date();
            DbUtil.updateLoginTime(uid,
                    TimeformatUtil.yyyyMMdd(date),
                    TimeformatUtil.hhMMss(date));

            return account;
        }
    }

    @Override
    public boolean accountExistInCache(String token) {
        if(StringUtils.isBlank(token)){
            return false;
        }

        // get data from cache
        String acc = RedisStringCache.get(token, CacheType.ACCOUNT);
        if(acc != null){
            RedisStringCache.cache(token,acc,CacheType.ACCOUNT);
            return true;
        }else {
            return false;
        }
    }
}
