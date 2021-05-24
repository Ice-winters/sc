package com.ice.service;

import com.ice.pojo.Product;

import java.util.List;

/**
 * @author : Ice-winters
 * @since : 2021-05-23 22:27
 */
public interface ProductService {


    /**
     * 查询商品列表
     *
     * @return
     */
    List<Product> selectProductList();
}
