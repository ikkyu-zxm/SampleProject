package cn.ikkyu.sample.test.webservice;

import cn.ikkyu.sample.test.service.impl.EasyExcelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xinming
 * @Date 2020/3/4 21:49
 */
@Slf4j
public class EasyExcelTest {



    @Test
    public void testImportAndExport() {
        log.info("开始测试easy excel ------");
        long startTime = System.currentTimeMillis();
        new EasyExcelServiceImpl().testEasyExcelImportExport("F:\\tem\\bigData.xlsx", "F:\\tem");
        log.info("测试结束  总共用时 {}", System.currentTimeMillis() - startTime);
    }



    @Test
    public void testImportAndExportByModel() {
        log.info("开始测试easy excel ------");
        long startTime = System.currentTimeMillis();
        try {
            new EasyExcelServiceImpl().testEasyExcelImportExportByModel("F:\\tmp\\WineAdviser\\test.xlsx", "F:\\tmp\\aaa");
        } catch (IOException e) {
            log.error("{}",e);
        }
        log.info("测试结束  总共用时 {}", System.currentTimeMillis() - startTime);
    }

    @Test
    public void test() {
        int i = 5 / 0;
        System.out.println(i);
    }

}
