package xyz.ikkyu.sample.test.domain;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author xinming
 * 商品资质类型
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum GoodsQualificationType implements Serializable {
    /**
     * 商品资质类型
     */
    QUALITY_COMMITMENT(1,"赠品质量承诺函", Lists.newArrayList()),
    LEVEL_BY_LEVEL_SALES_AUTHORIZATION(2,"生产商至贵公司的逐级销售经营授权", Lists.newArrayList("55","102")),
    SALES_AUTHORIZATION_19(3,"1919销售经营授权", Lists.newArrayList("54","56")),
    CUSTOMS_DECLARATION(4,"报关单", Lists.newArrayList("60","61")),
    INSPECTION_REPORT(5,"质检报告", Lists.newArrayList("59")),
    GOODS_PICTURE(6,"商品图片", Lists.newArrayList()),
    TRADEMARK_AUTHORIZATION(7,"商标授权", Lists.newArrayList("58","63")),
    PROCESS_OA_SCREENSHOT(8,"oa流程截图", Lists.newArrayList()),
    DEFERRED_COMMITMENT(9,"延期承诺书", Lists.newArrayList()),
    PURCHASE_CONTRACT(10,"供应链采购合同", Lists.newArrayList()),
    HEALTH_INSPECTION(2,"卫检", Lists.newArrayList()),
    ;
    @ApiModelProperty("编码")
    private int value;
    @ApiModelProperty("描述")
    private String desc;
    @ApiModelProperty("老srm资质对应")
    private List<String> oldSrmQuaIds;

    public static Optional<GoodsQualificationType> valueQuaIdOf(String oldSrmQuaId) {
        return Arrays.stream(GoodsQualificationType.values())
            .filter(type -> type.getOldSrmQuaIds().contains(oldSrmQuaId))
            .findFirst();
    }
}
