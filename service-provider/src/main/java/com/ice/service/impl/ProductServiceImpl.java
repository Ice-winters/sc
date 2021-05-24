package com.ice.service.impl;

import com.ice.pojo.Product;
import com.ice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Ice-winters
 * @since : 2021-05-23 22:29
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> selectProductList() {
        return Arrays.asList(
                new Product(1, "huawei", 2, 5800D),
                new Product(2, "lianxiang", 3, 7822D),
                new Product(3, "xiaomipingban", 5, 2666D)
        );
    }

}
