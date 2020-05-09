package com.unionman.springbootelasticsearchdemo.repository;

import com.unionman.springbootelasticsearchdemo.entity.Vehicle;
import com.unionman.springbootelasticsearchdemo.pojo.dto.VehicleDTO;
import com.unionman.springbootelasticsearchdemo.pojo.dto.VehicleQueryDTO;
import org.zxp.esclientrhl.repository.PageList;

import java.util.List;
import java.util.Map;

/**
 * @author Zhifeng.Zeng
 * @descrption
 * @date 2020/05/06 20:10
 */
public interface VehicleRepository {

    /**
     * 添加机动车数据
     *
     * @param vehicle
     */
    void addVehicle(Vehicle vehicle);

    /**
     * 编辑机动车数据
     *
     * @param vehicle
     */
    void updateVehicle(Vehicle vehicle);

    /**
     * 删除机动车数据
     *
     * @param id
     */
    void deleteVehicle(String id);

    /**
     * 机动车条件检索
     *
     * @param vehicleQueryDTO
     * @return
     */
    PageList<Vehicle> findVehicles(VehicleQueryDTO vehicleQueryDTO);

    /**
     * 大量插入机动车数据
     * @param vehicleList
     */
    void addVehicles(List<Vehicle> vehicleList);

    /**
     * 车牌归档统计
     * @return
     */
    Map<String, Long> plateAggregationStatistics();

    /**
     * 时段车流统计
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
