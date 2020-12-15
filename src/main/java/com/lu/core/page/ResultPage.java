package com.lu.core.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页数据对象
 *
 * @author lzkj
 */
@ApiModel(description = "分页数据")
@Data
public class ResultPage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 列表数据
     */
    private T data;

    /**
     * 分页信息
     */
    private Page page;

    public ResultPage() {
    }

    public ResultPage(int code, String msg, T data, Page page) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.page = page;
    }

    @Data
    @ApiModel(description = "分页信息")
    public class Page {
        @ApiModelProperty(value = "第几页")
        long pageNum;

        @ApiModelProperty(value = "页大小")
        long pageSize;

        @ApiModelProperty(value = "总页数")
        long pages;

        @ApiModelProperty(value = "总条数")
        long total;

        public Page(long pageNum, long pageSize, long pages, long total) {
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.pages = pages;
            this.total = total;
        }

    }
}
