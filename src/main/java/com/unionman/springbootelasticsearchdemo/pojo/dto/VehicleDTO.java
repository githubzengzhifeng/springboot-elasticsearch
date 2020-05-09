package com.unionman.springbootelasticsearchdemo.pojo.dto;

import com.unionman.springbootelasticsearchdemo.entity.bean.ImageBean;
import com.unionman.springbootelasticsearchdemo.entity.bean.MetadataBean;
import com.unionman.springbootelasticsearchdemo.entity.bean.VehiclePoBean;
import lombok.Getter;
import lombok.Setter;
import org.zxp.esclientrhl.annotation.ESID;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.enums.DataType;

import java.util.List;

/**
 * @author Zhifeng.Zeng
 * @descrption 机动车传输对象
 * @date 2020/05/06 20:12
 */
@Setter
@Getter
public class VehicleDTO{

    private String id;


    /**
     * 其它信息
     */
    private MetadataBean metadata;


    /**
     * 收藏了该条数据的用户列表
     */
    private List<String> usersOfFavorite;

    /**
     * 原图信息
     */
    private ImageBean img;

    /**
     * 机动车信息
     */
    private VehiclePoBean vehicle;


}
