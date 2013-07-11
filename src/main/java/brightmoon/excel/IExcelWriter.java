package brightmoon.excel;

import java.util.List;

/**
 * 对excel进行操作的工具方法接口. 
 * @author lsq
 * 
 */
public interface IExcelWriter {
	/**
	 * 根据集合里面的javabean的集合添加到excel中.
	 * @param sheetName sheet页名称
	 * @param data 集合数据
	 * @param targetFile 目标文件地址
	 * @param createEmpty 如果目标文件不存在是否新建一个excel文件，否则抛错
	 */
	public void addSheetFromBeanList(String sheetName, List data,
			String targetFile, boolean createEmpty);

	/**
	 * 根据ExcelSheet的对象内容添加到excel中.
	 * @param sheetName sheet页名称
	 * @param sheet sheet对象
	 * @param targetFile 目标文件地址
	 * @param createEmpty 如果目标文件不存在是否新建一个excel文件，否则抛错.
	 */
	public void addSheetFromExcelSheet(String sheetName, ExcelSheet sheet,
			String targetFile, boolean createEmpty);

	/**
	 * 创建一个空的excel文件。
	 * @param targetFile excel地址
	 * @throws Exception
	 */
	public void createEmptyExcel(String targetFile) throws Exception;

	/**
	 * 更新指定的sheet页的指定sheet，行，列的值.
	 * @param excelFile excel文件地址
	 * @param sheetIndex sheet索引
	 * @param rowIndex 行索引
	 * @param colIndex 列索引
	 * @param newValue 更新的内容
	 */
	public void updateCellValue(String excelFile, int sheetIndex, int rowIndex,
			int colIndex, String newValue);
}