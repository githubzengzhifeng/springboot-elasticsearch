package com.unionman.springbootelasticsearchdemo.entity.bean;

import lombok.Getter;
import lombok.Setter;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.enums.DataType;

import java.io.Serializable;

/**
 * @author Zhifeng.Zeng
 */
@Setter
@Getter
public class CutboardBean implements Serializable {

    private static final long serialVersionUID = 1413228955926158764L;

    public CutboardBean() {
    }

    public CutboardBean(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @ESMapping(datatype = DataType.integer_type)
    private int x;

    @ESMapping(datatype = DataType.integer_type)
    private int y;

    @ESMapping(datatype = DataType.integer_type)
    private int width;

    @ESMapping(datatype = DataType.integer_type)
    private int height;



}

