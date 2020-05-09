package com.unionman.springbootelasticsearchdemo.entity.bean;

import lombok.Data;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.enums.DataType;

import java.io.Serializable;

/**
 * @author Zhifeng.Zeng
 */
@Data
public class SymbolsBean implements Serializable {

    private static final long serialVersionUID = -4328355953297966037L;

    @ESMapping(datatype = DataType.integer_type)
    private int symbolId;

    @ESMapping
    private String symbolName;

    @ESMapping(datatype = DataType.nested_type,nested_class = CutboardBean.class)
    private CutboardBean cutboard;


}
