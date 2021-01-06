package com.test.stock.demo.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author cuibaoqiang
 * @date 2021-01-06 10:59:52
 * @desc 买入/售出建议
 */
@Data
@Builder
public class AdviceRequest {

    /**
     * 代码
     */
    private String code;

    /**
     * 起始日期
     */
    private Date beginDate;

    /**
     * 结束日期
     */
    private Date endDate;

}
