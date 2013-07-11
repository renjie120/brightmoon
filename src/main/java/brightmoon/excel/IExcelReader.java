package brightmoon.excel;

import java.util.List;

/**
 * 读取excel的接口，提供读取excel的多样化的方法.
 * @author lsq
 *
 */
public interface IExcelReader {
	/**
	 * 返回指定sheet的指定行的列数.
	 * @param sheetNum
	 * @param rowId
	 * @return
	 */
	public String getColNum(int sheetNum, int rowId) ;
	
	/**
	 * 设置当前进行操作的excel的sheet.
	 * @param num
	 * @return
	 */
	public boolean setCurrentSheet(int num);
	
	/**
	 * 返回指定单元格的内容.
	 * sheetId通过setCurrentSheet进行设置.
	 * @param rowId
	 * @param colId
	 * @return
	 */
	public String getCellAsStringByIndex(int rowId, int colId);
	
	/**
	 * 通过cell单元格字符串返回单元格内容.
	 * @param colStr
	 * @return
	 */
	public String getCellAsStringByIndex(String colStr);
	
	/**
	 * 返回单元格的内容.
	 * @param sheetNum
	 * @param rowindex
	 * @param colIndex
	 * @return
	 */
	public String getCellAsStringByIndex(int sheetNum, int rowindex,
			int colIndex);
	
	/**
	 * 返回单元格的内容，可以设置默认值.
	 * @param sheetNum
	 * @param rowindex
	 * @param colIndex
	 * @param defaultStr
	 * @return
	 */
	public String getCellAsStringByIndex(int sheetNum, int rowindex,
			int colIndex, String defaultStr);
	 
	/**
	 * 返回一个sheet的行数.
	 * @param sheetNum
	 * @return
	 */
	public String getRowNum(int sheetNum);
	
	/**
	 * 将一个sheet的内容以字符串数组形式返回.
	 * @param sheetNum
	 * @param firstRowNum
	 * @param lastRowNum
	 * @param firstColIndex
	 * @param lastColIndex
	 * @return
	 */
	public String[][] getSheetAsTable(int sheetNum, int firstRowNum,
			int lastRowNum, int firstColIndex, int lastColIndex) ;
	
	/**
	 * 得到指定索引的sheet的名称.
	 * @param num
	 * @return
	 */
	public String getSheetName(int num);
	
	/**
	 * 得到excel的全部的sheet的名称.
	 * @return
	 */
	public List getSheetNames();
	
	/**
	 * 得到指定的sheet索引的excel的内容。
	 * @param sheetNum
	 * @return
	 */
	public String[][] readAllExcel(int sheetNum) ;
	
	public String[][] readAllExcel(int sheetNum, boolean returnMeetFirstNullRow);
	
	public String[][] readAllExcel(int sheetNum,
			boolean returnMeetFirstNullRow, boolean autoClose);
	
	public List<String[][]> readAllExcel(int[] sheetNums);
	
	/**
	 * 读取指定位置单元格的内容，返回字符串
	 * @param sheetNum
	 * @param row
	 * @param col
	 * @return
	 */
	public String readExcel(int sheetNum, int row, int col) ;
}