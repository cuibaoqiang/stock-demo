package com.test.stock.demo.service;

import com.test.stock.demo.module.KLineInfo;
import com.test.stock.demo.module.SimpleInfo;
import com.test.stock.demo.request.SinaKLineRequest;

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
     * 查询KLine信息列表
     * @return 列表
     */
    List<KLineInfo> getKLineData(SinaKLineRequest request);

}
