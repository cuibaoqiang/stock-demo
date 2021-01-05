package com.test.stock.demo.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 16:24:30
 * @desc https://www.jianshu.com/p/2f45fcb44771
 */
@FeignClient( name = "SinaClient")
public interface SinaClient {


}
