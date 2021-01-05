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

    private Date day;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;

}
