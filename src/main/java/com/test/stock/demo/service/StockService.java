package com.test.stock.demo.service;

import com.test.stock.demo.module.KLineInfo;
import com.test.stock.demo.module.SimpleInfo;
import com.test.stock.demo.request.AdviceRequest;
import com.test.stock.demo.request.SinaKLineRequest;

import java.util.Date;
import java.util.List;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 15:59:56
 * @desc
 */
public interface StockService {

    /**
     * 查询所有Stock列表
     * @return 列表
     */
    List<SimpleInfo> queryAllStockList();

    /**
     * 查询每天15:30时KLine信息列表
     * @return 列表
     */
    List<KLineInfo> getKLineEndData(SinaKLineRequest request);

    /**
     * 查询每天10:30时KLine信息列表
     * @return 列表
     */
    List<KLineInfo> getKLineBeginData(SinaKLineRequest request);

    /**
     * 查询买入建议
     * @return 日期列表
     */
    List<Date> getBuyAdvice(AdviceRequest request);

    /**
     * 查询售出建议
     * @return 日期列表
     */
    List<Date> getSaleAdvice(AdviceRequest request);

}
