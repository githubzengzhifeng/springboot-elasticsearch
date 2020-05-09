package com.unionman.springbootelasticsearchdemo.service.impl;

import com.unionman.springbootelasticsearchdemo.entity.Vehicle;
import com.unionman.springbootelasticsearchdemo.entity.bean.VehiclePoBean;
import com.unionman.springbootelasticsearchdemo.pojo.dto.VehicleDTO;
import com.unionman.springbootelasticsearchdemo.pojo.dto.VehicleQueryDTO;
import com.unionman.springbootelasticsearchdemo.pojo.vo.PageVO;
import com.unionman.springbootelasticsearchdemo.pojo.vo.VehicleVO;
import com.unionman.springbootelasticsearchdemo.repository.VehicleRepository;
import com.unionman.springbootelasticsearchdemo.service.VehicleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zxp.esclientrhl.repository.PageList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Zhifeng.Zeng
 * @descrption
 * @date 2020/05/06 20:11
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void addVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleDTO, vehicle);
        vehicleRepository.addVehicle(vehicle);

    }

    @Override
    public void addVehicles(List<VehicleDTO> vehicleDTOList) {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleDTOList.forEach(vehicleDTO->{
            Vehicle vehicle = new Vehicle();
            BeanUtils.copyProperties(vehicleDTO,vehicle);
            vehicle.setId(null);
            vehicleList.add(vehicle);
        });
        vehicleRepository.addVehicles(vehicleList);
    }

    @Override
    public void deleteVehicle(String id) {
        vehicleRepository.deleteVehicle(id);
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleDTO, vehicle);
        vehicleRepository.updateVehicle(vehicle);
    }

    @Override
    public PageVO<VehicleVO> findVehicles(VehicleQueryDTO vehicleQueryDTO) {

        // 车牌
        String plateText = vehicleQueryDTO.getPlateText();
        if (plateText != null) {

            //小写字母转大写 去空格
            plateText = plateText.toUpperCase().trim();
            vehicleQueryDTO.setPlateText(getPlateText(plateText));
        }

        PageVO<VehicleVO> pageVO = new PageVO<>();
        PageList<Vehicle> pageList = vehicleRepository.findVehicles(vehicleQueryDTO);
        List<Vehicle> vehicleList = pageList.getList();
        if (vehicleList != null && vehicleList.size() > 0) {
            List<VehicleVO> vehicleVoList = new ArrayList<>();
            vehicleList.forEach(vehicle -> {
                VehicleVO vehicleVO = new VehicleVO();
                BeanUtils.copyProperties(vehicle, vehicleVO);
                vehicleVoList.add(vehicleVO);
            });
            pageVO.setTotalPages(pageList.getTotalPages());
            pageVO.setPageSize(vehicleQueryDTO.getPageSize());
            pageVO.setTotal(pageList.getTotalElements());
            pageVO.setRecords(vehicleVoList);
            pageVO.setCurrentPage(vehicleQueryDTO.getCurrentPage());
        }

        return pageVO;
    }

    @Override
    public Map<String, Long> plateAggregationStatistics() {
        Map<String, Long>  plateFilesNumber = vehicleRepository.plateAggregationStatistics();
        return plateFilesNumber;
    }

    @Override
    public Map<String, Long> timeperiodTrafficFlowStatistics(Long startTime, Long endTime) {
        return vehicleRepository.timeperiodTrafficFlowStatistics(startTime,endTime);
    }

    @Override
    public Map<String, Long> trafficFlowStatistics(Long startTime, Long endTime) {
        return vehicleRepository.trafficFlowStatistics(startTime,endTime);

    }

    @Override
    public Map<String, Long> getModelIdentificationRate() {
        return vehicleRepository.getModelIdentificationRate();
    }

    @Override
    public Map<String, Long> getStats() {
        return vehicleRepository.getStats();

    }


    /**
     * @param plateText 车牌号
     * @return String 车牌号正则
     * @description 车牌判断
     * @date 2019/04/30 10:59:22
     */
    private String getPlateText(String plateText) {
        if (plateText != null) {
            //车牌正则：普通车牌，警车及港澳台，教练车
            if (!plateText.matches("(^[\u4E00-\u9FA5]{1}[A-Z0-9]{7}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)")) {
                if (StringUtils.contains(plateText, "%")) {
                    plateText = StringUtils.replace(plateText, "%", ".*");
                }
                if (StringUtils.contains(plateText, "$")) {
                    plateText = StringUtils.replace(plateText, "$", ".*");
                }
            }
        }
        return plateText;
    }
}
