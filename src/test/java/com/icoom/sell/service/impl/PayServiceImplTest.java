package com.icoom.sell.service.impl;

import com.icoom.sell.dto.OrderDTO;
import com.icoom.sell.service.PayService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by mxj on 2018/9/2 下午9:59
 */
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        payService.create(orderDTO);
    }
}