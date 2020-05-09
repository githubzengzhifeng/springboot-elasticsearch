package com.unionman.springbootelasticsearchdemo.service;

import com.unionman.springbootelasticsearchdemo.pojo.dto.VehicleDTO;
import com.unionman.springbootelasticsearchdemo.pojo.dto.VehicleQueryDTO;
import com.unionman.springbootelasticsearchdemo.pojo.vo.PageVO;
import com.unionman.springbootelasticsearchdemo.pojo.vo.VehicleVO;

import java.util.List;
import java.util.Map;

/**
 * @author Zhifeng.Zeng
 * @descrption
 * @date 2020/05/06 20:10
 */
public interface VehicleService {
    /**
     * 添加机动车数据
     * @param vehicleDTO
     */
    void addVehicle(VehicleDTO vehicleDTO);

    /**
     * 添加多条机动车数据
     * @param vehicleDTOList
     */
    void addVehicles(List<VehicleDTO> vehicleDTOList);

    /**
     * 删除机动车数据
     * @param id
     */
    void deleteVehicle(String id);

    /**
     * 编辑机动车数据
     * @param vehicleDTO
     */
    void updateVehicle(VehicleDTO vehicleDTO);

    /**
     * 查询机动车数据
     * @param vehicleQueryDTO
     * @return
     */
    PageVO<VehicleVO> findVehicles(VehicleQueryDTO vehicleQueryDTO);

    /**
     * 车牌归档统计
     */
    Map<String, Long> plateAggregationStatistics();

    /**
     * 获取时段车流统计
     * @param startTime
     * @param endTime
     * @return
     */
    Map<String, Long> timeperiodTrafficFlowStatistics(Long startTime, Long endTime);

    /**
     * 按日期进行车流量统计
     * @param startTime
     * @param endTime
     * @return
     */
    Map<String, Long> trafficFlowStatistics(Long startTime, Long endTime);

    /**
     * 按车型进行聚合统计
     * @return
     */
    Map<String, Long> getModelIdentificationRate();

    /**
     * 统计时间戳的最大值 最小值 总和 基数 平均数
     * @return
     */
    Map<String, Long> getStats();
}
