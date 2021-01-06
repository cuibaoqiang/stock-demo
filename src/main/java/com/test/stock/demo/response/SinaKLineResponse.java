package com.test.stock.demo.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 17:02:57
 * @desc
 */
@Data
public class SinaKLineResponse {

    /**
     * 时间
     */
    private Date day;
    /**
     * 开始价格
     */
    private BigDecimal open;
    /**
     * 最高价格
     */
    private BigDecimal high;
    /**
     * 最低价格
     */
    private BigDecimal low;
    /**
     * 结束价格
     */
    private BigDecimal close;

    /**
     * 成交量（手）
     */
    private BigDecimal volume;

}
