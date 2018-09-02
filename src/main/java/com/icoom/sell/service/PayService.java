package com.icoom.sell.service;

import com.icoom.sell.dto.OrderDTO;

/**
 * Created by mxj on 2018/9/2 下午4:41
 */
public interface PayService {

    void create(OrderDTO orderDTO);
}
