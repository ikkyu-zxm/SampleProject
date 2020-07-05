package xyz.ikkyu.sample.test.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.excel.write.metadata.WriteWorkbook;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.ikkyu.sample.test.domain.ChannelExcelModel;
import xyz.ikkyu.sample.test.domain.GoodsBrandConfigExcelModel;
import xyz.ikkyu.sample.test.excel.GoodsBrandConfigExcelListener;
import xyz.ikkyu.sample.test.service.EasyExcelService;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xinming
 * @Date 2020/3/4 21:05
 */
@Slf4j
@Service
public class EasyExcelServiceImpl implements EasyExcelService {


    private static final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");


    @Override
    public void testEasyExcelImportExport(String dataUrl, String outUrl) {
        Sheet sheet = new Sheet(1, 1);
        try (OutputStream out = new FileOutputStream(outUrl + "\\withHead.xlsx")) {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(dataUrl));

            ZipSecureFile.setMinInflateRatio(-1.0d);

            List<Object> readList = EasyExcelFactory.read(inputStream, sheet);

            List<List<String>> outList = readList.stream()
                    .map(obj -> {
                        List inList = (List) obj;
                        List<String> strings = new ArrayList<>(inList.size());
                        for (Object o : inList) {
                            strings.add((String) o);
                        }
                        return strings;
                    })
                    .collect(Collectors.toList());
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("sheet1");
//            List<List<String>> data = new ArrayList<>();
//            for (int i = 0; i < 100; i++) {
//                List<String> item = new ArrayList<>();
//                item.add("item0" + i);
//                item.add("item1" + i);
//                item.add("item2" + i);
//                data.add(item);
//            }

//            Table table = new Table(1);
            writer.write0(outList, sheet1);
            //推荐使用方法
//            EasyExcel.write(dataUrl + "\\withHead.xlsx",)
            writer.finish();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void moreMultiMeterExportTest(String outUrl) {

        GoodsBrandConfigExcelModel brandConfigExcelModel = new GoodsBrandConfigExcelModel();

        brandConfigExcelModel.setChildGoodsCode("商品编码");
        brandConfigExcelModel.setChildGoodsName("商品名称");

        ChannelExcelModel channelExcelModel = new ChannelExcelModel();
        channelExcelModel.setChannelName("渠道名称");
        channelExcelModel.setShopBn("渠道编码");

        WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetNo(1);

//        ExcelWriterSheetBuilder sheet = EasyExcel.write("outUrl").sheet("sheet1");
//        sheet.doWrite(Lists.newArrayList(brandConfigExcelModel));
//        sheet.doWrite(Lists.newArrayList(channelExcelModel));


        WriteWorkbook writeWorkbook = new WriteWorkbook();
        writeWorkbook.setFile(new File(outUrl));
        ExcelWriter excelWriter = new ExcelWriter(writeWorkbook);

        writeSheet.setSheetName("sheet1");
//        writeSheet.setClazz(GoodsBrandConfigExcelModel.class);

        WriteTable writeTable1 = new WriteTable();
        writeTable1.setTableNo(1);
        writeTable1.setClazz(GoodsBrandConfigExcelModel.class);

        excelWriter.write(Lists.newArrayList(brandConfigExcelModel), writeSheet, writeTable1);

        WriteTable writeTable2 = new WriteTable();
        writeTable2.setTableNo(2);
        writeTable2.setClazz(ChannelExcelModel.class);
//        WriteSheet writeSheet2 = new WriteSheet();
//        writeSheet2.setSheetNo(1);

        excelWriter.write(Lists.newArrayList(channelExcelModel), writeSheet, writeTable2);
        excelWriter.finish();


//        EasyExcel.write(outUrl, GoodsBrandConfigExcelModel.class).sheet("sheet1").doWrite(Lists.newArrayList(brandConfigExcelModel));

    }


    @Override
    public void testEasyExcelImportExportByModel(String dataUrl, String outUrl) throws IOException {
        Sheet sheet = new Sheet(1, 1);
//        File file = new File(outUrl + "\\withHead.xlsx");
//        if (!file.exists()) {
//            if (!file.getParentFile().exists()) {
//               file.mkdirs();
//            }
//        }


        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(dataUrl));

            ZipSecureFile.setMinInflateRatio(-1.0d);


            //实例化实现了AnalysisEventListener接口的类
            GoodsBrandConfigExcelListener listener = new GoodsBrandConfigExcelListener();

            EasyExcel.read(inputStream,GoodsBrandConfigExcelModel.class, listener).sheet().doRead();
            List<GoodsBrandConfigExcelModel> dataList = listener.getDataList();
            System.out.println(JSON.toJSONString(dataList));


//            List<Object> readList = EasyExcelFactory.read(inputStream, sheet);
//
//            List<GoodsBrandConfigExcelModel> outList = readList.stream()
//                    .map(obj -> {
//                        List<String> list = (List<String>) obj;
//                        GoodsBrandConfigExcelModel model = new GoodsBrandConfigExcelModel();
//                        model.setChildGoodsCode(list.get(0));
//                        model.setChildGoodsName(list.get(1));
//                        return model;
//                    })
//                    .collect(Collectors.toList());

//            persistenceExcel(outList, GoodsBrandConfigExcelModel.class, outUrl + "\\withHead.xlsx");
//
//            ExcelHeadProperty excelHeadProperty = new ExcelHeadProperty(GoodsBrandConfigExcelModel.class, Lists.newArrayList());
//            ExcelWriter writer = EasyExcelFactory.getWriter(out);
//
//
//            Sheet sheet1 = new Sheet(1, 0);
//            sheet1.setSheetName("sheet1");
//            sheet1.setHead(excelHeadProperty.getHead());
//            sheet1.setClazz(GoodsBrandConfigExcelModel.class);
//            writer.write(outList, sheet1);
//            writer.finish();

        } catch (Exception e) {
            log.error("{}", e);
        }


    }





    public <T extends BaseRowModel> String persistenceExcel(List<?> data, Class<T> clazz, String path) {

//        String path = LocalDateTime.now().format(DATETIMEFORMATTER) + FILE_SUFFIX;

        File file = new File(path);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.mkdirs();
            }
        } else {
            file.delete();
        }
        try (OutputStream out = new FileOutputStream(file)) {
            List<T> modelList = new ArrayList<>(data.size());
            for (Object obj : data) {
                T model = clazz.newInstance();
                BeanUtils.copyProperties(obj, model);
                modelList.add(model);
            }
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            Sheet sheet1 = new Sheet(1, 0, clazz);
            sheet1.setSheetName("sheet1");
            writer.write(modelList, sheet1);
            writer.finish();
        } catch (Exception e) {
            log.error("导出excel失败");
            log.error("{}", e);
            return "";
        }
        return path;
    }
}
