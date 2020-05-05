package cn.ikkyu.ftp.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xinming
 */

public class aaa {

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


    public Long getDate() {
        return startDate;
    }

    public void setDate(Long startDate) {
        this.startDate = startDate;
    }
}
