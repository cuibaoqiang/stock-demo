package com.test.stock.demo.response;

import lombok.Data;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 16:29:31
 * @desc
 */
@Data
public class XiongResponse<T> {
    private Integer code;
    private String message;
    private T data;
    private String meta;

    public static boolean isSuccess(XiongResponse response) {
        return response.getCode() == 200;
    }

}
