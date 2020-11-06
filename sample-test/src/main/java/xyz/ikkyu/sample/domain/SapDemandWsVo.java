package xyz.ikkyu.sample.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author xinming
 * @Date 2020/1/4 12:07
 */
@Data
@ApiModel(description = "sap 下发需求单信息")
public class SapDemandWsVo {

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期", example = "")
    private String badat;
    /**
     * 采购申请单号
     */
    @ApiModelProperty(value = "采购申请单号", example = "1110000099")
    private String banfn;
    /**
     * 行项目  行号
     */
    @ApiModelProperty(value = "行号", example = "10")
    private String bnfpo;
    /**
     * 采购公司代码
     */
    @ApiModelProperty(value = "采购公司代码", example = "5010")
    private String bukrs;
    /**
     * 是否紧急    002: 紧急
     */
    @ApiModelProperty(value = "是否紧急", example = "002")
    private String dispo;
    /**
     * 交货日期
     */
    @ApiModelProperty(value = "交货日期", example = "yyyyMMdd")
    private String eeind;
    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码", example = "453232")
    private String matnr;
    /**
     * 需求数量
     */
    @ApiModelProperty(value = "需求数量", example = "340")
    private String menge;
    /**
     * 仓库编码   todo（是否不为门店编码） 到门店的情况是否为空
     */
    @ApiModelProperty(value = "仓库编码", example = "7033")
    private String werks;
    /**
     * 交货地址   todo 和库存地址
     */
    @ApiModelProperty(value = "交货地址", example = "0000023776")
    private String zzadrc;
    /**
     * 库存地点    todo 1001代表省仓采购 其他为门店编码（待确定）
     */
    @ApiModelProperty(value = "库存地点", example = "1001")
    private String lgort;
    /**
     * 库存地点描述
     */
    @ApiModelProperty(value = "库存地点描述", example = "四川省仓")
    private String lgobe;

}
