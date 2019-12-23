package cn.ikkyu.ftp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author xinming
 * @Date 2019/11/23 15:45
 */
@Data
@ApiModel("sdk推送案系统商品基础信息vo")
public class PushArchiveVo {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "商品Id", example = "21312312312")
    private Long id;


    @ApiModelProperty(value = "推送数据类型编码 01/供应商数据   02/商品数据", example = "02")
    private String archiveSynTypeCode;

    /**
     * 供应商编码
     */
    @ApiModelProperty(value = "供应商编码", example = "21312312312")
    private String supplierCode;

    /**
     * 供应商名
     */
    @ApiModelProperty(value = "供应商名", example = "21312312312")
    private String supplierName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", example = "宝石树诺星干红葡萄酒750ML")
    private String goodsName;

    /**
     * 商品分类ID(中类：白酒ID)
     */
    @ApiModelProperty(value = "商品分类编码(中类：白酒编码)", example = "白酒")
    private String goodsCategoryId;

    /**
     * 商品中类名
     */
    @ApiModelProperty(value = "商品分类(中类名)", example = "白酒")
    private String	goodsCategoryName;

    /**
     * 国际条码
     */
    @ApiModelProperty(value = "国际条码", example = "21312312312")
    private String barCode;

    /**
     * 净含量(ml)
     */
    @ApiModelProperty(value = "净含量(ml)", example = "500")
    private String netVolume;

    /**
     * 酒精度(%)
     */
    @ApiModelProperty(value = "酒精度", example = "52")
    private String alcohol;

    /**
     * 国家名称
     */
    @ApiModelProperty(value = "国家名称", example = "中国")
    private String countryName;

    /**
     * 省份/地区名称
     */
    @ApiModelProperty(value = "省份/地区名称", example = "四川",hidden = true)
    private String provinceName;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称", example = "乐山",hidden = true)
    private String cityName;

    /**
     * 生产厂家
     */
    @ApiModelProperty(value = "生产厂家", example = "茅台大酒厂")
    private String manufacturer;

    /**
     * 1919商品编码   入驻成功后才有
     */
    @ApiModelProperty(value = "1919商品编码   入驻成功后才有", example = "21312312312")
    private String goodsCode;

    /**
     * 批次号
     */
    @ApiModelProperty(value = "批次号",example = "2132342342")
    private String batchNumber;


    private List<ProductQualificationReqVo> qualificationList;

}
