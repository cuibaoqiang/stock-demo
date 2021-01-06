package com.test.stock.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.stock.demo.module.SimpleInfo;
import com.test.stock.demo.request.AdviceRequest;
import com.test.stock.demo.request.SinaKLineRequest;
import com.test.stock.demo.service.StockService;
import com.test.stock.demo.utils.DateUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest(classes = {DemoApplication.class})
class DemoApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StockService stockService;

    @Test
    void queryAllStockList() {
        log.info("" + stockService.queryAllStockList().size());
    }

    @SneakyThrows
    @Test
    void getKLineEndData() {
        log.info("" + objectMapper.writeValueAsString(stockService.getKLineEndData(SinaKLineRequest.builder()
                .code("sh600519")
                .dateLen(10L)
                .build())));
    }
    @SneakyThrows
    @Test
    void getKLineBeginData() {
        log.info("" + objectMapper.writeValueAsString(stockService.getKLineBeginData(SinaKLineRequest.builder()
                .code("sh600519")
                .dateLen(10L)
                .build())));
    }

    @SneakyThrows
    @Test
    void getBuyAdvice() {
        log.info("" + objectMapper.writeValueAsString(stockService.getBuyAdvice(AdviceRequest.builder()
                .code("sh600004")
                .beginDate(DateUtil.parseToDate("2021-01-05", DateUtil.DATE_FMT_A))
                .endDate(new Date())
                .build())));
    }
    @SneakyThrows
    @Test
    void getAllBuyAdvice() {
        List<SimpleInfo> simpleInfos = stockService.queryAllStockList();
        simpleInfos.forEach(simpleInfo -> {
            try {
                List<Date> buyAdvice = stockService.getBuyAdvice(AdviceRequest.builder()
                        .code(simpleInfo.getCode())
                        .beginDate(DateUtil.parseToDate("2021-01-05", DateUtil.DATE_FMT_A))
                        .endDate(new Date())
                        .build());
                if (!CollectionUtils.isEmpty(buyAdvice)) {
                    log.info(simpleInfo.getCode() + "\t" + simpleInfo.getName());
                }
            } catch (Exception e) {
                log.error(simpleInfo.getCode());
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
