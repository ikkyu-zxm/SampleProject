package cn.ikkyu.sample.test.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 档案部查看商品详细信息
 *
 * @author Nicolll
 * @version v1.0
 * @date 2019/2/18
 * @description
 */
@Data
@ApiModel("档案部商品详细信息")
public class BackArchivesGoodsInfoRespVO extends GoodsBaseInfoRespVO {
    /**
     * 1.2.9.3
     * 修改资质是否推送
     */
    @ApiModelProperty(value = "修改资质是否推送")
    private Boolean updateQualificationPush;

    /**
     * 商品资质信息
     */
    @ApiModelProperty(value = "商品资质信息")
    private List<GoodsRegisterQualificationRespVO> qualificationList;
    /**
     * 合同附件OSS名称 多个附件用,分隔
     */
    @ApiModelProperty(value = "合同附件OSS名称", example = "2131231 合同  2312")
    private String contractFiles;

    private Long contractDeadline;


    private Long startDate;

    @ApiModelProperty(value = "引荐人")
    private String referrer;


    @ApiModelProperty("入驻省份")
    private String provinceInfoStr;
}
