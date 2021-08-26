package xyz.ikkyu.sample.dao.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xinming
 * @Date 2020/5/31 16:15
 */
@Data
@Entity
@Table(name="tb_terminal_info")
@TableName("tb_terminal_info")
public class TbTerminalInfoPo {

    /**
     * 自增主键
     */
    @Id
    @TableId
    private Long id;
    /**
     * 门店编码(物理门店的)
     */
    @TableField
    private String code;
    /**
     * 门店名称
     */
    @TableField
    private String name;
    /**
     * 门店类型 tb_dic  门店经营类型 ZY ZG SC JM HH 其他
     */
    private String type;

}
