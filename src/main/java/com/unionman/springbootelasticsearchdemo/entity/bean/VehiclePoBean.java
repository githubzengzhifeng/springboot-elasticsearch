package com.unionman.springbootelasticsearchdemo.entity.bean;


import lombok.Data;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.enums.DataType;

import java.util.List;

/**
 * @author Zhifeng.Zeng
 */
@Data
public class VehiclePoBean {

    @ESMapping(datatype = DataType.integer_type)
    private int id;

    /**
     * 图片URL
     */
    @ESMapping
    private String insetsUrl;

    @ESMapping(datatype = DataType.nested_type,nested_class = CutboardBean.class)
    private CutboardBean cutboard;

    @ESMapping(datatype = DataType.integer_type)
    private int colorId;

    @ESMapping
    private String colorName;

    /**
     * 特征id，系统生成
     */
    @ESMapping(datatype = DataType.keyword_type)
    private String featuresId;

    @ESMapping
    private String features;

    /**
     * 车辆行走状态
     */
    @ESMapping
    private String state;

    /**
     * 是否存在套牌嫌疑
     */
    @ESMapping(datatype = DataType.boolean_type)
    private Boolean isDeckSuspect;


    @ESMapping(datatype = DataType.nested_type,nested_class = ModelTypeBean.class)
    private ModelTypeBean modelType;


    @ESMapping(datatype = DataType.keyword_type)
    private String plateText;

    @ESMapping(datatype = DataType.integer_type)
    private int plateColorId;

    @ESMapping
    private String plateColorName;

    @ESMapping(datatype = DataType.integer_type)
    private int plateStyleId;

    @ESMapping
    private String plateStyleName;


    @ESMapping(datatype = DataType.nested_type,nested_class = CutboardBean.class)
    private CutboardBean plateCutboard;


    @ESMapping(datatype = DataType.nested_type,nested_class = SymbolsBean.class)
    private List<SymbolsBean> symbols;

    @ESMapping(datatype = DataType.nested_type,nested_class = PassengersBean.class)
    private List<PassengersBean> passengers;

}
