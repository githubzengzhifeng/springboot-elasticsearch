package com.unionman.springbootelasticsearchdemo.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author Rong.Jia
 * @description 分页查询表现层对象
 * @date 2019/02/19 18:00:22
 */
@Data
public class PageVO<T> implements Serializable {

    private static final long serialVersionUID = -3826579718601386597L;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 页码
     */
    private Integer currentPage;

    /**
     * 查询数据列表
     */
    private List<T> records;

    /**
     * 当前页是否为第一页
     */
    private Boolean isFirst;

    /**
     * 当前页是否为最后一页
     */
    private Boolean isLast;

    /**
     * 如果有下一页
     */
    private Boolean hasNext;

    /**
     * 如果有上一页
     */
    private Boolean hasPrevious;


    public List<T> getRecords() {
        return records == null ? Collections.emptyList() : records;
    }

    public Long getTotal() {
        return total == null ? 0L : total;
    }
}
