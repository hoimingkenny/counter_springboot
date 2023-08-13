package com.example.trading_system_backend.service;

import com.example.trading_system_backend.bean.res.OrderInfo;
import com.example.trading_system_backend.bean.res.PositionInfo;
import com.example.trading_system_backend.bean.res.TradeInfo;
import com.example.trading_system_backend.config.CounterConfig;
import com.example.trading_system_backend.util.DbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceImpl implements IOrderService {
    @Override
    public Long getBalance(long uid) {
        return DbUtil.getBalance(uid);
    }

    @Override
    public List<PositionInfo> getPostList(long uid) {
        return DbUtil.getPosiList(uid);
    }

    @Override
    public List<OrderInfo> getOrderList(long uid) {
        return DbUtil.getOrderList(uid);
    }

    @Override
    public List<TradeInfo> getTradeList(long uid) {
        return DbUtil.getTradeList(uid);
    }

//    @Autowired
//    private CounterConfig config;
//
//    @Autowired
//    private GatewayConn gatewayConn;
//
//    @Override
//    public boolean sendOrder(long uid, short type, long timestamp, int code, byte direction, long price, long volume, byte ordertype) {
//        return false;
//    }
//
//    @Override
//    public boolean cancelOrder(int uid, int counteroid, int code) {
//        return false;
//    }
}
