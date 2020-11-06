package xyz.ikkyu.sample.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author xinming
 */
@Data
@ApiModel(description = "返回sap需求单接收状态")
public class RpcRst {
    /**
     * 需求单接收状态码   0：成功  1：采购公司未找到  -1：接收异常
     */
    @ApiModelProperty(value = "需求单接收状态码",example = "0")
    private int code;
    /**
     * 需求单接收说明
     */
    @ApiModelProperty(value = "需求单接收说明",example = "操作成功")
    private String message;
}
