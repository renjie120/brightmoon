package brightmoon.excel;

import java.util.ArrayList;
import java.util.List;

public class ExcelRow {
	private List<ExcelCol> cols; 
	
	public void addCol(ExcelCol col){
		if(cols==null){
			cols = new ArrayList<ExcelCol>();
		}
		cols.add(col);
	}

	public List<ExcelCol> getCols() {
		return cols;
	} 
}