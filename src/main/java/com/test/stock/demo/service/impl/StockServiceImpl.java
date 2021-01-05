package com.test.stock.demo.service.impl;

import com.test.stock.demo.client.SinaClient;
import com.test.stock.demo.client.XiongClient;
import com.test.stock.demo.mapStruct.KLineInfoMapStruct;
import com.test.stock.demo.module.KLineInfo;
import com.test.stock.demo.module.SimpleInfo;
import com.test.stock.demo.request.SinaKLineRequest;
import com.test.stock.demo.response.SinaKLineResponse;
import com.test.stock.demo.response.XiongResponse;
import com.test.stock.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 16:06:18
 * @desc
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private XiongClient xiongClient;

    @Autowired
    private SinaClient sinaClient;

    @Autowired
    private KLineInfoMapStruct kLineInfoMapStruct;

    @Override
    public List<SimpleInfo> queryAllStockList() {
        XiongResponse<List<List<String>>> response = xiongClient.StockAll();
        if (XiongResponse.isSuccess(response)) {
            List<List<String>> tempList = response.getData();
            return tempList.stream().map(temp -> SimpleInfo.builder().code(temp.get(0)).name(temp.get(1)).build()).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<KLineInfo> getKLineData(SinaKLineRequest request) {
        List<SinaKLineResponse> kLineData = sinaClient.getKLineData(request.getCode(), request.getScale(), request.getMa(), request.getDataLen());
        return kLineInfoMapStruct.toBeanList(kLineData.stream().filter(temp -> temp.getDay().getHours() == 15).collect(Collectors.toList()));
    }
}
