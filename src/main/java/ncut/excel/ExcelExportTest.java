package ncut.excel;

import ncut.excel.model.User;
import ncut.excel.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author NikoBelic
 * @create 20/11/2016 01:17
 */
public class ExcelExportTest
{
    public static void exportType1() throws IOException
    {
        // 伪造数据
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            userList.add(new User(i, "用户" + i, 10 + i, new Date()));
        }


        // 导出数据
        // 创建excel
        HSSFWorkbook wb = new HSSFWorkbook();

        // 创建sheet
        HSSFSheet sheet = wb.createSheet("用户信息");

        // 创建一行
        HSSFRow rowTitle = sheet.createRow(0);

        // 创建标题栏样式
        HSSFCellStyle styleTitle = wb.createCellStyle();
        styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
        HSSFFont fontTitle = wb.createFont();
        // 宋体加粗
        fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontTitle.setFontName("宋体");
        fontTitle.setFontHeight((short) 200);
        styleTitle.setFont(fontTitle);

        // 在行上创建1列
        HSSFCell cellTitle = rowTitle.createCell(0);

        // 列标题及样式
        cellTitle.setCellValue("姓名");
        cellTitle.setCellStyle(styleTitle);

        // 在行上创建列2
        cellTitle = rowTitle.createCell(1);
        cellTitle.setCellValue("年龄");
        cellTitle.setCellStyle(styleTitle);

        cellTitle = rowTitle.createCell(2);
        cellTitle.setCellValue("出生日期");
        cellTitle.setCellStyle(styleTitle);


        HSSFCellStyle styleCenter = wb.createCellStyle();
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中

        // 取数据

        for (int i = 0; i < userList.size(); i++)
        {

            User item = userList.get(i);
            HSSFRow row = sheet.createRow(i + 1);

            HSSFCell cell = row.createCell(0);
            cell.setCellValue(item.getName());
            cell.setCellStyle(styleCenter);

            cell = row.createCell(1);
            cell.setCellValue(item.getAge());
            cell.setCellStyle(styleCenter);

            cell = row.createCell(2);
            cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getBirthDay()));
            cell.setCellStyle(styleCenter);
        }

        FileOutputStream fout = new FileOutputStream("/Users/lixiwei-mac/Desktop/ex.xls");
        wb.write(fout);
        fout.close();
        wb.close();

        System.out.println("导出完成！");
    }

    public static void exportType2() throws Exception
    {

        // 伪造数据
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            userList.add(new User(i,"用户" + i,10+i,new Date()));
        }



        List<String[]> columNames = new ArrayList<String[]>();
        columNames.add(new String[]{"姓名", "生日"});
        columNames.add(new String[]{"姓名", "生日"});

        List<String[]> fieldNames = new ArrayList<String[]>();
        fieldNames.add(new String[]{"name", "birthDay"});
        fieldNames.add(new String[]{"name", "birthDay"});

        LinkedHashMap<String, List<?>> map = new LinkedHashMap<String, List<?>>();
        map.put("用户信息1", userList);
        map.put("用户信息2", userList);


        ExcelUtil.ExcelExportData setInfo = new ExcelUtil.ExcelExportData();
        setInfo.setDataMap(map);
        setInfo.setFieldNames(fieldNames);
        setInfo.setTitles(new String[]{"用户信息报表1", "用户信息报表2"});
        setInfo.setColumnNames(columNames);

        // 将需要导出的数据输出到文件
        System.out.println(ExcelUtil.export2File(setInfo, "/Users/lixiwei-mac/Desktop/ex.xls"));

    }

    public static void main(String[] args) throws Exception
    {
        //exportType1();
        exportType2();


    }
}
