package cn.ikkyu.ftp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author xinming
 * @Date 2019/11/22 15:00
 */
@Data
@ApiModel("sdk推送档案系统商品资质vo")
public class ProductQualificationReqVo {

    /**
     * 资质类型
     */
    @ApiModelProperty(value = "资质类型")
    private PictureType goodsQualificationTypeCode;

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
     *    资质图片(多个时以逗号分隔)
     */
    @ApiModelProperty(value = "资质图片(多个时以逗号分隔)",example = "21312312312")
    private	String	qualificationPicture;


}
