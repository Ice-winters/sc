package com.ice.service.impl;

import com.ice.pojo.Order;
import com.ice.pojo.Product;
import com.ice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author : Ice-winters
 * @since : 2021-05-24 00:34
 */
@Service
public class OrderServiceImpl implements OrderService {

    public static final String SERVICE_ID = "service-provider";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    // ribbon 负载均衡
    private LoadBalancerClient loadBalancerClient;

    @Override
    public Order selectOrderById(Integer id) {
        return new Order(id, "order-001", "china", 4999D,
                selectProductListByLoadBalancerClient());
    }


    private List<Product> selectProductListByDiscoveryClient() {

        StringBuilder sb;
        // 从注册中心中获取服务列表 元数据形式获取信息
        List<String> services = discoveryClient.getServices();
        if (CollectionUtils.isEmpty(services)) {
            return null;
        }

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(SERVICE_ID);
        if (CollectionUtils.isEmpty(serviceInstances)) {
            return null;
        }
        ServiceInstance serviceInstance = serviceInstances.get(0);
        sb = new StringBuilder();
        sb.append("http://")
                .append(serviceInstance.getHost())
                .append(":")
                .append(serviceInstance.getPort())
                .append("/product/list");

        // responseEntity 封装了返回数据
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
                sb.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }
        );

        return responseEntity.getBody();
    }

    private List<Product> selectProductListByLoadBalancerClient() {
        StringBuilder sb;
        // 根据服务名称获取服务
        ServiceInstance serviceInstance = loadBalancerClient.choose(SERVICE_ID);
        if (serviceInstance == null) {
            return null;
        }
        sb = new StringBuilder();
        sb.append("http://")
                .append(serviceInstance.getHost())
                .append(":")
                .append(serviceInstance.getPort())
                .append("/product/list");

        // 判断是调用的那个service
        System.out.println(sb.toString());


        // responseEntity 封装了返回数据
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
                sb.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }
        );

        return responseEntity.getBody();


    }


    /**
     * 采用负载均衡注解形式，可以注解写服务的名字，会自动从eureka中获取信息，转换为ip+端口的实际地址去访问
     * @return
     */
    private List<Product> selectProductListByLoadBalancerAnnotation() {
        return restTemplate.exchange(
                "http://service-provider/product/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        ).getBody();
    }

}
