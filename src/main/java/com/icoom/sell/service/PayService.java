package com.icoom.sell.service;

import com.icoom.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;

/**
 * Created by mxj on 2018/9/2 下午4:41
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify (String notifyData);
}
