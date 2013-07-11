package brightmoon.excel;

import java.util.ArrayList;
import java.util.List;

public class ExcelSheet {
	private List<ExcelRow> rows; 
	private ExcelRow title;
	public ExcelRow getTitle() {
		return title;
	}

	public void setTitle(ExcelRow title) {
		this.title = title;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	} 
	private String sheetName;
	
	public void addRow(ExcelRow row){
		if(rows==null){
			rows = new ArrayList<ExcelRow>();
		}
		rows.add(row);
	}

	public List<ExcelRow> getRows() {
		return rows;
	} 
}