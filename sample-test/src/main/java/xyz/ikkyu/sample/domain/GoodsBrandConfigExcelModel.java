package xyz.ikkyu.sample.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class GoodsBrandConfigExcelModel {

    @ExcelProperty(value = "商品编码", index = 0)
    private String childGoodsCode;

    @ExcelProperty(value = "商品名称", index = 1)
    private String childGoodsName;

    @ExcelProperty(value = "修正后商品编码", index = 2)
    private String masterGoodsCode;

    @ExcelProperty(value = "修正后商品名称", index = 3)
    private String masterGoodsName;

    @ExcelProperty(value = "品牌id", index = 4)
    private String brandCode;

    @ExcelProperty(value = "品牌名称", index = 5)
    private String brandName;

    @ExcelProperty(value = "系列编码", index = 6)
    private String seriesCode;

    @ExcelProperty(value = "系列名称", index = 7)
    private String seriesName;

}