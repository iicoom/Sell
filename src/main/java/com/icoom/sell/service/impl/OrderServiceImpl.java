package com.icoom.sell.service.impl;

import com.icoom.sell.dataobject.OrderDetail;
import com.icoom.sell.dataobject.OrderMaster;
import com.icoom.sell.dataobject.ProductInfo;
import com.icoom.sell.dto.CartDTO;
import com.icoom.sell.dto.OrderDTO;
import com.icoom.sell.enums.ResultEnum;
import com.icoom.sell.exception.SellException;
import com.icoom.sell.repository.OrderDetailRepository;
import com.icoom.sell.repository.OrderMasterRepository;
import com.icoom.sell.service.OrderService;
import com.icoom.sell.service.ProductService;
import com.icoom.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqKey();
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);

        // 1. 查询商品（数量，价格）
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 2. 计算总价
            orderAmout = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmout);

            // 订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        // 3. 写入订单数据库(orderMaster和orderDetail)
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmout);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);
        
        // 4. 扣库存
        List<CartDTO>

        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
