package cn.ikkyu.sample.test.service.impl;

import cn.ikkyu.sample.test.service.EasyExcelService;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xinming
 * @Date 2020/3/4 21:05
 */
@Service
public class EasyExcelServiceImpl implements EasyExcelService {


    @Override
    public void testEasyExcelImportExport(String dataUrl,String outUrl) {
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
            writer.finish();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
