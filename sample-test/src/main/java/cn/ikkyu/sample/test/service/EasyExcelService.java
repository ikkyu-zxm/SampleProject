package cn.ikkyu.sample.test.service;

import java.io.IOException;

/**
 * @author xinming
 * @Date 2020/3/4 21:05
 */
public interface EasyExcelService {

    /**
     * 测试easyExcel导入导出
     * @param dataUrl
     */
    void testEasyExcelImportExport(String dataUrl,String outUrl);


    void testEasyExcelImportExportByModel(String dataUrl,String outUrl) throws IOException;

}
