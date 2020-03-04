package cn.ikkyu.ftp.poi;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xinming
 * @Date 2020/3/4 22:12
 */
@Slf4j
public class PoiImportExportTest {



    @Test
    public void testPoiImportAndExport() {
        log.info("开始测试 poi ------");
        long startTime = System.currentTimeMillis();
        try (OutputStream out = new FileOutputStream("F:\\tem\\withHead2.xlsx")) {
            InputStream inputStream = new BufferedInputStream(new FileInputStream("F:\\tem\\bigData.xlsx"));
            ZipSecureFile.setMinInflateRatio(-1.0d);
//            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);

            XSSFSheet sheet = wb.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();

            ArrayList<List<String>> data = new ArrayList<>(lastRowNum);
            for (int i = 0; i < lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                int colNum = row.getPhysicalNumberOfCells();
                ArrayList<String> strings = new ArrayList<>(colNum);
                for (int x = 0; x < colNum; x++) {
                    XSSFCell cell = row.getCell(x);
                    strings.add(cell.getStringCellValue());
                }
                data.add(strings);
            }

            //创建一个excel文件
            XSSFWorkbook wbOut = new XSSFWorkbook();
            XSSFSheet sheetOut = wbOut.createSheet("aaaaa");

            List<String> tem = data.get(0);
            int size = tem.size();
            for (int i = 0; i < lastRowNum; i++) {
                List<String> strings = data.get(i);
                XSSFRow outRow = sheetOut.createRow(i);
                for (int x = 0; x < size; x++) {
                    XSSFCell cell = outRow.createCell(x);
                    cell.setCellValue(strings.get(x));
                }
            }
            wbOut.write(out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("测试结束  总共用时 {}", System.currentTimeMillis() - startTime);

    }
}
