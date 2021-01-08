package com.test.stock.demo.service.impl;

import com.test.stock.demo.client.SinaClient;
import com.test.stock.demo.client.XiongClient;
import com.test.stock.demo.mapStruct.KLineInfoMapStruct;
import com.test.stock.demo.module.KLineInfo;
import com.test.stock.demo.module.SimpleInfo;
import com.test.stock.demo.request.AdviceRequest;
import com.test.stock.demo.request.SinaKLineRequest;
import com.test.stock.demo.response.SinaKLineResponse;
import com.test.stock.demo.response.XiongResponse;
import com.test.stock.demo.service.StockService;
import com.test.stock.demo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 16:06:18
 * @desc
 */
@Slf4j
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
    public List<KLineInfo> getKLineEndData(SinaKLineRequest request) {
        if (StringUtils.isNotBlank(request.getMa())) {
            request.setMa("no");
        }
        if (Objects.isNull(request.getScale())) {
            request.setScale(60);
        }
        List<SinaKLineResponse> kLineData = sinaClient.getKLineData(request.getCode(), request.getScale(), request.getMa(), request.getDateLen() * 240 / request.getScale());
        return kLineInfoMapStruct.toBeanList(kLineData.stream().filter(temp -> temp.getDay().getHours() == 15).collect(Collectors.toList()));
    }

    @Override
    public List<KLineInfo> getKLineBeginData(SinaKLineRequest request) {
        if (StringUtils.isNotBlank(request.getMa())) {
            request.setMa("no");
        }
        if (Objects.isNull(request.getScale())) {
            request.setScale(60);
        }
        List<SinaKLineResponse> kLineData = sinaClient.getKLineData(request.getCode(), request.getScale(), request.getMa(), request.getDateLen() * 240 / request.getScale());
        return kLineInfoMapStruct.toBeanList(kLineData.stream().filter(temp -> temp.getDay().getHours() == 10).collect(Collectors.toList()));
    }

    @Override
    public List<KLineInfo> getBuyAdvice(AdviceRequest request) {
        int adviceDays = 120;
        List<KLineInfo> result = new ArrayList<>();
        List<KLineInfo> kLineEndData = getKLineEndData(SinaKLineRequest.builder().code(request.getCode()).dateLen(DateUtil.dateInterval(new Date(), request.getBeginDate()) + adviceDays).build());
        int tempIndex = kLineEndData.stream().filter(temp -> temp.getDay().before(request.getBeginDate())).collect(Collectors.toList()).size();
        while (tempIndex < kLineEndData.size()) {
            if (tempIndex < 3) {
                tempIndex++;
                continue;
            }
            List<KLineInfo> viewList = kLineEndData.subList(Math.max(1, tempIndex - adviceDays + 1), tempIndex + 1);
            if (viewList.get(viewList.size() - 1).getClose().doubleValue() >= viewList.stream().max(Comparator.comparing(KLineInfo::getClose)).get().getClose().doubleValue() && isContinueIncrease(request.getCode(), viewList, 3)) {

                if (viewList.get(viewList.size() - 1).getDay().after(request.getBeginDate())) {
                    result.add(viewList.get(viewList.size() - 1));
                }
            }
            tempIndex++;
        }
        return result;
    }

    @Override
    public List<Date> getSaleAdvice(AdviceRequest request) {
        return null;
    }

    private boolean isContinueIncrease(String code, List<KLineInfo> viewList, Integer continueDays) {

        int tempDays = 1;
        while (tempDays < viewList.size()) {
            if (viewList.get(viewList.size() - tempDays).getClose().compareTo(viewList.get(viewList.size() - tempDays - 1).getClose()) < 0) {
                break;
            }
            tempDays++;
        }
        log.warn("{} viewList size {} continue days {}",code, viewList.size() , tempDays - 1);
        return tempDays - 1 == continueDays;
    }
}
