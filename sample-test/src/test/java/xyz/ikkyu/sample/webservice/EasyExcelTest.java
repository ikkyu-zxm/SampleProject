package xyz.ikkyu.sample.webservice;

import xyz.ikkyu.sample.domain.ChannelExcelModel;
import xyz.ikkyu.sample.excel.BaseExcelListener;
import xyz.ikkyu.sample.service.impl.EasyExcelServiceImpl;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.junit.Test;

import java.io.*;
import java.util.List;

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


    @Test
    public void testBaseExcelListener() {
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream("F:\\tmp\\WineAdviser\\渠道汇总数据4.9.xlsx"));

            ZipSecureFile.setMinInflateRatio(-1.0d);

            //实例化实现了AnalysisEventListener接口的类
            BaseExcelListener listener = new BaseExcelListener<ChannelExcelModel>();

            EasyExcel.read(inputStream,ChannelExcelModel.class, listener).sheet(0).doRead();
            List<ChannelExcelModel> dataList = listener.getDataList();
            System.out.println(JSON.toJSONString(dataList));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
