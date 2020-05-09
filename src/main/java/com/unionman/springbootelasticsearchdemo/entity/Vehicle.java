package com.unionman.springbootelasticsearchdemo.entity;


import com.unionman.springbootelasticsearchdemo.entity.bean.ImageBean;
import com.unionman.springbootelasticsearchdemo.entity.bean.MetadataBean;
import com.unionman.springbootelasticsearchdemo.entity.bean.VehiclePoBean;
import lombok.Data;
import org.zxp.esclientrhl.annotation.ESID;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.annotation.ESMetaData;
import org.zxp.esclientrhl.enums.DataType;

import java.io.Serializable;
import java.util.List;

/**
 * @author Zhifeng.Zeng
 * @description 机动车实体
 * @date 2019/4/10 10:02
 */
@Data
@ESMetaData(indexName = "um_t_vehicle",number_of_replicas = 0,number_of_shards = 5)
public class Vehicle implements Serializable {

    @ESID
    private String id;

    private static final long serialVersionUID = -6262522813705996337L;

    /**
     * 其它信息
     */
    @ESMapping(datatype = DataType.nested_type,nested_class = MetadataBean.class)
    private MetadataBean metadata;


    /**
     * 收藏了该条数据的用户列表
     */
    @ESMapping(datatype = DataType.keyword_type)
    private List<String> usersOfFavorite;

    /**
     * 原图信息
     */
    @ESMapping(datatype = DataType.nested_type,nested_class = ImageBean.class)
    private ImageBean img;

    /**
     * 机动车信息
     */
    @ESMapping(datatype = DataType.nested_type,nested_class = VehiclePoBean.class)
    private VehiclePoBean vehicle;

}
