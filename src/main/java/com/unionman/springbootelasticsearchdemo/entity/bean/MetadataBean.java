package com.unionman.springbootelasticsearchdemo.entity.bean;

import lombok.Data;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.enums.DataType;

/**
 * @author sihui.sha
 * @description: 监控点，区域等基本信息
 * @date 2017/12/29
 */
@Data
public class MetadataBean {

    /**
     * 时间戳
     */
    @ESMapping(datatype = DataType.long_type)
    private Long timestamp;

    /**
     * 监控点编号，private Integer，不唯一
     */
    @ESMapping(datatype = DataType.keyword_type)
    private String cameraInfoIndexCode;

    /**
     * 监控点名
     */
    @ESMapping
    private String cameraInfoName;

    /**
     * 监控点ip地址
     */
    @ESMapping
    private String cameraInfoIp;

    /**
     * 组织编号
     */
    @ESMapping(datatype = DataType.keyword_type)
    private String organizationIndexCode;

    /**
     * 组织名
     */
    @ESMapping
    private String organizationName;

    /**
     * 卡口编号
     */
    @ESMapping(datatype = DataType.keyword_type)
    private String tollgateIndexCode;

    /**
     * 卡口名
     */
    @ESMapping
    private String tollgateName;

    /**
     * 方位Id
     */
    @ESMapping(datatype = DataType.integer_type)
    private Integer positionId;

    /**
     * 行驶方向
     */
    @ESMapping
    private String drivingDirectionOf;


}
