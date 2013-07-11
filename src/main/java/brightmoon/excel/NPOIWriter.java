package brightmoon.excel;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import brightmoon.util.Util;

/**
 * 对Excel2003进行写的类.
 * 
 */
public class NPOIWriter extends ExcelTool implements IExcelWriter {
	/**
	 * 工作簿
	 */
	private Workbook workbook;

	/**
	 * 日志记录
	 */
	private Log log = LogFactory.getLog("logger");

	/**
	 * excel表
	 */
	private Sheet sheet;

	/**
	 * excel文件流
	 */
	private FileInputStream fis;

	/**
	 * 消息
	 */
	private StringBuffer msg = null;

	public NPOIWriter() {

	}

	/**
	 * 根据一个javabean对象，返回这个对象的属性值集合，使用到反射机制。
	 * 
	 * @return
	 */
	private String[] getPropertyOfBean(Object obj) {
		String[] result = null;
		try {
			PropertyDescriptor[] props = Introspector.getBeanInfo(
					obj.getClass(), Object.class).getPropertyDescriptors();
			result = new String[props.length];
			for (int temp = 0; temp < props.length; temp++) {
				try {
					result[temp] = props[temp].getReadMethod().invoke(obj)
							.toString();
				} catch (Exception e) {
					e.printStackTrace();
					log.error("出现异常", e);
					return null;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			log.error("出现异常", e1);
			return null;
		}
		return result;
	}

	private Workbook getWorkbook(String excelFile) throws Exception {
		FileInputStream fis = new FileInputStream(excelFile);
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(fis);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return workbook;
	}

	private void checkFile(String targetFile, String sheetName,
			boolean createEmpty) throws Exception {
		File f = new File(targetFile);
		if (!f.exists() && createEmpty) {
			createEmptyExcel(targetFile, sheetName);
		} else if (!f.exists()) {
			throw new RuntimeException("文件不存在请确认");
		}
	}

	private void checkFile(String targetFile) throws Exception {
		File f = new File(targetFile);
		if (!f.exists()) {
			throw new RuntimeException("文件不存在请确认");
		}
	}

	private void createExcelFromList(Workbook workbook, List data,
			String sheetName) throws Exception {
		Sheet sheet = null;
		Row title = null;
		Sheet _shteet = workbook.getSheet(sheetName);
		if (data.size() < 1) {
			throw new RuntimeException("数据不得为空");
		}

		if (_shteet != null) {
			sheet = _shteet;
		} else {
			sheet = workbook.createSheet(sheetName);
		}

		// 取list中的第一个数据，进行属性名称的读取，准备放到excel表格中的第一行
		Object aData = data.get(0);
		PropertyDescriptor[] props = Introspector.getBeanInfo(aData.getClass(),
				Object.class).getPropertyDescriptors();
		// 在表格的第一行建立一个数据行，用来放置这些属性的名称
		title = sheet.createRow(0);
		// 设置行高.注意值设置的很大。。
		title.setHeight((short) 500);
		for (short temp = 0; temp < props.length; temp++) {
			Cell cell = title.createCell(temp);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(props[temp].getShortDescription());
			// 设置各个列的宽度
			sheet.setColumnWidth((short) temp, (short) 5000);
		}
		for (int temp = 0; temp < data.size(); temp++) {
			// 实际的数据是开始从第二行开始进行传递的
			Row row = sheet.createRow(temp + 1);
			// 取出javabean对象里面的各个属性的值
			Object obj = data.get(temp);
			String values[] = getPropertyOfBean(obj);
			for (short cellNum = 0; cellNum < values.length; cellNum++) {
				Cell cell = row.createCell(cellNum);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(values[cellNum]);
			}
		}
	}

	public void addSheetFromBeanList(String sheetName, List data,
			String targetFile, boolean createEmpty) {
		Workbook workbook = null;
		try {
			checkFile(targetFile, sheetName, createEmpty);
			workbook = getWorkbook(targetFile);
			if (workbook == null) {
				workbook = createWorkBook(new File(targetFile), targetFile);
			}
			createExcelFromList(workbook, data, sheetName);
			saveExcel(workbook, targetFile);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("出现异常", e);
		}
	}

	private void createExcelFromExcelSheet(Workbook workbook,
			ExcelSheet _sheet, String sheetName) throws Exception {
		Sheet _st = workbook.getSheet(sheetName);
		if (_sheet.getRows() == null || _sheet.getRows().size() < 1) {
			throw new RuntimeException("数据不得为空");
		}
		Sheet sheet = null;
		if (_st != null) {
			sheet = _st;
		} else {
			sheet = workbook.createSheet(sheetName);
		}

		ExcelRow _title = _sheet.getTitle();
		int _r = 0;
		if (_title != null) {
			List<ExcelCol> headers = _title.getCols();
			if (headers != null) {
				int j = headers.size();
				Row title = sheet.createRow(_r++);
				for (short temp = 0; temp < j; temp++) {
					Cell cell = title.createCell(temp);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(headers.get(temp).getValue());
				}
			}
		}
		List<ExcelRow> rows = _sheet.getRows();
		int j = rows.size();
		for (short temp = 0; temp < j; temp++) {
			Row ct = sheet.createRow(_r++);
			List<ExcelCol> cols = rows.get(temp).getCols();
			int jj = cols.size();
			for (short temp2 = 0; temp2 < jj; temp2++) {
				Cell cell = ct.createCell(temp2);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(cols.get(temp2).getValue());
			}
		}
	}

	public void addSheetFromExcelSheet(String sheetName, ExcelSheet sheet,
			String targetFile, boolean createEmpty) {
		Workbook workbook = null;
		try {
			checkFile(targetFile, sheetName, createEmpty);
			workbook = getWorkbook(targetFile);
			if (workbook == null) {
				workbook = createWorkBook(new File(targetFile), targetFile);
			}
			createExcelFromExcelSheet(workbook, sheet, sheetName);
			saveExcel(workbook, targetFile);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("出现异常", e);
		}
	}

	/**
	 * 更新指定的cell对象.
	 * 
	 * @param c
	 * @param newValue
	 * @return
	 */
	private Cell updateCellValue(Cell c, String newValue) {
		Cell cell = c;
		if (cell != null) {
			switch (cell.getCellType()) {
			case 0: {// 数字
				cell.setCellValue(Double.parseDouble(newValue));
				break;
			}
			case 1: {// 字符串
				cell.setCellValue(newValue);
				break;
			}
			case 3: {// 空值
				try {
					cell.setCellValue(Double.parseDouble(newValue));
				} catch (Exception e) {
					cell.setCellValue(newValue);
				}
				break;
			}
			case 4: {// boolean类型
				cell.setCellValue(Boolean.parseBoolean(newValue));
				break;
			}
			default:
				break;
			}
		}
		return cell;
	}

	/**
	 * 更新单元格内容为新的内容.
	 * 
	 * @param sheetNum
	 * @param rowindex
	 * @param colIndex
	 * @param newValue
	 */
	public void updateCellValue(String excelFile, int sheetNum, int rowindex,
			int colIndex, String newValue) {
		Workbook workbook;
		try {
			checkFile(excelFile);
			workbook = getWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(sheetNum);
			if (sheet == null)
				throw new RuntimeException("执行sheet不存在");
			Row row = sheet.getRow(rowindex);
			if (row == null)
				row = sheet.createRow(rowindex);
			Cell cell = row.getCell(colIndex);
			if (cell == null)
				cell = row.createCell(colIndex);
			updateCellValue(cell, newValue);

			saveExcel(workbook, excelFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveExcel(Workbook workbook, String fileName) {
		FileOutputStream fOut;
		try {
			fOut = new FileOutputStream(fileName);
			workbook.write(fOut);
			fOut.flush();
			// 操作结束，关闭文件
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Workbook createWorkBook(File f, String targetFile) {
		Workbook workbook = null;
		if ("xls".equals(Util.getFileExtension(f.getName()))) {
			workbook = new HSSFWorkbook();
		} else {
			workbook = new XSSFWorkbook();
		}
		return workbook;
	}

	public void createEmptyExcel(String targetFile, String sheetName) {
		try {
			Workbook workbook = null;
			FileOutputStream fOut;
			File f = new File(targetFile);
			if (f.exists())
				return;
			try {
				fOut = new FileOutputStream(targetFile);
				workbook = createWorkBook(f, targetFile);
				workbook.createSheet(sheetName);
				workbook.write(fOut);
				fOut.flush();
				// 操作结束，关闭文件
				fOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createEmptyExcel(String targetFile) throws Exception {
		createEmptyExcel(targetFile, "Sheet1");
	}
}