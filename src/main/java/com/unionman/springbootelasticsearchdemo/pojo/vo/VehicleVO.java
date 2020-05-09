package com.unionman.springbootelasticsearchdemo.pojo.vo;

import com.unionman.springbootelasticsearchdemo.entity.bean.ImageBean;
import com.unionman.springbootelasticsearchdemo.entity.bean.MetadataBean;
import com.unionman.springbootelasticsearchdemo.entity.bean.VehiclePoBean;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Zhifeng.Zeng
 * @descrption
 * @date 2020/05/06 20:13
 */
@Setter
@Getter
public class VehicleVO {

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
