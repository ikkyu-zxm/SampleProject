package xyz.ikkyu.sample.dao.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author xinming
 * @Date 2020/5/31 16:15
 */
@Data
@Entity
@Table(name="tb_terminal_info")
public class TbTerminalInfoPo {

    /**
     * 自增主键
     */
    @Id
    private Long id;
    /**
     * 门店编码(物理门店的)
     */
    private String code;
    /**
     * 门店名称
     */
    private String name;
    /**
     * 门店类型 tb_dic  门店经营类型 ZY ZG SC JM HH 其他
     */
    private String type;
    /**
     * 门店类型 的文本值
     */
    private String typeTxt;
    /**
     * 门店状态 1开 2关
     */
    private String isOpen;
    /**
     * 门店状态 的文本值
     */
    private String isOpenTxt;

    /**
     * 门店级别 tb_dic
     */
    private String storeLevel;

    /**
     * 门店级别 tb_dic
     */
    private String storeLevelTxt;

    /**
     * 烟草门店编码
     */
    private String smokeCode;
    /**
     * 建筑面积
     */
    private String buildArea;
    /**
     * 仓库面积
     */
    private String warehouseArea;
    /**
     * 租赁面积
     */
    private String leaseArea;
    /**
     * 经营面积
     */
    private String operatArea;
    /**
     * 开间宽度
     */
    private String openingWidth;
    /**
     * 陈列SKU坑位数
     */
    private String storeSku;
    /**
     * 仓储SKU坑位数
     */
    private String warehouseSku;
    /**
     * 店长姓名
     */
    private String storeManager;
    /**
     * 店长电话
     */
    private String managerMobile;
    /**
     * 公司代码
     */
    private String companyCode;
    /**
     * 装修开始时间
     */
    private Date decoratStartTimes;
    /**
     * 装修结束时间
     */
    private Date decoratEndTimes;
    /**
     * 开业时间/开店日期
     */
    private Date openDate;
    /**
     * 关店日期
     */
    private Date closeDate;
    /**
     * 省名称
     */
    private String provence;
    /**
     * 市名称
     */
    private String city;
    /**
     * 区名称
     */
    private String district;
    /**
     * 实用面积
     */
    private String acreage;
    /**
     * 门店人数
     */
    private String people;
    /**
     * 门店品牌name
     */
    private String property;
    /**
     * 门店品牌 str
     */
    private String propertyStr;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 经营省公司
     */
    private String operatCompany;
    /**
     * 所属商圈(tb_dic)
     */
    private String businessDistrict;
    /**
     * 城市规模
     */
    private String citySize;
    /**
     * 门店价格区域(门店销售规模 tb_dic)
     */
    private String storePriceArea;
    /**
     * 是否烟门店: 0 是 1 否
     */
    private String whetherSmokeStore;
    /**
     * 是否电子开票: 0 是 1 否
     */
    private String whetherBilling;
    /**
     * 是否可发物流: 0 是 1 否
     */
    private String whetherDeliverableLogistics;
    /**
     * 是否接受分单: 0 是 1 否
     */
    private String whetherAcceptOrder;
    /**
     * 备用门店编码
     */
    private String alternateStoreCode;
    /**
     * 备用门店名称
     */
    private String alternateStoreName;
    /**
     * 所属门店体系: 0 1919门店 1 隔壁仓库店
     */
    private String ownStoreSystem;
    /**
     * 所属管理系统: 0 POS系统 1 隔壁仓库系统
     */
    private String managementSystem;
    /**
     * 所属经营大区code
     */
    private String regionCode;
    /**
     * 拓展字段
     */
    private String extentions;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 标签信息
     */
    private String markInfo;
    /**
     * 门店描述
     */
    private String description;
    /**
     * 称谓
     */
    private String title;
    /**
     * 邮政编码/城市
     */
    private String postCode;
    /**
     * 国家
     */
    private String country;
    /**
     * 时区
     */
    private String timeZone;
    /**
     * 运输区域
     */
    private String transportationArea;
    /**
     * 搜索项 1
     */
    private String sort1;
    /**
     * 搜索项 2
     */
    private String sort2;
    /**
     * SAP城市代码
     */
    private String sapCity;
    /**
     * 客户代码
     */
    private String consumerCode;
    /**
     * 采购组织
     */
    private String purchasingOrganization;
    /**
     * 销售组织
     */
    private String salesOrganization;
    /**
     * 分销渠道
     */
    private String distributionChannel;
    /**
     * 部门
     */
    private String department;
    /**
     * 销售办事处
     */
    private String salesOffice;

    private String salesOfficeName;
    /**
     * 日历
     */
    private String calendar;
    /**
     * 房东姓名
     */
    private String landlordName;
    /**
     * 房东电话
     */
    private String landlordMobile;
    /**
     * 门店补货额度
     */
    private String replenishmentQuota;
    /**
     * 日结日期
     */
    private String dateOfDate;
    /**
     * 开店时间 00:00
     */
    private String openTime;
    /**
     * 关店时间 00:00
     */
    private String closeTime;
    /**
     * 高背柜数量
     */
    private String highBackCabinetNum;
    /**
     * 灯箱数量
     */
    private String lightBoxNum;
    /**
     * 显示屏数量
     */
    private String displayNum;
    /**
     * 临窗柜数量
     */
    private String windowCabinetNum;
    /**
     * 会所
     */
    private String clubNum;
    /**
     * 地理区域
     */
    private String geographicalArea;
    /**
     * 货架数量
     */
    private String goodsShelvesNum;
    /**
     * 橱柜数量
     */
    private String cupboardNum;
    /**
     * 统收公司号
     */
    private String collectingCompanyCode;
    /**
     * 中岛柜数量
     */
    private String centralIslandCabinetNum;
    /**
     * 中岛柜端架数量
     */
    private String centralIslandCabinetEndFrameNum;

    private String terminalStatus;

    /**
     * 促销堆头
     */
    private String promotionalStack;

    /**
     * 销售规模
     */
    private String storeSalesScale;
    /**
     * 红酒促销台
     */
    private String redWinePromotionDesk;
    /**
     * 楼层
     */
    private String floor;
    /**
     * 白酒促销台
     */
    private String liquorPromotionDesk;
    /**
     * 车位数量
     */
    private String parkingLotNum;
    /**
     * 通用促销台
     */
    private String generalSalesPromotionDesk;
    /**
     * 冰柜
     */
    private String freezerNum;
    /**
     * 是否独立核算
     */
    private String isIndependentAccounting;
    /**
     * 是否可开增票
     */
    private String isIncreaseTicket;
    /**
     * 是否为中心店
     */
    private String isCenterStore;
    /**
     * 经营区域
     */
    private String area;

    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 门店对应品牌code
     */
    private String storeCode;

    /**
     * 省市区对应名称 方便前端回显
     */
    private String provenceTxt;

    /**
     * 省市区对应名称 方便前端回显
     */
    private String cityTxt;

    /**
     * 省市区对应名称 方便前端回显
     */
    private String districtTxt;

    // /**
    // * 城市邮政编码
    // */

    private String markId;

    // 是否开通蜂鸟 1开启 2关闭
    private Integer hummingBird;
    // 蜂鸟认证状态（0:未认证, 1:审核中, 2:审核通过, 3:认证失败, 4:判定失效）
    private String hummingBirdStatus;


    //TODO 新增属性
    /**
     * 城市类型
     */
    private String cityTypeId;

    /**
     * 商圈类型
     */
    private String districtTypeId;

    /**
     * 经营面积分类
     */
    private String manageAreaTypeId;


    /**
     * 预估坑位下限
     */
    private Integer pitLowerLimit;

    /**
     * 预估坑位上限
     */
    private Integer pitUpperLimit;

    private String serviceDistrictCode;


    private String userId;

    private String fieldType;
}
