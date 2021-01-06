package com.test.stock.demo.client;

import com.test.stock.demo.response.SinaKLineResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 16:24:30
 * @desc https://www.jianshu.com/p/2f45fcb44771
 */
@FeignClient(url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php", name = "SinaClient")
public interface SinaClient {

    @GetMapping(path = "CN_MarketData.getKLineData")
    List<SinaKLineResponse> getKLineData(@RequestParam("symbol") String code, @RequestParam("scale") Integer scale, @RequestParam("ma") String ma, @RequestParam("datalen") Long datalen);

}
