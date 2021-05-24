package com.ice.service;

import com.ice.pojo.Order;

/**
 * @author : Ice-winters
 * @since : 2021-05-24 00:23
 */
public interface OrderService {
    /**
     * 根据主键Id查询订单
     *
     * @param id
     * @return
     */
    Order selectOrderById(Integer id);
}
