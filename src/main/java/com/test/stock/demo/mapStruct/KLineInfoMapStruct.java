package com.test.stock.demo.mapStruct;

import com.test.stock.demo.module.KLineInfo;
import com.test.stock.demo.response.SinaKLineResponse;
import org.mapstruct.Mapper;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 17:35:47
 * @desc
 */
@Mapper(componentModel = "spring")
public interface KLineInfoMapStruct extends IMapStruct<SinaKLineResponse, KLineInfo> {
}
