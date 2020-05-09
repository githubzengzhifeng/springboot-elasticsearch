package com.unionman.springbootelasticsearchdemo.entity.bean;


import lombok.Data;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.enums.DataType;

/**
 * @author Zhifeng.Zeng
 */
@Data
public class ImageBean {

    /**
     * 图片的宽度
     */
    @ESMapping(datatype = DataType.integer_type)
    private Integer width;

    /**
     * 图片的高度
     */
    @ESMapping(datatype = DataType.integer_type)
    private Integer height;

    /**
     * 图片的资源标识符，优先于BinData
     */
    @ESMapping
    private String URL;

}
