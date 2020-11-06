package xyz.ikkyu.sample.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

public class JsonResult<T> {
    @ApiModelProperty(
        value = "服务器RPC响应code",
        example = "200"
    )
    private int code;
    @ApiModelProperty(
        value = "执行结果描述",
        example = "success"
    )
    private String msg;
    @ApiModelProperty(
        value = "服务器结果返回时的 Unix timestamp,单位毫秒",
        example = "1356019200000"
    )
    private long ts;
    @ApiModelProperty(
        name = "data",
        value = "响应结果",
        example = "{}"
    )
    private T data;

    public JsonResult(int code, String msg) {
        this.code = 200;
        this.msg = "success";
        this.ts = System.currentTimeMillis();
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(int code, String msg, T data) {
        this.code = 200;
        this.msg = "success";
        this.ts = System.currentTimeMillis();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isOK() {
        return this.code == 200 || this.code == 0;
    }

    public JsonResult(T data) {
        this.code = 200;
        this.msg = "success";
        this.ts = System.currentTimeMillis();
        this.data = data;
    }



    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public long getTs() {
        return this.ts;
    }

    public T getData() {
        return this.data;
    }

    public JsonResult() {
        this.code = 200;
        this.msg = "success";
        this.ts = System.currentTimeMillis();
    }
}
