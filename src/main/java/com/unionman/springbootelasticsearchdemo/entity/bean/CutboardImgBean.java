package com.unionman.springbootelasticsearchdemo.entity.bean;

import lombok.Data;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.enums.DataType;

import java.io.Serializable;

/**
 * @author Zhifeng.Zeng
 */
@Data
public class CutboardImgBean implements Serializable {

    private static final long serialVersionUID = -7511528677609709363L;

    @ESMapping(datatype = DataType.nested_type,nested_class = CutboardBean.class)
    private CutboardBean cutboard;



}
