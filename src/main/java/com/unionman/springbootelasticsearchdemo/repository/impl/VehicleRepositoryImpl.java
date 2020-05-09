package com.unionman.springbootelasticsearchdemo.repository.impl;

import com.alibaba.fastjson.JSON;
import com.unionman.springbootelasticsearchdemo.entity.Vehicle;
import com.unionman.springbootelasticsearchdemo.entity.bean.VehiclePoBean;
import com.unionman.springbootelasticsearchdemo.pojo.dto.VehicleBrandIdDTO;
import com.unionman.springbootelasticsearchdemo.pojo.dto.VehicleDTO;
import com.unionman.springbootelasticsearchdemo.pojo.dto.VehicleQueryDTO;
import com.unionman.springbootelasticsearchdemo.repository.VehicleRepository;
import com.unionman.springbootelasticsearchdemo.utils.AssertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.metrics.Stats;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zxp.esclientrhl.repository.ElasticsearchTemplate;
import org.zxp.esclientrhl.repository.PageList;
import org.zxp.esclientrhl.repository.PageSortHighLight;

import java.util.*;

/**
 * @author Zhifeng.Zeng
 * @descrption
 * @date 2020/05/06 20:11
 */
@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    public static final String METADATA_NESTED = "metadata";

    public static final String TIMESTAMP = "metadata.timestamp";

    public static final String MODEL_STYLE = "vehicle.modelType.style";

    public static final String VEHICLE_NESTED = "vehicle";

    public static final String VEHICLE_SYMBOLS_NESTED = "vehicle.symbols";

    public static final String VEHICLE_SYMBOL_ID = "vehicle.symbols.symbolId";

    public static final String VEHICLE_PLATE_TEXT = "vehicle.plateText";

    public static final String MODEL_TYPE = "vehicle.modelType";

    public static final String MODEL_STYLE_ID = "vehicle.modelType.styleId";

    public static final String MODEL_BRAND_ID = "vehicle.modelType.brandId";

    public static final String MODEL_SUB_BRAND_ID = "vehicle.modelType.subBrandId";

    public static final String COLOR_ID = "vehicle.colorId";

    public static final String VEHICLE_PLATE_STYLE_ID = "vehicle.plateStyleId";

    public static final String VEHICLE_PLATE_COLOR_ID = "vehicle.plateColorId";

    public static final String TOLLGATE_INDEX_CODE = "metadata.tollgateIndexCode";


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void addVehicle(Vehicle vehicle) {
        try {
            elasticsearchTemplate.save(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        try {
            elasticsearchTemplate.updateCover(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVehicle(String id) {
        try {
            elasticsearchTemplate.deleteById(id, Vehicle.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageList<Vehicle> findVehicles(VehicleQueryDTO vehicleQueryDTO) {

        String plateText = vehicleQueryDTO.getPlateText();
        Long startTime = vehicleQueryDTO.getStartTime();
        Long endTime = vehicleQueryDTO.getEndTime();
        List<Integer> plateTypes = vehicleQueryDTO.getPlateTypes();
        List<Integer> vehicleColors = vehicleQueryDTO.getVehicleColors();
        List<Integer> vehicleMarkings = vehicleQueryDTO.getVehicleMarkings();
        List<Integer> plateColors = vehicleQueryDTO.getPlateColors();

        //车辆品牌
        List<VehicleBrandIdDTO> brandIds = vehicleQueryDTO.getVehicleBrandIds();

        //拼接查询条件
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        // 车牌号条件
        plateConditions(queryBuilder, plateText, plateTypes, plateColors, vehicleQueryDTO.getIdentifier(), vehicleQueryDTO.getPassingType());

        // 车辆品牌
        if (AssertUtils.isNotNull(brandIds)) {
            List<Integer> vehicleBrandIds = new ArrayList<>();
            List<Integer> vehicleSubBrandIds = new ArrayList<>();
            for (VehicleBrandIdDTO vehicleBrandIdDTO : brandIds) {
                if (AssertUtils.isFirstNotNullAndSecondNull(vehicleBrandIdDTO.getVehicleBrandId(), vehicleBrandIdDTO.getVehicleSuBrandIds())) {
                    vehicleBrandIds.add(vehicleBrandIdDTO.getVehicleBrandId());
                } else {
                    vehicleBrandIds.add(vehicleBrandIdDTO.getVehicleBrandId());
                    vehicleBrandIdDTO.getVehicleSuBrandIds().forEach(vehicleSuBrand -> {
                        vehicleSubBrandIds.add(vehicleSuBrand.getVehicleSuBrandId());
                    });
                }
            }
            if (AssertUtils.isNotNull(vehicleBrandIds)) {
                queryBuilder.must(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.nestedQuery(MODEL_TYPE, QueryBuilders.termsQuery(MODEL_BRAND_ID, vehicleBrandIds), ScoreMode.Total), ScoreMode.Total));
                if (AssertUtils.isNotNull(vehicleSubBrandIds)) {
                    queryBuilder.must(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.nestedQuery(MODEL_TYPE, QueryBuilders.termsQuery(MODEL_SUB_BRAND_ID, vehicleSubBrandIds), ScoreMode.Total), ScoreMode.Total));
                }
            }
        }

        // 车辆颜色
        if (AssertUtils.isNotNull(vehicleColors)) {
            queryBuilder.must(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.termsQuery(COLOR_ID, vehicleColors), ScoreMode.Total));
        }

        // 标识物
        if (AssertUtils.isNotNull(vehicleMarkings)) {
            queryBuilder.must(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.nestedQuery(VEHICLE_SYMBOLS_NESTED, QueryBuilders.termsQuery(VEHICLE_SYMBOL_ID, vehicleMarkings), ScoreMode.Total), ScoreMode.Total));
        }


        // 时间
        if (AssertUtils.isNotNull(startTime) && AssertUtils.isNotNull(endTime)) {
            queryBuilder.must(QueryBuilders.nestedQuery(METADATA_NESTED, QueryBuilders.rangeQuery(TIMESTAMP).from(startTime).to(endTime), ScoreMode.Total));
        }

        // 卡口
        if (AssertUtils.isNotNull(vehicleQueryDTO.getTollgateIndexCodes())) {
            queryBuilder.must(QueryBuilders.nestedQuery(METADATA_NESTED, QueryBuilders.termsQuery(TOLLGATE_INDEX_CODE, vehicleQueryDTO.getTollgateIndexCodes()), ScoreMode.Total));

        }

        // 排序方式
        PageSortHighLight psh = new PageSortHighLight(vehicleQueryDTO.getCurrentPage(), vehicleQueryDTO.getPageSize());
        org.zxp.esclientrhl.repository.Sort.Order order = new org.zxp.esclientrhl.repository.Sort.Order(SortOrder.DESC, TIMESTAMP);
        psh.setSort(new org.zxp.esclientrhl.repository.Sort(order));
        PageList<Vehicle> vehiclePageList = null;
        try {
            vehiclePageList = elasticsearchTemplate.search(queryBuilder, psh, Vehicle.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vehiclePageList;
    }

    @Override
    public void addVehicles(List<Vehicle> vehicleList) {
        try {
            elasticsearchTemplate.save(vehicleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Long> plateAggregationStatistics() {

        return elasticsearchTemplate.nestedBucketAggs(VEHICLE_NESTED, null, VEHICLE_PLATE_TEXT, Vehicle.class, true, 10000000);

    }

    @Override
    public Map<String, Long> timeperiodTrafficFlowStatistics(Long startTime, Long endTime) {
        // 拼接查询条件
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.nestedQuery(METADATA_NESTED, QueryBuilders.rangeQuery(TIMESTAMP).from(startTime).to(endTime), ScoreMode.Total));

        return elasticsearchTemplate.nestedDateForHourHistogramAggs(METADATA_NESTED, queryBuilder, TIMESTAMP, Vehicle.class);
    }


    @Override
    public Map<String, Long> trafficFlowStatistics(Long startTime, Long endTime) {
        // 拼接查询条件
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.nestedQuery(METADATA_NESTED, QueryBuilders.rangeQuery(TIMESTAMP).from(startTime).to(endTime), ScoreMode.Total));

        return elasticsearchTemplate.nestedDateForDayHistogramAggs(METADATA_NESTED, queryBuilder, TIMESTAMP, Vehicle.class);
    }

    @Override
    public Map<String, Long> getModelIdentificationRate() {
        return  elasticsearchTemplate.nestedBucketAggs(MODEL_TYPE, null, MODEL_STYLE, Vehicle.class, false);

    }

    @Override
    public Map<String, Long> getStats() {
        Stats stats = elasticsearchTemplate.nestedStatsAggs(METADATA_NESTED, null, TIMESTAMP, Vehicle.class);
        Map<String, Long> map = new HashMap<>();
        map.put("max",Double.valueOf(stats.getMax()).longValue());
        map.put("min",Double.valueOf(stats.getMin()).longValue());
        map.put("sum",Double.valueOf(stats.getSum()).longValue());
        map.put("count",Double.valueOf(stats.getCount()).longValue());
        map.put("avg",Double.valueOf(stats.getAvg()).longValue());
        return map;
    }


    /**
     * @param plateText   车牌号
     * @param plateTypes  车牌类型
     * @param passingType 过车类型
     * @param identifier  标识符
     * @return 车牌，车牌类型条件
     */
    private void plateConditions(BoolQueryBuilder queryBuilder, String plateText, List<Integer> plateTypes, List<Integer> plateColors, Boolean identifier, Byte passingType) {
        if (identifier) {
            if (AssertUtils.isNotNull(plateText)) {
                if (plateText.matches("(^[\u4E00-\u9FA5]{1}[A-Z0-9]{7}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)")) {

                    queryBuilder.must(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.termQuery(VEHICLE_PLATE_TEXT, plateText), ScoreMode.Total));
                } else {

                    if (StringUtils.contains(plateText, "*") || StringUtils.contains(plateText, "?")) {
                        queryBuilder.must(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.wildcardQuery(VEHICLE_PLATE_TEXT, plateText), ScoreMode.Total));

                    } else {
                        queryBuilder.must(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.wildcardQuery(VEHICLE_PLATE_TEXT, "*" + plateText + "*"), ScoreMode.Total));
                    }
                }
            }
        } else {
            if (AssertUtils.isNotNull(plateText)) {
                if (plateText.matches("(^[\u4E00-\u9FA5]{1}[A-Z0-9]{7}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)")) {
                    queryBuilder.mustNot(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.termQuery(VEHICLE_PLATE_TEXT, plateText), ScoreMode.Total));
                } else {
                    if (StringUtils.contains(plateText, "*") || StringUtils.contains(plateText, "?")) {
                        queryBuilder.mustNot(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.wildcardQuery(VEHICLE_PLATE_TEXT, plateText), ScoreMode.Total));

                    } else {
                        queryBuilder.mustNot(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.wildcardQuery(VEHICLE_PLATE_TEXT, "*" + plateText + "*"), ScoreMode.Total));
                    }
                }
            }
        }

        // 车牌类型
        if (AssertUtils.isNotNull(plateTypes)) {
            queryBuilder.must(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.termsQuery(VEHICLE_PLATE_STYLE_ID, plateTypes), ScoreMode.Total));
        }

        // 车牌颜色
        if (AssertUtils.isNotNull(plateColors)) {
            queryBuilder.must(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.termsQuery(VEHICLE_PLATE_COLOR_ID, plateColors), ScoreMode.Total));
        }

        // 过车类型
        if (AssertUtils.isNotNull(passingType)) {
            if (AssertUtils.isEquals(0, passingType.intValue())) {
                queryBuilder.mustNot(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.rangeQuery(VEHICLE_PLATE_STYLE_ID).lte(0), ScoreMode.Total));

            } else if (AssertUtils.isEquals(-1, passingType.intValue())) {
                queryBuilder.must(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.termQuery(VEHICLE_PLATE_STYLE_ID, -1), ScoreMode.Total));

            } else {
                queryBuilder.must(QueryBuilders.nestedQuery(VEHICLE_NESTED, QueryBuilders.termQuery(VEHICLE_PLATE_STYLE_ID, 0), ScoreMode.Total));
            }
        }
    }
}
