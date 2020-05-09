package com.unionman.springbootelasticsearchdemo.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Zhifeng.Zeng
 * @descrption
 * @date 2020/05/08 17:25
 */
@Setter
@Getter
public class VehicleQueryDTO extends PageDTO{


    /**
     * 车牌号
     */
    private String plateText;

    /**
     * 标识符，关联车牌号，true: 是，false: 非，默认false; 车牌号非空, 该字段必填
     */
    private Boolean identifier;

    /**
     * 车牌类型 集合
     */
    private List<Integer> plateTypes;


    /**
     * 卡口id 数组
     */
    private List<String> tollgateIndexCodes;

    /**
     * 车辆颜色数组
     */
    private List<Integer> vehicleColors;

    /**
     * 车辆标识物数组
     */
    private List<Integer> vehicleMarkings;

    /**
     * 车辆品牌，车系
     */
    private List<VehicleBrandIdDTO> vehicleBrandIds;

    /**
     * 过车类型
     */
    private Byte passingType;


    /**
     * 车牌颜色集合
     */
    private List<Integer> plateColors;

    public Boolean getIdentifier() {
        return identifier = identifier ==null ? Boolean.FALSE : identifier;
    }
}
