package com.example.trading_system_backend.controller;

import com.example.trading_system_backend.bean.res.Account;
import com.example.trading_system_backend.bean.res.CaptchaRes;
import com.example.trading_system_backend.bean.res.CounterRes;
import com.example.trading_system_backend.cache.CacheType;
import com.example.trading_system_backend.cache.RedisStringCache;
import com.example.trading_system_backend.service.AccountService;
import com.example.trading_system_backend.util.Captcha;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import thirdparty.uuid.SnowflakeUuid;

import static com.example.trading_system_backend.bean.res.CounterRes.FAIL;
import static com.example.trading_system_backend.bean.res.CounterRes.RELOGIN;

@RestController
@RequestMapping("/login")
@Log4j2
//@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {

    @Autowired
    private AccountService accountService;

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

    @RequestMapping("/userlogin")
    public CounterRes login(@RequestParam long uid,
                            @RequestParam String password,
                            @RequestParam String captcha,
                            @RequestParam String captchaId) throws Exception {
        Account account = accountService.login(uid, password,
                captcha, captchaId);

        if(account == null){
            return new CounterRes(FAIL,
                    "Username / Password is incorrect，Login failed",null);
        }else {
            return new CounterRes(account);
        }
    }

    @RequestMapping("/loginfail")
    public CounterRes loginFail(){
        return new CounterRes(RELOGIN,"Login again",null);
    }

}
