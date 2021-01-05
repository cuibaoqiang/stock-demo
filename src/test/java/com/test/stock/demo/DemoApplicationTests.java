package com.test.stock.demo;

import com.test.stock.demo.request.SinaKLineRequest;
import com.test.stock.demo.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = {DemoApplication.class})
class DemoApplicationTests {
    @Autowired
    private StockService stockService;

    @Test
    void queryAllStockList() {
        log.info("" + stockService.queryAllStockList().size());
    }

    @Test
    void getKLineData() {
        log.info("" + stockService.getKLineData(SinaKLineRequest.builder()
                .code("sh600519")
                .scale(60)
                .ma("no")
                .dataLen(100)
                .build()));
    }

}
