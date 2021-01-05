package com.test.stock.demo.client;

import com.test.stock.demo.response.XiongResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 16:24:49
 * @desc https://www.doctorxiong.club/api/
 */
@FeignClient(url = "https://api.doctorxiong.club/v1/", name = "doctorxiong")
public interface XiongClient {

    @RequestMapping(path = "stock/all")
    XiongResponse<List<List<String>>> StockAll();
}
