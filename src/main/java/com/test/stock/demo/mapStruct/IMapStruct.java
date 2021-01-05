package com.test.stock.demo.mapStruct;

import java.util.List;

/**
 * @author cuibaoqiang
 * @date 2021-01-05 17:36:52
 * @desc
 */
public interface IMapStruct<DTO, BEAN> {

    List<DTO> toDTOList(List<BEAN> listOfBean);

    List<BEAN> toBeanList(List<DTO> listOfDto);

    DTO toDTO(BEAN bean);

    BEAN toBean(DTO dto);
}
