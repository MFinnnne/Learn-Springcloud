package com.atguigu.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/6/11 0:05
 **/
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);


}
