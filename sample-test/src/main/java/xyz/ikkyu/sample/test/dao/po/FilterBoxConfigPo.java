package xyz.ikkyu.sample.test.dao.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author xinming
 * @Date 2020/5/30 11:09
 */
@Data
@Entity
@Table(name="filter_box_config")
public class FilterBoxConfigPo {

    @Id
    private Long id;

    private Timestamp createTime = Timestamp.valueOf(LocalDateTime.now());

    private Timestamp updateTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 下拉框唯一编码
     */
    private String code;

    /**
     * 绑定字段
     */
    private String boundField;

    /**
     * 下拉框数据
     */
    private String data;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 是否直接展示
     */
    private Boolean directDisplay;

    /**
     * 数据源
     * todo 名称待定
     */
    private String dataSourceTarget;

    /**
     * 数据源类型
     * string:  直接展示data中的数据
     * method: 调用方法获取
     */
    private String dataSourceType;

    /**
     * 筛选框类型
     * 字典表中的字符串
     * todo  字典表中的code未定义
     */
    private String filterBoxType;

    /**
     * 筛选框数据类型
     * 查询字典表
     * todo  字典表中的code未定义
     */
    private String filterBoxParameterType;

    /**
     * 数据库查询语句
     * 由选择的绑定字段和选择的查询语句拼接
     * 例： name like (?)
     * todo  字典表中的code未定义
     */
    private String executeStatement;

    /**
     * 筛选框对应页面
     */
    private String pageCode;
}
