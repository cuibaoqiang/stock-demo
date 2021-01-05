package com.test.stock.demo.module;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 15:59:41
 * @desc
 */
@Data
public class KLineInfo {

    private Date day;

    private BigDecimal close;

}
