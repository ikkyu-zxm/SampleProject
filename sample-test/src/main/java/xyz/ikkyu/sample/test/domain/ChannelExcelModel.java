package xyz.ikkyu.sample.test.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xinming
 */
@Data
public class ChannelExcelModel {

    @ExcelProperty(value = "order_source", index = 0)
    private String shopBn;

    @ExcelProperty(value = "修正后名称", index = 1)
    private String channelName;

}
