package cn.ikkyu.sample.test.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author xinming
 */
@Data
@ApiModel("商品基础信息")
public class GoodsBaseInfoRespVO implements Serializable {


    private static final long serialVersionUID = 7526956023125179362L;

    /**
     * 1.2.11.2
     * 最小类分类code
     */
    private  String sapSmallCategoryCode;
    /**1.2.11.2{[id,code,name]}
     * 商品中心分类json数组
     */
    @ApiModelProperty("商品中心分类")
    private String categoryJsonStr;
    /**
     * 其他系列(为true表示该系列在SAP未建档)
     */
    @ApiModelProperty("其他系列")
    private Boolean unfiledSeries;

    /**
     * 商品系列Code
     */
    @ApiModelProperty("商品系列Code")
    private String goodsSeriesCode;


    /**
     * 系列名称
     */
    @ApiModelProperty("系列名称")
    private String seriesName;
    /**
     * 商品中心扩展属性提交外部系统编码,用来获取外部扩展属性
     */
    @ApiModelProperty("商品中心扩展属性提交外部系统编码,用来获取外部扩展属性")
    private String externalSysCode;

    //1.2.11.2 end

    /**
     * 是否审核通过
     */
    @ApiModelProperty(value = "是否审核通过 true：通过",example = "false")
    private Boolean reviewPass;

    /**
     * 商品标识  商品Id
     */
    @ApiModelProperty(value = "商品标识",example = "84645")
    private Long goodsRegisterInfoId;


    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位", example = "ML")
    private String measurementUnit;


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
     * 供应链名称
     */
    @ApiModelProperty(value = "供应链名称",example = "四川省供")
    private String supplyChainName;

    /**
     * 供应链编码
     */
    @ApiModelProperty(value = "供应链编码",example = "1066")
    private String supplyChainCode;


    /**
     * 删除标记(预留)
     */
    @ApiModelProperty(value = "删除标记(预留)", example = "false",hidden = true)
    private Boolean deleted;

    /**
     * 启用标记(预留)
     */
    @ApiModelProperty(value = "启用标记(预留)", example = "false",hidden = true)
    private Boolean enabled;


    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", example = "宝石树诺星干红葡萄酒750ML")
    private String goodsName;

    /**
     * 商品具体分类(酒类/白酒)
     */
    @ApiModelProperty(value = "商品具体分类", example = "酒类/白酒")
    private String goodsCategoryStr;

    /**
     * 商品分类ID(小类：白酒_酱香型ID)
     */
    @ApiModelProperty(value = "商品分类ID(小类)", example = "白酒_酱香型")
    private String goodsSmallCategoryId;

    /**
     * 商品分类ID(中类：白酒ID)
     */
    @ApiModelProperty(value = "商品分类ID(中类：白酒ID)", example = "白酒")
    private String goodsCategoryId;

    /**
     * 商品分类ID(大类：酒类ID)
     */
    @ApiModelProperty(value = "商品分类ID(大类：白酒ID)", example = "酒类")
    private String goodsBigCategoryId;

	/**
	 * 商品分类(小类名)
	 */
    @ApiModelProperty(value = "商品分类(小类名)", example = "白酒_浓香型")
	private String	goodsSmallCategoryName;
	
	/**
	 * 商品中类名
	 */
    @ApiModelProperty(value = "商品分类(中类名)", example = "白酒")
	private String	goodsCategoryName;
	
	/**
	 * 商品分类(大类名)
	 */
    @ApiModelProperty(value = "商品分类(大类名)", example = "酒类")
	private String	goodsBigCategoryName;

    /**
     * 商品分类属性Id
     */
    @ApiModelProperty(value = "商品分类属性Id", example = "酒类")
    private String goodsCategoryAttributeId;

    /**
     * 商品分类属性Name
     */
    @ApiModelProperty(value = "商品分类属性Name", example = "酒类")
    private String goodsCategoryAttributeName;

    /**
     * 是否赠品建档
     */
    @ApiModelProperty(value = "是否赠品建档", example = "false")
    private Boolean gift;


    /**
     * 是否新品
     */
    @ApiModelProperty(value = "是否新品",example = "false")
    private Boolean newArrival;


    /**
     * 其他品牌(为true表示该品牌在SAP未建档)
     */
    @ApiModelProperty(value = "其他品牌(为true表示该品牌在SAP未建档)", example = "false")
    private Boolean unfiledBrand;

    /**
     * 商品品牌ID
     */
    @ApiModelProperty(value = "商品品牌ID", example = "21312312312")
    private String goodsBrandId;

    /**
     * 品牌名称
     */
    @ApiModelProperty(value = "品牌名称", example = "五粮液")
    private String brandName;

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
     * 老数据赠品自字段
     */
    @ApiModelProperty(value = "老数据赠品自字段", example = "500")
    private String mtart;

    /**
     * 酒精度(%)
     */
    @ApiModelProperty(value = "酒精度", example = "52")
    private String alcohol;

    /**
     * 原产地(国家) SAP GEO数据
     */
    @ApiModelProperty(value = "原产地(国家) id", example = "21312312312")
    private String countryId;

    /**
     * 国家名称
     */
    @ApiModelProperty(value = "国家名称", example = "中国")
    private String countryName;

    /**
     * 原产地(省) ps:存储的为SAP GEO数据ID,当商品为进口时使用此字段
     */
    @ApiModelProperty(value = "原产地(省)sap Id  当商品为进口时使用此字段", example = "21312312312",hidden = true)
    private String sapProvinceId;

    /**
     * 原产地(省) ps:存储的为中台GEO数据ID,当商品为国产时使用此字段
     */
    @ApiModelProperty(value = "原产地(省)中台 Id   当商品为国产时使用此字段", example = "21312312312",hidden = true)
    private String provinceId;

    /**
     * 省份/地区名称
     */
    @ApiModelProperty(value = "省份/地区名称", example = "四川",hidden = true)
    private String provinceName;

    /**
     * 原产地(市) ps：中台GEO数据ID,商品为国产时使用
     */
    @ApiModelProperty(value = "原产地(市) 中台 Id  商品为国产时使用", example = "21312312312",hidden = true)
    private String cityId;

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
     * 建议零售价(单位：元)
     */
    @ApiModelProperty(value = "建议零售价(单位：元)", example = "299800")
    private BigDecimal retailPrice;

    /**
     * 市调计价百分比
     */
    @ApiModelProperty(value = "市调计价百分比", example = "1")
    private String marketValuationPercentage;

    /**
     * 最低销售限价(单位：元)
     */
    @ApiModelProperty(value = "最低销售限价(单位：元)", example = "2500.00")
    private BigDecimal minPrice;

    /**
     * 最高销售限价
     */
    @ApiModelProperty(value = "最高销售限价(单位：元)", example = "999999.99")
    private BigDecimal maxPrice;

    /**
     * 每箱数量
     */
    @ApiModelProperty(value = "每箱数量", example = "12")
    private Integer numberPerBox;

    /**
     * 单支毛重(g)
     */
    @ApiModelProperty(value = "单支毛重(g)", example = "600")
    private String grossWeight;

    /**
     * 单支净重(g)
     */
    @ApiModelProperty(value = "单支净重(g)", example = "500")
    private String netWeight;

    /**
     * 单支体积(ccm)
     */
    @ApiModelProperty(value = "单支体积(ccm)", example = "500")
    private String volume;

    /**
     * 1919商品编码   入驻成功后才有
     */
    @ApiModelProperty(value = "1919商品编码   入驻成功后才有", example = "21312312312")
    private String goodsCode;

    /**
     * 商品主属性ID
     */
    @ApiModelProperty(value = "商品主属性ID", example = "21312312312")
    private String primeAttributeId;

    /**
     * 商品主属性
     */
    @ApiModelProperty(value = "商品主属性", example = "流通")
    private String primeAttributeName;

    /**
     * 商品信息匹配是否正确
     */
    @ApiModelProperty(value = "商品信息匹配是否正确", example = "false")
    private Boolean systemMatch;

    /**
     * 是否为1919已建档商品
     */
    @ApiModelProperty(value = "是否为1919已建档商品", example = "false",hidden = true)
    private Boolean existGoods;

    /**
     * 商品子属性ID
     */
    @ApiModelProperty(value = "商品子属性ID", example = "21312312312")
    private String subAttributeId;

	@ApiModelProperty(value = "商品子属性", example = "定制专用")
	private String					subAttributeName;
    /**
     * 零售价定价模式
     */
    @ApiModelProperty(value = "零售价定价模式", example = "21312312312")
    private String retailPricingMode;

    /**
     *  零售价定价模式Id
     */
    @ApiModelProperty(value = "零售价定价模式Id", example = "21312312312")
    private String retailPricingModeId;

    /**
     * 会员定价模式
     */
    @ApiModelProperty(value = "会员定价模式", example = "21312312312")
    private String memberPricingMode;

    /**
     * 会员定价模式
     */
    @ApiModelProperty(value = "会员定价模式", example = "21312312312")
    private String memberPricingModeId;

    /**
     * 商品毛利分数(%)
     */
    @ApiModelProperty(value = "商品毛利分数(%)", example = "20")
    private Integer grossPricePercent;

    /**
     * 是否按箱补货
     */
    @ApiModelProperty(value = "是否按箱补货", example = "false")
    private Boolean replenishmentByBox;

    /**
     * 是否二维码管控
     */
    @ApiModelProperty(value = "是否二维码管控", example = "false")
    private Boolean qrCodeControl;

    /**
     * 是否申请赠品资质豁免
     */
    @ApiModelProperty(value = "是否申请赠品资质豁免", example = "false")
    private Boolean qualificationExemption;

    /**
     * 版本
     */
    @ApiModelProperty(value = "版本", example = "16",hidden = true)
    private Long version;

    /**
     * 包装单位id
     */
    @ApiModelProperty(value = "包装单位ID", example = "21312312312")
    private String unitId;

    /**
     * 包装单位名
     */
    @ApiModelProperty(value = "包装单位名称", example = "箱")
    private String unitName;

    /**
     * 入驻成功
     */
    @ApiModelProperty(value = "入驻成功", example = "false",hidden = true)
    private Boolean registered;


    /**
     * 最小补货数
     */
    @ApiModelProperty(value = "最小补货数即箱规", example = "10")
    private String bstrf;
    
}
