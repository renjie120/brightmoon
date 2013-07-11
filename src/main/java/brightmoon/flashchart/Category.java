package brightmoon.flashchart;
/**
 * @ClassName: Category
 * @Description: Category标签
 * @author 419723443@qq.com
 * @date Jan 21, 2010 2:49:16 PM
 * 
 */
public class Category extends FlashChartBase implements IBaseTag {
	/** 标签 */
	private String label;
	/** 是否显示标签 */
	private boolean showLabel;
	/** 提示 */
	private String toolText;
	// 下面适用于multi-series/combination charts
	/**
	 * define the x value (numerical position on the x-axis) where this category
	 * name would be placed
	 */
	private double x;
	/** 是否出现垂直线 */
	private boolean showVerticalLine;
	/** 线是否出现虚线框 */
	private boolean lineDashed;

	public Category() {
	}

	public Category(String label) {
		getRegisterAtrributes().add("label");
		this.label = label;
	}

	public String toString() {
		start();
		if (getRegisterAtrributes().contains("label"))
			this.add("label =").addValue(label);
		if (getRegisterAtrributes().contains("showLabel"))
			this.add("showLabel =").addValue(showLabel);
		if (getRegisterAtrributes().contains("toolText"))
			this.add("toolText =").addValue(toolText);
		if (getRegisterAtrributes().contains("x"))
			this.add("x =").addValue(x);
		if (getRegisterAtrributes().contains("showVerticalLine"))
			this.add("showVerticalLine =").addValue(showVerticalLine);
		if (getRegisterAtrributes().contains("lineDashed"))
			this.add("lineDashed =").addValue(lineDashed);
		end();
		return this.getString();
	}

	public FlashChartBase end() {
		getStringBuffer().append("/>");
		return this;
	}

	public FlashChartBase start() {
		getStringBuffer().append("<category ");
		return this;
	}

	public String getLabel() {
		return label;
	}

	public Category setLabel(String label) {
		getRegisterAtrributes().add("label");
		this.label = label;
		return this;
	}

	public boolean isShowLabel() {
		return showLabel;
	}

	public Category setShowLabel(boolean showLabel) {
		getRegisterAtrributes().add("showLabel");
		this.showLabel = showLabel;
		return this;
	}

	public String getToolText() {
		return toolText;
	}

	public Category setToolText(String toolText) {
		getRegisterAtrributes().add("toolText");
		this.toolText = toolText;
		return this;
	}

	public double getX() {
		return x;
	}

	public Category setX(double x) {
		this.x = x;
		getRegisterAtrributes().add("x");
		return this;
	}

	public boolean isShowVerticalLine() {
		return showVerticalLine;
	}

	public Category setShowVerticalLine(boolean showVerticalLine) {
		this.showVerticalLine = showVerticalLine;
		getRegisterAtrributes().add("showVerticalLine");
		return this;
	}

	public boolean isLineDashed() {
		return lineDashed;
	}

	public Category setLineDashed(boolean lineDashed) {
		this.lineDashed = lineDashed;
		getRegisterAtrributes().add("lineDashed");
		return this;
	}

}
