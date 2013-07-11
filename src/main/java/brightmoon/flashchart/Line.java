package brightmoon.flashchart;

/**
 * @ClassName: Line
 * @Description: Line标签
 * @author 419723443@qq.com
 * @date Jan 22, 2010 12:59:30 PM
 * 
 */
public class Line extends FlashChartBase implements IBaseTag {
	/** trendline开始的值 */
	private double startValue;
	/** trendline终止的值 */
	private double endValue;
	/** 显示值 */
	private String displayValue;
	/** trendline颜色 */
	private String color;
	/** trendline厚度 */
	private int thickness;
	/** trendline透明度(0-100) */
	private int alpha;
	/** 虚线长度 */
	private int dashLen;
	/** 虚线空白处长度 */
	private int dashGap;
	/** 提示信息 */
	private String toolText;
	/** trend是线形或者是区域 */
	private boolean isTrendZone;
	/** 在线的左边或者右边出现 */
	private boolean valueOnRight;
	/** 是否显示虚线 */
	private boolean dashed;
	/**
	 * Whether the trend line/zone would be displayed over data plots or under
	 * them. (Bubble.swf中)
	 */
	private boolean showOnTop;
	/** 线的厚度 */
	private int lineThickness;

	public FlashChartBase end() {
		getStringBuffer().append("/>");
		return this;
	}

	public FlashChartBase start() {
		getStringBuffer().append("<line ");
		return this;
	}

	public String toString() {
		start();
		if (getRegisterAtrributes().contains("startValue"))
			this.add("startValue=").addValue(startValue);
		if (getRegisterAtrributes().contains("endValue"))
			this.add("endValue=").addValue(endValue);
		if (getRegisterAtrributes().contains("displayValue"))
			this.add("displayValue=").addValue(displayValue);
		if (getRegisterAtrributes().contains("color"))
			this.add("color=").addValue(color);
		if (getRegisterAtrributes().contains("thickness"))
			this.add("thickness=").addValue(thickness);
		if (getRegisterAtrributes().contains("alpha"))
			this.add("alpha=").addValue(alpha);
		if (getRegisterAtrributes().contains("dashLen"))
			this.add("dashLen=").addValue(dashLen);
		if (getRegisterAtrributes().contains("dashGap"))
			this.add("dashGap=").addValue(dashGap);
		if (getRegisterAtrributes().contains("toolText"))
			this.add("toolText=").addValue(toolText);
		if (getRegisterAtrributes().contains("isTrendZone"))
			this.add("isTrendZone=").addValue(isTrendZone);
		if (getRegisterAtrributes().contains("valueOnRight"))
			this.add("valueOnRight=").addValue(valueOnRight);
		if (getRegisterAtrributes().contains("dashed"))
			this.add("dashed=").addValue(dashed);
		if (getRegisterAtrributes().contains("showOnTop"))
			this.add("showOnTop=").addValue(showOnTop);
		if (getRegisterAtrributes().contains("lineThickness"))
			this.add("lineThickness=").addValue(lineThickness);
		end();
		return this.getString();
	}

	public double getStartValue() {
		return startValue;
	}

	public Line setStartValue(double startValue) {
		this.startValue = startValue;
		getRegisterAtrributes().add("startValue");
		return this;
	}

	public double getEndValue() {
		return endValue;
	}

	public Line setEndValue(double endValue) {
		this.endValue = endValue;
		getRegisterAtrributes().add("endValue");
		return this;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public Line setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
		getRegisterAtrributes().add("displayValue");
		return this;
	}

	public String getColor() {
		return color;
	}

	public Line setColor(String color) {
		getRegisterAtrributes().add("color");
		this.color = color;
		return this;
	}

	public int getThickness() {
		return thickness;
	}

	public Line setThickness(int thickness) {
		this.thickness = thickness;
		getRegisterAtrributes().add("thickness");
		return this;
	}

	public int getAlpha() {
		return alpha;
	}

	public Line setAlpha(int alpha) {
		getRegisterAtrributes().add("alpha");
		this.alpha = alpha;
		return this;
	}

	public int getDashLen() {
		return dashLen;
	}

	public Line setDashLen(int dashLen) {
		this.dashLen = dashLen;
		getRegisterAtrributes().add("dashLen");
		return this;
	}

	public int getDashGap() {
		return dashGap;
	}

	public Line setDashGap(int dashGap) {
		this.dashGap = dashGap;
		getRegisterAtrributes().add("dashGap");
		return this;
	}

	public String getToolText() {
		return toolText;
	}

	public Line setToolText(String toolText) {
		this.toolText = toolText;
		getRegisterAtrributes().add("toolText");
		return this;
	}

	public boolean isTrendZone() {
		return isTrendZone;
	}

	public Line setTrendZone(boolean isTrendZone) {
		this.isTrendZone = isTrendZone;
		getRegisterAtrributes().add("isTrendZone");
		return this;
	}

	public boolean isValueOnRight() {
		return valueOnRight;
	}

	public Line setValueOnRight(boolean valueOnRight) {
		this.valueOnRight = valueOnRight;
		getRegisterAtrributes().add("valueOnRight");
		return this;
	}

	public boolean isDashed() {
		return dashed;
	}

	public Line setDashed(boolean dashed) {
		this.dashed = dashed;
		getRegisterAtrributes().add("dashed");
		return this;
	}

	public boolean isShowOnTop() {
		return showOnTop;
	}

	public Line setShowOnTop(boolean showOnTop) {
		this.showOnTop = showOnTop;
		getRegisterAtrributes().add("showOnTop");
		return this;
	}

	public int getLineThickness() {
		return lineThickness;
	}

	public Line setLineThickness(int lineThickness) {
		this.lineThickness = lineThickness;
		getRegisterAtrributes().add("lineThickness");
		return this;
	}

}
