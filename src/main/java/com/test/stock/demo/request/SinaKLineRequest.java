package com.test.stock.demo.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 17:02:57
 * @desc
 */
@Data
@Builder
public class SinaKLineRequest {
//http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sh600519&scale=60&ma=no&datalen=12
    /**
     * 代码
     */
    private String code;
    /**
     * 间隔 5、10、30、60
     */
    private Integer scale;
    /**
     * 平均区间 不填写no
     */
    private String ma;

    /**
     * 与当前日期的差 最近dateLen个交易日
     */
    private Long dateLen;

}
