package com.unionman.springbootelasticsearchdemo.pojo.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PageDTO {

    public PageDTO() {
    }

    public PageDTO(@Min(value = 0, message = "参数必须大于0") Integer pageSize, @NotNull(message = "参数必传，传-1代表不分页") @Min(value = -1, message = "参数必须大于-1") Integer currentPage) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    private Integer pageSize;

    private Integer currentPage;

    private String orderType;

    private String orderField;

    public Integer getPageSize() {
        return pageSize != null ? pageSize : 20;
    }

    public Integer getCurrentPage() {
        return currentPage != null ? currentPage : 0;
    }

    private Long startTime;

    private Long endTime;



}
