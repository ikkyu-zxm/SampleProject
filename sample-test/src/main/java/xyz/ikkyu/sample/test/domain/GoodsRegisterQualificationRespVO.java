package xyz.ikkyu.sample.test.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xinming
 */
@Data
@ApiModel("商品资质响应VO")
public class GoodsRegisterQualificationRespVO implements Serializable {

    private static final long serialVersionUID = -3307770906823022779L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "id",example = "21312312312")
    private	Long id;


    /**
     *  商品注册信息ID
     */
    @ApiModelProperty(value = "商品注册信息ID",example = "21312312312")
    private	Long goodsRegisterInfoId;


    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期",example = "21312312312")
    private	Long deadLine;

    @ApiModelProperty(value = "开始日期",example = "21312312312")
    private Long startDate;


    /**
     * 永久有效
     */
    @ApiModelProperty(value = "永久有效",example = "false")
    private	Boolean	longTermLicense;

    /**
     * 资质类型（资质名称）
     */
    @ApiModelProperty(value = "资质名称",example = "赠品质量承诺函")
    private String qualificationType;


    /**
     * 资质类型（维护到配置描述表、或者本地维护实体表）
     */
    @ApiModelProperty(value = "资质类型编码")
    private GoodsQualificationType goodsQualificationTypeCode;


    /**
     *    资质图片(多个时以逗号分隔)
     */
    @ApiModelProperty(value = "资质图片(多个时以逗号分隔)",example = "21312312312")
    private	String	qualificationPicture;

    /**
     * 是否豁免
     * true ：豁免
     * false ：正常入驻
     */
    @ApiModelProperty(value = "是否豁免资质",example = "false")
    private Boolean exempt;

    /**
     *   豁免资质最迟提交日期（）
     *   TODO  不用
     */
    @ApiModelProperty(value = "豁免资质最迟提交日期",example = "1550240934540")
    private Long exemptDeadLine;


    /**
     * 资质期限说明
     */
    @ApiModelProperty(value = "资质期限说明",example = "食品许可证2019年1月31日到期")
    private String instructions;


}
