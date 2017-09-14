package com.myimooc.one;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 通过 POI 创建 Excel 文件
 * @author ZhangCheng on 2017-07-06
 *
 */
public class PoiCreateExcel {
	
	public static void main(String[] args) {
		try {
			create();
			System.out.println("创建成功");
		} catch (Exception e) {
			System.out.println("创建失败，异常为：" + e);
		}
	}
	
	/**
	 * 功能：创建 Excel 文件
	 * @throws Exception
	 */
	public static void create()throws Exception{
		
		// 定义Excel文件路径
		File file = new File("d:/poi_test.xls");
		// 创建文件
		file.createNewFile();
		
		// 定义 数组存表头
		String[] title = {"id","name","sex"};
		// 创建Excel工作簿
		HSSFWorkbook workBook = new HSSFWorkbook();
		// 创建工作表sheet
		HSSFSheet sheet = workBook.createSheet();
		// 创建第一行
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = null;
		// 将表头写入第一行
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		// 追加数据
		for (int i = 1; i < 10; i++) {
			HSSFRow nextRow = sheet.createRow(i);
			HSSFCell cell2 = nextRow.createCell(0);
			cell2.setCellValue("a"+i);
			cell2 = nextRow.createCell(1);
			cell2.setCellValue("user"+i);
			cell2 = nextRow.createCell(2);
			cell2.setCellValue("男");
		}
		// 将Excel内容写入文件
		FileOutputStream stream = FileUtils.openOutputStream(file);
		workBook.write(stream);
		// 释放资源
		stream.close();
		workBook.close();
	}
}
