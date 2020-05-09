package com.unionman.springbootelasticsearchdemo.entity.bean;

import lombok.Data;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.enums.DataType;

import java.io.Serializable;

/**
 * @author Zhifeng.Zeng
 */
@Data
public class AttributesBean implements Serializable {

    private static final long serialVersionUID = 660173605030351755L;

    @ESMapping(datatype = DataType.integer_type)
    private int attributeId;

    @ESMapping
    private String name;

    @ESMapping
    private String attributeName;

    @ESMapping(datatype = DataType.integer_type)
    private int valueId;

    @ESMapping(datatype = DataType.keyword_type)
    private String valueString;


}

