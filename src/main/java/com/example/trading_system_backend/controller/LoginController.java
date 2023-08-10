package com.example.trading_system_backend.controller;

import com.example.trading_system_backend.bean.res.CaptchaRes;
import com.example.trading_system_backend.bean.res.CounterRes;
import com.example.trading_system_backend.cache.CacheType;
import com.example.trading_system_backend.cache.RedisStringCache;
import com.example.trading_system_backend.util.Captcha;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thirdparty.uuid.SnowflakeUuid;

@RestController
@RequestMapping("/login")
@Log4j2
//@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {
    @RequestMapping("/captcha")
    public CounterRes captcha() throws Exception {
        // 1. generate verification code
        Captcha captcha = new Captcha(120,40,4,10);

        // 2. put the code's ID and value into Redis with expiry time
        String uuid = String.valueOf(SnowflakeUuid.getInstance().getUUID());
        RedisStringCache.cache(uuid,captcha.getCode(),
                CacheType.CAPTCHA);

        // 3. send encoded base64 images back to frontend
        //uuid,base64
        CaptchaRes res = new CaptchaRes(uuid,captcha.getBase64ByteStr());

        return new CounterRes(res);
    }

//    @RequestMapping("/userlogin")
//    public CounterRes login() throws Exception {
//
//    }
}
