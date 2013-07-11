package brightmoon.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import brightmoon.util.Util;

/**
 * 读取POI的类.支持excel2007有效. 
 * 
 */
public class NPOIReader extends ExcelTool implements IExcelReader {
	/**
	 * 工作簿
	 */
	private Workbook workbook;

	/**
	 * 日志记录
	 */
	private Logger log;

	/**
	 * excel表
	 */
	private Sheet sheet;

	/**
	 * 当前读取的sheet的页码
	 */
	private int currentSheetNum;
	private int currentRow;
	private int currentCell;

	/**
	 * excel文件流
	 */
	private FileInputStream fis;

	/**
	 * 消息
	 */
	private StringBuffer msg = null;

	/**
	 * 初始化对象.
	 * 
	 * @param exl
	 * @throws Exception
	 */
	public NPOIReader(File exl) throws Exception {
		fis = new FileInputStream(exl);
		try {
			workbook = WorkbookFactory.create(fis);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		msg = new StringBuffer();
	}

	public NPOIReader(String exlFileName) throws IOException {
		File file = new File(exlFileName);
		fis = new FileInputStream(file);
		try {
			workbook = WorkbookFactory.create(fis);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = new StringBuffer();
	}

	public NPOIReader(InputStream input) throws Exception {
		try {
			workbook = WorkbookFactory.create(input);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		msg = new StringBuffer();
	}

	public void destory() {
		try {
			msg = null;
			if (fis != null)
				fis.close();
		} catch (Exception ex) {
			msg.append(ex.getMessage());
		}
	}

	public void error(Object o) {
		if (o instanceof Exception) {
			Exception e = (Exception) o;
			e.printStackTrace();
		} else
			System.out.println(o);
	}

	/**
	 * 更新单元格内容为新的内容.
	 * 
	 * @param sheetNum
	 * @param rowindex
	 * @param colIndex
	 * @param newValue
	 */
	public void updateCellValue(int sheetNum, int rowindex, int colIndex,
			String newValue) {
		Cell cell = getCell(sheetNum, rowindex, colIndex);
		updateCellValue(cell, newValue);
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
	 * 得到excel的制定sheetNum的名称。 缓存在prop中.
	 * 
	 * @param num
	 * @return
	 */
	public String getSheetName(int num) {
		setCurrentSheet(num);
		return sheet.getSheetName();
	}

	/**
	 * 可以看到根据下面的五个参数确定了一个excel文件页面里面的一个矩形区域。
	 * 
	 * @param sheetNum
	 *            文件的页面
	 * @param firstRowNum
	 *            第一行的行数
	 * @param lastRowNum
	 *            最后一行的行数
	 * @param firstColIndex
	 *            第一列的列数
	 * @param lastColIndex
	 *            最后一列的列数
	 * @return
	 */
	public String[][] getSheetAsTable(int sheetNum, int firstRowNum,
			int lastRowNum, int firstColIndex, int lastColIndex) {
		String[][] cells = null;
		if (setCurrentSheet(sheetNum)) {
			cells = new String[lastRowNum - firstRowNum + 1][lastColIndex
					- firstColIndex + 1];
			for (int c1 = firstRowNum; c1 <= lastRowNum; c1++) {
				for (int c2 = firstColIndex; c2 <= lastColIndex; c2++) {
					try {
						cells[c1][c2] = getCellAsStringByIndex(c1, c2);
					} catch (Exception e) {
						e.printStackTrace();
						cells[c1][c2] = "";
					}
				}
			}
		}
		return cells;
	}

	/**
	 * 设置当前excel的sheet页.
	 * 
	 * @param num
	 * @return
	 */
	public boolean setCurrentSheet(int num) {
		if (workbook != null && num < workbook.getNumberOfFonts()) {
			try {
				String index = getDataIndex(num);
				sheet = workbook.getSheetAt(num);
				currentSheetNum = num;
				return true;
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private String getDataIndex(int sheetNum) {
		return "sheet_" + sheetNum;
	}

	private String getDataIndex(int sheetNum, int rownum) {
		return "sheet_" + sheetNum + "_row_" + rownum;
	}

	private String getDataIndex(int sheetNum, int rownum, int cellnum) {
		return "sheet_" + sheetNum + "_row_" + rownum + "_cell_" + cellnum;
	}

	private Row getRow(int rowId) {
		return getRow(currentSheetNum, rowId);
	}

	/**
	 * 得到excel中的指定行.
	 * 
	 * @param sheetId
	 *            sheet索引
	 * @param rowId
	 *            行索引
	 * @return
	 */
	private Row getRow(int sheetId, int rowId) {
		String sheetRowIndex = getDataIndex(sheetId, rowId);
		Row row = sheet.getRow(rowId);
		return row;
	}

	/**
	 * 得到excel中的制定cell
	 * 
	 * @param sheetId
	 *            sheet索引
	 * @param rowId
	 *            行索引
	 * @param colId
	 *            列索引
	 * @return
	 */
	public Cell getCell(int sheetId, int rowId, int colId) {
		Row row = getRow(rowId);
		String sheetCellIndex = getDataIndex(sheetId, rowId, colId);
		Cell cell = row.getCell((short) colId);
		return cell;
	}

	private Cell getCell(int rowId, int colId) {
		return getCell(currentSheetNum, rowId, colId);
	}

	/**
	 * 返回指定位置的单元格.
	 * 
	 * @param rowId
	 *            设置单元格的行
	 * @param colId
	 *            设置单元格的列
	 * @return
	 */
	public String getCellAsStringByIndex(int rowId, int colId) {
		String cellStr = "";
		if (sheet != null && rowId < sheet.getLastRowNum() + 1) {
			try {
				Row row = getRow(rowId);
				if (row != null) {
					if (colId < row.getLastCellNum()) {
						Cell cell = getCell(rowId, colId);
						currentRow = rowId;
						currentCell = colId;
						cellStr = getCellContent(cell);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				cellStr = "";
			}
		}
		// System.out.println("rowid:"+getCellInfo(rowId,
		// colId)+",cellStr="+cellStr);
		return cellStr;
	}

	/**
	 * 得到number单元格的内容.
	 * 
	 * @param cell
	 * @return
	 */
	private String getNumber(Cell cell) {
		String str = NumberFormat.getNumberInstance().format(
				cell.getNumericCellValue());
		while (str.indexOf(",") > -1) {
			str = str.substring(0, str.indexOf(","))
					+ str.substring(str.indexOf(",") + 1);
		}
		return str;
	}

	/**
	 * 得到日期单元格的内容.
	 * 
	 * @param cell
	 * @return
	 */
	private String getDate(Cell cell) {
		Date d = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(d);
	}

	private String getCellContent(Cell cell) {
		String cellStr = "";
		if (cell != null) {
			try {
				switch (cell.getCellType()) {
				case 0: {// 数字
					// 检查是不是日期类型.
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						cellStr = getDate(cell);
					} else {
						cellStr = getNumber(cell);
					}
					break;
				}
				case 1: {// 字符串
					cellStr = cell.getStringCellValue();
					break;
				}
				case 2: {// 公式
					String formula = cell.getCellFormula();
					if (formula.indexOf("DATE(") >= 0) {
						cellStr = getDate(cell);
					} else if (formula.indexOf("SUM(") >= 0) {
						cellStr = Double.toString(cell.getNumericCellValue());
					} else if (formula.indexOf("SIN(") >= 0) {
						cellStr = Double.toString(cell.getNumericCellValue());
					} else {
						// 是数字的日期格式,就读取日期形式.
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							cellStr = getDate(cell);
						} else {
							try {
								cellStr = cell.getStringCellValue();
							} catch (Exception eee) {
								try {
									cellStr = getNumber(cell);
								} catch (Exception f) {
									error(f);
								}
							}
						}
					}
					break;
				}
				case 3: {// 空值.
					cellStr = cell.getStringCellValue();
					break;
				}
				case 4: {// boolean类型
					cellStr = Boolean.toString(cell.getBooleanCellValue());
					break;
				}
				default: {// 其他
					cellStr = new String("");
				}
					if (cellStr == null) {
						cellStr = "";
					}
				}
			} catch (Exception e) {
				error(e);
				cellStr = "";
			}
		}
		return cellStr;
	}

	private void error(Exception f) {
		f.printStackTrace();
		System.out.println("出现错误!sheet:" + currentSheetNum + ";出错单元格:"
				+ ExcelTool.getCellInfo(currentRow, currentCell));
	}

	/**
	 * 返回指定sheet,指定行,指定列的单元格内容.
	 * 
	 * @param sheetNum
	 * @param rowindex
	 * @param colIndex
	 * @return
	 */
	public String getCellAsStringByIndex(int sheetNum, int rowindex,
			int colIndex) {
		return getCellAsStringByIndex(sheetNum, rowindex, colIndex, "");
	}

	/**
	 * 返回指定sheet,指定行,指定列的单元格内容.
	 * 
	 * @param sheetNum
	 *            sheet索引
	 * @param rowindex
	 *            行索引
	 * @param colIndex
	 *            列索引
	 * @param defaultStr
	 *            空值转换为默认值
	 * @return
	 */
	public String getCellAsStringByIndex(int sheetNum, int rowindex,
			int colIndex, String defaultStr) {
		if (setCurrentSheet(sheetNum)) {
			return Util.notBlank(getCellAsStringByIndex(rowindex, colIndex),
					defaultStr);
		}
		return defaultStr;
	}

	public void emptyCell(Cell cell) {
		System.out.println(cell.getCellType());
	}

	public String getErrorMessage() {
		return msg.toString();
	}

	/**
	 * 返回当前的页面
	 * 
	 * @return
	 */
	private Sheet getSheet() {
		return sheet;
	}

	/**
	 * 返回当前的工作簿
	 * 
	 * @return
	 */
	private Workbook getWorkbook() {
		return workbook;
	}

	/**
	 * 
	 * @param fileName
	 *            要读取的excel文件名
	 * @param sheetNum
	 *            要读取的表单的数目
	 * @param row
	 *            要读取的单元格行数
	 * @param col
	 *            要读取的单元格列数
	 * @return
	 */
	public String readExcel(int sheetNum, int row, int col) {
		try {
			setCurrentSheet(sheetNum - 1);
			return getCellAsStringByIndex((row - 1), (col - 1));
		} catch (Exception e) {
			e.printStackTrace();
			return "出现异常，可能是文件未找到！";
		}
	}

	/**
	 * 读取excel文件的一个表格里面的基本信息：最大行数.
	 * 
	 * @param fileName
	 *            文件名
	 * @param sheetNum
	 *            要读取的表单的数目
	 * @return
	 */
	public String getRowNum(int sheetNum) {
		try {
			setCurrentSheet(sheetNum - 1);
			Sheet sheet = getSheet();
			int rowNum = sheet.getLastRowNum() + 1;
			return new Integer(rowNum).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "检查输入的文件是否存在或者页面不存在！";
		}
	}

	/**
	 * 读取excel中某一个表单的某一行的最大列数.
	 * 
	 * @param fileName
	 *            文件名
	 * @param sheetNum
	 *            表单的数目
	 * @param row
	 *            行数
	 * @return
	 */
	public String getColNum(int sheetNum, int rowId) {
		Row row;
		int colNum;
		try {
			setCurrentSheet(sheetNum - 1);
			row = getRow(rowId);
			colNum = row.getLastCellNum();
			return new Integer(colNum).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "检查输入的文件是否存在！";
		}
	}

	/**
	 * 获取文件的工作簿的数目.
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public String getSheetNum() {
		try {
			return new Integer(workbook.getNumberOfSheets()).toString();
		} catch (Exception e) {
			error(e);
			return "";
		}
	}

	/**
	 * 返回全部的sheet的名称.是集合类型.
	 * 
	 * @return
	 */
	public List getSheetNames() {
		int num = Integer.parseInt(getSheetNum());
		List result = new ArrayList();
		for (int i = 0; i < num; i++) {
			result.add(workbook.getSheetName(i));
		}
		return result;
	}

	/**
	 * 返回指定文件的页面的全部数据.
	 * 
	 * @param fileName
	 *            文件名
	 * @param sheetNum
	 *            文件的表数
	 * @return String[][]
	 */
	public String[][] readAllExcel(int sheetNum) {
		return readAllExcel(sheetNum, false);
	}

	/**
	 * 返回指定文件页面全部数据.
	 * 
	 * @param fileName
	 *            文件名
	 * @param sheetNums
	 *            指定文件的sheetid的集合,对多个id组成的数组.
	 * @return
	 */
	public List<String[][]> readAllExcel(int[] sheetNums) {
		return readAllExcel(sheetNums, false);
	}

	/**
	 * 返回指定excel中的指定sheet内容.
	 * 
	 * @param sheetNum
	 *            sheet索引
	 * @param returnMeetFirstNullRow
	 *            是否遇到第一个空行自动返回
	 * @return
	 */
	public String[][] readAllExcel(int sheetNum, boolean returnMeetFirstNullRow) {
		int[] sheetNums = new int[] { sheetNum };
		List<String[][]> ans = readAllExcel(sheetNums, returnMeetFirstNullRow);
		return ans.get(0);
	}

	/**
	 * 返回指定excel中的指定sheet内容.
	 * 
	 * @param sheetNum
	 *            sheet索引
	 * @param returnMeetFirstNullRow
	 *            是否遇到第一个空行自动返回
	 * @param autoClose
	 *            读取完毕是否自动关闭
	 * @return
	 */
	public String[][] readAllExcel(int sheetNum,
			boolean returnMeetFirstNullRow, boolean autoClose) {
		int[] sheetNums = new int[] { sheetNum };
		List<String[][]> ans = readAllExcel(sheetNums, returnMeetFirstNullRow,
				autoClose);
		return ans.get(0);
	}

	/**
	 * 是否自动关闭excel.
	 * 
	 * @param sheetNums
	 * @param returnMeetFirstNullRow
	 * @param autoClose
	 * @return
	 */
	private List<String[][]> readAllExcel(int[] sheetNums,
			boolean returnMeetFirstNullRow, boolean autoClose) {
		// 如果遇到第一个空行自动返回,调用下面的方法.
		List<String[][]> ans = new ArrayList<String[][]>();
		int sheetId;
		Sheet sheet;
		int maxRowNum;
		Row row;
		int maxColNum;
		String[][] result;
		try {
			for (int i = sheetNums.length - 1; i >= 0; i--) {
				result = new String[][] { {} };
				sheetId = sheetNums[i] - 1;
				setCurrentSheet(sheetId);
				sheet = getSheet();
				// 如果设置了要遇到第一个空行就自动返回,就计算maxRowNum!
				if (returnMeetFirstNullRow) {
					maxRowNum = 0;
					row = null;
					// 得到从0行开始的第一个非空行数.
					for (;; maxRowNum++) {
						row = getRow(maxRowNum);
						if (row == null) {
							if (maxRowNum != 0)
								maxRowNum--;
							break;
						}
					}
				}
				// 否则直接取全部的excel的行数
				else {
					maxRowNum = sheet.getLastRowNum();
				}
				if (maxRowNum != 0) {
					// 得到第一行的列数.
					row = getRow(0);
					maxColNum = row.getLastCellNum();
					result = getSheetAsTable(sheetId, 0, maxRowNum, 0,
							maxColNum - 1);
				}
				ans.add(result);
			}
			if (autoClose)
				destory();
			return ans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 一次返回多个sheet的内容.
	 * 
	 * @param excelRd
	 * @param sheetNums
	 * @param returnMeetFirstNullRow
	 * @return
	 */
	private List<String[][]> readAllExcel(int[] sheetNums,
			boolean returnMeetFirstNullRow) {
		return readAllExcel(sheetNums, returnMeetFirstNullRow, true);
	}

	public String getCellAsStringByIndex(String colStr) {
		int row = getNumFromExcelStr(getCodeFromStr(colStr));
		int col = getNumFromStr(colStr);
		return getCellAsStringByIndex(row, col);
	}
}