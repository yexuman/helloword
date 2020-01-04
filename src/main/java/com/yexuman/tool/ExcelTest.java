package com.yexuman.tool;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yexuman
 * @date 2020/1/2 14:43
 */
public class ExcelTest {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void writeExcel(List<List<String>> dataList, int cloumnCount, String finalXlsxPath) {
        FileOutputStream out = null;
        try {
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            // Create Blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            workbook.createSheet("sheet1");
            // Create file system using specific name
             out = new FileOutputStream(finalXlsxFile);

            // write operation workbook using file out object
            workbook.write(out);



            //Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workbook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
//            int rowNumber = sheet.getLastRowNum(); // 第一行从0开始算
//            System.out.println("原始数据总行数，除属性列：" + rowNumber);
//            for (int i = 1; i <= rowNumber; i++) {
//                Row row = sheet.getRow(i);
//                sheet.removeRow(row);
//            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out = new FileOutputStream(finalXlsxPath);
            workbook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                //Row row = sheet.createRow(j + 1);
                Row row = sheet.createRow(j);
                List<String> dataMap = dataList.get(j);
                for (int i = 0; i <= 21; i++) {
                    String str = dataMap.get(i).toString();
                    Cell cell = row.createCell(i);
                    cell.setCellValue(str);

                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out = new FileOutputStream(finalXlsxPath);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    /**
     * 根据fileType不同读取excel文件
     *
     * @param path
     * @param path
     * @throws IOException
     */
    @SuppressWarnings({"resource", "deprecation"})
    public static List<List<List<String>>> readExcel(String path) {
        String fileType = path.substring(path.lastIndexOf(".") + 1);
        // return a list contains many list
        List<List<List<String>>> res = new ArrayList<>();
        // 读取excel文件
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            // 获取工作薄
            Workbook wb = null;
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                return null;
            }

            // 读取第一个工作页sheet
            Sheet sheet = wb.getSheetAt(0);
            String biaoMing = "";
            String tmpBiaoMing = "";
            // 第一行为标题
            int m = 0;
            ArrayList<List<String>> ttt = new ArrayList<>();
            //行
            for (Row row : sheet) {
                if (m == 0) {
                    m++;
                    continue;
                }

                int i = 0;
                ArrayList<String> list = new ArrayList();
                for (Cell cell : row) {  //列
                    if (m != 0) {

                        // 根据不同类型转化成字符串
                        if (i > 22) {
                            continue;
                        }
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        list.add(cell.getStringCellValue());
                        if (i == 22) {
                            tmpBiaoMing = cell.getStringCellValue();
                        }
                        i++;
                    }
                }
                ttt.add(list);
                if (biaoMing != "" && biaoMing != tmpBiaoMing) {
                    res.add(ttt);
                    ttt= new ArrayList<>();
                }
                biaoMing = tmpBiaoMing;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\yexuman\\Documents\\WeChat Files\\a862221933\\FileStorage\\File\\2020-01\\订单导出1577777828244原.xlsx";
        List<List<List<String>>> res = readExcel(path);

        for (int i = 0; i < res.size(); i++) {
            System.out.println("---------------------------" + i);
            List<List<String>> list = res.get(i);

             String p = list.get(0).get(list.get(0).size()-1) + ".xlsx";
            //String p = "hhh" + i + ".xlsx";
            ExcelTest.writeExcel(list, list.size(), "C:\\Users\\yexuman\\Documents\\WeChat Files\\a862221933\\FileStorage\\File\\2020-01\\output2\\" + p);
        }

        System.out.println("-------------");
    }

}
