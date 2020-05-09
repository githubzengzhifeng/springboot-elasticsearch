package com.unionman.springbootelasticsearchdemo.controller;

import com.unionman.springbootelasticsearchdemo.pojo.dto.VehicleDTO;
import com.unionman.springbootelasticsearchdemo.pojo.dto.VehicleQueryDTO;
import com.unionman.springbootelasticsearchdemo.pojo.vo.PageVO;
import com.unionman.springbootelasticsearchdemo.pojo.vo.ResponseVO;
import com.unionman.springbootelasticsearchdemo.pojo.vo.VehicleVO;
import com.unionman.springbootelasticsearchdemo.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zhifeng.Zeng
 * @descrption 机动车控制类
 * @date 2020/05/06 19:12
 */
@Slf4j
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * 添加机动车数据
     * @param vehicleDTO
     * @return
     */
    @PostMapping
    public ResponseVO addVehicle(@RequestBody VehicleDTO vehicleDTO){

        vehicleService.addVehicle(vehicleDTO);
        return ResponseVO.success();
    }

    /**
     * 批量添加机动车数据
     * @param vehicleDTOList
     * @return
     */
    @PostMapping("/many")
    public ResponseVO addVehicles(@RequestBody List<VehicleDTO> vehicleDTOList){

        vehicleService.addVehicles(vehicleDTOList);
        return ResponseVO.success();
    }

    /**
     * 删除机动车数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseVO deleteVehicle(@PathVariable("id") String id){
        vehicleService.deleteVehicle(id);
        return ResponseVO.success();
    }

    /**
     * 编辑机动车数据
     * @param vehicleDTO
     * @return
     */
    @PutMapping
    public ResponseVO updateVehicle(@RequestBody VehicleDTO vehicleDTO){

        vehicleService.updateVehicle(vehicleDTO);
        return ResponseVO.success();
    }

    /**
     * 查询机动车
     */
    @PostMapping("/query")
    public ResponseVO<PageVO<VehicleVO>> findUsers(@RequestBody VehicleQueryDTO vehicleQueryDTO){
        PageVO<VehicleVO> vehicleVoPageVo = vehicleService.findVehicles(vehicleQueryDTO);
        return ResponseVO.success(vehicleVoPageVo);
    }


    /**
     * 车牌归档统计
     */
    @GetMapping("/plate/aggregationStatistics")
    public ResponseVO plateAggregationStatistics(){
        return ResponseVO.success(vehicleService.plateAggregationStatistics());
    }

    /**
     * 获取时段车流统计
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/timeFlow/aggregationStatistics")
    public ResponseVO timeperiodTrafficFlowStatistics(Long startTime,Long endTime){
        return ResponseVO.success(vehicleService.timeperiodTrafficFlowStatistics(startTime,endTime));
    }

    /**
     * 获取车量日流量统计
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/dailyFlow/aggregationStatistics")
    public ResponseVO dailyFlowTrafficFlowStatistics(Long startTime,Long endTime){
        return ResponseVO.success(vehicleService.trafficFlowStatistics(startTime,endTime));
    }

    /**
     * 按车型进行聚合统计
     * @return
     */
    @GetMapping("/model/aggregationStatistics")
    public ResponseVO modelStatistics(){
        return ResponseVO.success(vehicleService.getModelIdentificationRate());
    }

    /**
     * 统计时间戳的最大值 最小值 总和 基数 平均数
     * @return
     */
    @GetMapping("/stats/aggregationStatistics")
    public ResponseVO getStats(){
        return ResponseVO.success(vehicleService.getStats());
    }
}
