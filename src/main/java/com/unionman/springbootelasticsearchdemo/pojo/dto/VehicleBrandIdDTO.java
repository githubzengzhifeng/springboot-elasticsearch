package com.unionman.springbootelasticsearchdemo.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Rong.Jia
 * @description: 车辆品牌，车系
 * @date 2019/03/19 11:49:22
 */
@Data
public class VehicleBrandIdDTO implements Serializable {

    private static final long serialVersionUID = 2977402252568078581L;

    /**
     * 车辆主品牌id
     */
    private Integer vehicleBrandId;

    private String vehicleBrandName;

    /**
     * 车辆子品牌id 集合
     */
    private List<VehicleSuBrandIdDTO> vehicleSuBrandIds;

    @Data
    public static class VehicleSuBrandIdDTO {

        private Integer vehicleSuBrandId;

        private String vehicleSuBrandName;
    }
}
