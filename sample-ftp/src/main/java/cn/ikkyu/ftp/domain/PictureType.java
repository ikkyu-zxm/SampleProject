package cn.ikkyu.ftp.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author xinming
 * @Date 2019/11/22 15:07
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PictureType {

    /**
     * 图片类型
     */
    CUSTOMS_DECLARATION(0,"报关单"),
    INSPECTION_REPORT(1,"质检报告"),
    HEALTH_INSPECTION(2,"卫检"),
    GOODS_PICTURE(3,"商品图片");

    @ApiModelProperty("编码")
    private int value;
    @ApiModelProperty("描述")
    private String desc;
}
