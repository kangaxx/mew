package com.cn.sh.lilac.utils;

/**
 * @author gxx
 * excel文件处理基本功能类
 */


import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * excel工具类
 */
public class ExcelUtils {

    //private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 课程excel
     * @param file
     * @return
     * @throws Exception
     */
    public static List getCourseListByExcel(MultipartFile file) throws Exception {

        List list = new ArrayList<>();

        // 创建excel工作簿
        InputStream in = file.getInputStream();
        String fileName = file.getOriginalFilename();
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }

        Sheet sheet = null;
        Row row = null;
        Cell cell = null;


        for (int i = 0; i < work.getNumberOfSheets(); i++) {

            sheet = work.getSheetAt(i);
            if(sheet == null) {
                continue;
            }

            // 滤过第一行标题

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                //if (row == null || row.getFirstCellNum() == j) {
                //改为前六行数据都不需要
                if (row == null || j < 7) {
                    continue;
                }

                List<Object> li = new ArrayList<>();

                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    if (cell == null) { continue; }
                    cell = row.getCell(y);
                    cell.setCellType(CellType.STRING);
                    cell.getStringCellValue();
                    // 日期类型转换
                    /*
                    if(y == 3) {
                        //cell.setCellType(CellType.STRING);
                        double s1 = cell.getNumericCellValue();
                        Date date = HSSFDateUtil.getJavaDate(s1);
                        li.add(date);
                        continue;
                    }
                     */

                    li.add(cell);
                }
                list.add(li);
            }
        }
        work.close();
        in.close();
        return list;
    }

    /**
     * 判断文件格式
     * @param in
     * @param fileName
     * @return
     */
    private static Workbook getWorkbook(InputStream in, String fileName) throws Exception {

        Workbook book = null;
        String filetype = fileName.substring(fileName.lastIndexOf("."));

        if(".xls".equals(filetype)) {
            book = new HSSFWorkbook(in);
        } else {
            throw new Exception("请上传excel文件！");
        }

        return book;
    }

    /**
     * 判断文件格式
     * @param file
     * @param sheetIndex sheet编号
     * @return
     */
    public static int getMaxRowNum(MultipartFile file, int sheetIndex) throws Exception {
        String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = work.getSheetAt(sheetIndex);
        if (null == sheet) {
            throw new Exception(String.format("sheet[%] 的内容为空", sheetIndex));
        }
        in.close();
        return sheet.getLastRowNum();
    }

    /**
     * 获取excel特定单元格的数据
     * @param file
     * @param sheetIndex
     * @param rowNum
     * @param cellNum
     * @return
     */
    public static String getStringValue(MultipartFile file, int sheetIndex, int rowNum, int cellNum) throws Exception {
        String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建excel工作簿为空！");
        }
        Sheet sheet = work.getSheetAt(sheetIndex);
        if (null == sheet) {
            throw new Exception(String.format("sheet[%] 的内容为空", sheetIndex));
        }
        return sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
    }


    public static Double getNumValue(MultipartFile file, int sheetIndex, int rowNum, int cellNum) throws Exception {
        String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建excel工作簿为空！");
        }
        Sheet sheet = work.getSheetAt(sheetIndex);
        if (null == sheet) {
            throw new Exception(String.format("sheet[%] 的内容为空", sheetIndex));
        }
        return sheet.getRow(rowNum).getCell(cellNum).getNumericCellValue();
    }

    public static BigDecimal getBigDecimalValue(MultipartFile file, int sheetIndex, int rowNum, int cellNum) throws Exception {
        String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建excel工作簿为空！");
        }
        Sheet sheet = work.getSheetAt(sheetIndex);
        if (null == sheet) {
            throw new Exception(String.format("sheet[%] 的内容为空", sheetIndex));
        }
        return new BigDecimal(sheet.getRow(rowNum).getCell(cellNum).getNumericCellValue());
    }
}
