package com.unionman.springbootelasticsearchdemo.entity.bean;

import lombok.Data;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.enums.DataType;

import java.io.Serializable;
import java.util.List;

/**
 * @author Zhifeng.Zeng
 */
@Data
public class PassengersBean implements Serializable {

    private static final long serialVersionUID = -2429429190737942761L;

    @ESMapping(datatype = DataType.integer_type)
    private int id;

    @ESMapping(datatype = DataType.boolean_type)
    private Boolean driver;

    @ESMapping(datatype = DataType.nested_type,nested_class = CutboardImgBean.class)
    private CutboardImgBean img;

    @ESMapping(datatype = DataType.integer_type)
    private int phoneFlag;

    @ESMapping(datatype = DataType.integer_type)
    private int beltFlag;

    @ESMapping(datatype = DataType.integer_type)
    private int facecoverFlag;

    @ESMapping(datatype = DataType.integer_type)
    private int smokingFlag;

    @ESMapping(datatype = DataType.nested_type,nested_class = AttributesBean.class)
    private List<AttributesBean> attributes;

}
