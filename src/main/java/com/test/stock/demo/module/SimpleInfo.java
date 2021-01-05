package com.test.stock.demo.module;

import lombok.Builder;
import lombok.Data;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 15:59:49
 * @desc
 */
@Data
@Builder
public class SimpleInfo {
    /**
     * 代码
     */
    String code;

    /**
     * 名称
     */
    String name;
}
