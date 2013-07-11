package com.renjie120.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import brightmoon.excel.ExcelCol;
import brightmoon.excel.ExcelRow;
import brightmoon.excel.ExcelSheet;
import brightmoon.excel.IExcelReader;
import brightmoon.excel.IExcelWriter;
import brightmoon.excel.NPOIWriter;
import brightmoon.excel.POIReader;
import brightmoon.excel.POIWriter;
import brightmoon.util.ChineseChanger;
import brightmoon.util.Util;

public class POITest {
	//@Test
	public void readTest() throws Exception {
		String fileName = "D:\\tete22.xlsx";
		File f = new File(fileName);
		IExcelReader read = new POIReader(f);
		String[][] results = read.readAllExcel(1, false);
		Util.printStrArr(results);
		read.setCurrentSheet(0);

		System.out.println(read.getCellAsStringByIndex("A1"));
		System.out.println(read.getCellAsStringByIndex(0, 0, 0));  
		System.out.println(read.getCellAsStringByIndex(0, 0));
 
		//ExcelTool.printStrArr(results); 
	}
	
	//@Test
	public void chineseChangeTest() throws Exception {
		System.out.println(ChineseChanger.converterToSpell("汇明网络"));
		System.out.println(ChineseChanger.converterToFirstSpell("李水清"));  
	}
	
	
	/**
	 * 测试excep的写工具包的功能.
	 * @throws Exception
	 */
	//@Test
	public void writePoiTest() throws Exception {  
		String fileName = "d:\\newasd.xls";
		IExcelWriter write = new POIWriter(); 
		write.createEmptyExcel(fileName);
		write.addSheetFromBeanList("newSheetName22", initList(),
				fileName,true); 
		
		IExcelWriter write2 = new POIWriter();
		write2.addSheetFromExcelSheet("newSheetName44", initSheetContent(),
				fileName,true);

		IExcelWriter write3 = new POIWriter(); 
		write3.updateCellValue(fileName, 0, 0, 0, "李水清李水清");
	} 
	
	@Test
	public void writeNPoiTest() throws Exception {  
		String fileName = "d:\\newasd.xlsx";
		IExcelWriter write = new NPOIWriter(); 
		write.createEmptyExcel(fileName);
		write.addSheetFromBeanList("aaa", initList(),
				fileName,true); 
		
		IExcelWriter write2 = new NPOIWriter();
		write2.addSheetFromExcelSheet("rrr", initSheetContent(),
				fileName,true);

		IExcelWriter write3 = new NPOIWriter(); 
		write3.updateCellValue(fileName, 0, 0, 0, "徐");
	} 
 
	public static  List initList() {
		List ans = new ArrayList();
		for (int i = 0; i < 10; i++) {
			AJavaBean bean = new AJavaBean();
			bean.setCount("sss" + i);
			bean.setId("ccc" + i);
			bean.setName("ddd" + i);
			ans.add(bean);
		}
		return ans;
	}
	
	public  static ExcelSheet initSheetContent() {
		ExcelSheet result= new ExcelSheet();
		ExcelRow title = new ExcelRow();
		for (int i = 0; i < 10; i++) {
			ExcelCol header = new ExcelCol();
			header.setValue("ddd"+i);
			title.addCol(header);
		}
		result.setTitle(title);

		for (int ii = 0; ii < 10; ii++) {
			ExcelRow title2 = new ExcelRow();
			for (int i = 0; i < 10; i++) {
				ExcelCol header = new ExcelCol();
				header.setValue("sss"+i);
				title2.addCol(header);
			}
			result.addRow(title2);
		}
		return result;
	}
}