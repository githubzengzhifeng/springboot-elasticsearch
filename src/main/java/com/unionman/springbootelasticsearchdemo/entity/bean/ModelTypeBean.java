package com.unionman.springbootelasticsearchdemo.entity.bean;

import lombok.Data;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.enums.DataType;

import java.io.Serializable;

/**
 * @author Zhifeng.Zeng
 */
@Data
public class ModelTypeBean implements Serializable {

    private static final long serialVersionUID = -1311618795850918446L;

    @ESMapping(datatype = DataType.integer_type)
    private Integer styleId;

    @ESMapping
    private String style;


    @ESMapping(datatype = DataType.integer_type)
    private Integer brandId;

    @ESMapping
    private String brand;

    @ESMapping(datatype = DataType.integer_type)
    private Integer subBrandId;

    @ESMapping
    private String subBrand;

    @ESMapping(datatype = DataType.integer_type)
    private Integer modelYearId;

    @ESMapping
    private String modelYear;

    @ESMapping(datatype = DataType.integer_type)
    private Integer poseId;

    @ESMapping
    private String pose;


}
