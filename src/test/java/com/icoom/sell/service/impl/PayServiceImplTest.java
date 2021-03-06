package com.icoom.sell.service.impl;

import com.icoom.sell.dto.OrderDTO;
import com.icoom.sell.service.OrderService;
import com.icoom.sell.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by mxj on 2018/9/2 下午9:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
//        OrderDTO orderDTO = new OrderDTO();
        OrderDTO orderDTO = orderService.findOne("1535259682782611164");
        payService.create(orderDTO);
    }

    @Test
    public void refund() {
        OrderDTO orderDTO = orderService.findOne("1535259682782611164");
        payService.refund(orderDTO);
    }
}