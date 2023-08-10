package com.example.trading_system_backend;

import com.example.trading_system_backend.config.CounterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import thirdparty.uuid.SnowflakeUuid;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TradingSystemBackendApplication {

    @Autowired
    private CounterConfig counterConfig;

    @PostConstruct
    private void init() {
        SnowflakeUuid.getInstance().init(counterConfig.getDataCenterId(), counterConfig.getWorkerId());
    }

    public static void main(String[] args) {
        SpringApplication.run(TradingSystemBackendApplication.class, args);
    }

}
