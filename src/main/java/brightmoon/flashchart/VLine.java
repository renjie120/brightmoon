package brightmoon.flashchart;
/**
 * @ClassName: VLine
 * @Description: VLine标签
 * @author 419723443@qq.com
 * @date Jan 22, 2010 11:18:14 AM
 * 
 */
public class VLine extends FlashChartBase implements IBaseTag {
	public FlashChartBase end() {
		getStringBuffer().append("/>");
		return this;
	}

	public FlashChartBase start() {
		getStringBuffer().append("<vLine ");
		return this;
	}

	/** 分割线颜色 */
	private String color;
	/** 分割线透明度(0-100) */
	private int alpha;
	/** 分割线厚度 */
	private int thickness;
	/** 虚线的长度 */
	private int dashLen;
	/** 虚线之间空白的长度 */
	private int dashGap;
	/** 分割线在两个距离直接的百分比位置(0-1) */
	private double linePosition;
	/** 标签描述在两个距离直接的百分比位置(0-1) */
	private double labelPosition;
	/** 是否用虚线框 */
	private boolean dashed;
	/** 是否显示标签边界 */
	private boolean showLabelBorder;
	/** 分割线标签的水平方位 */
	private LabelHAlign labelHAlign;
	/** 分割线的标签 */
	private String label;
	/** 分割线标签的垂直方位 */
	private LabelVAlign labelVAlign;

	public String toString() {
		start();
		if (getRegisterAtrributes().contains("color"))
			this.add("color=").addValue(color);
		if (getRegisterAtrributes().contains("alpha"))
			this.add("alpha=").addValue(alpha);
		if (getRegisterAtrributes().contains("dashLen"))
			this.add("dashLen=").addValue(dashLen);
		if (getRegisterAtrributes().contains("dashGap"))
			this.add("dashGap=").addValue(dashGap);
		if (getRegisterAtrributes().contains("linePosition"))
			this.add("linePosition=").addValue(linePosition);
		if (getRegisterAtrributes().contains("labelPosition"))
			this.add("labelPosition=").addValue(labelPosition);
		if (getRegisterAtrributes().contains("dashed"))
			this.add("dashed=").addValue(dashed);
		if (getRegisterAtrributes().contains("showLabelBorder"))
			this.add("showLabelBorder=").addValue(showLabelBorder);
		if (getRegisterAtrributes().contains("labelHAlign"))
			this.add("labelHAlign=").addValue(labelHAlign);
		if (getRegisterAtrributes().contains("label"))
			this.add("label=").addValue(label);
		if (getRegisterAtrributes().contains("labelVAlign"))
			this.add("labelVAlign=").addValue(labelVAlign);
		end();
		return getString();
	}

	public String getColor() {
		return color;
	}

	public VLine setColor(String color) {
		getRegisterAtrributes().add("color");
		this.color = color;
		return this;
	}

	public int getThickness() {
		return thickness;
	}

	public VLine setThickness(int thickness) {
		getRegisterAtrributes().add("thickness");
		this.thickness = thickness;
		return this;
	}

	public int getAlpha() {
		return alpha;
	}

	public VLine setAlpha(int alpha) {
		getRegisterAtrributes().add("alpha");
		this.alpha = alpha;
		return this;
	}

	public int getDashLen() {
		return dashLen;
	}

	public VLine setDashLen(int dashLen) {
		getRegisterAtrributes().add("dashLen");
		this.dashLen = dashLen;
		return this;
	}

	public int getDashGap() {
		return dashGap;
	}

	public VLine setDashGap(int dashGap) {
		getRegisterAtrributes().add("dashGap");
		this.dashGap = dashGap;
		return this;
	}

	public double getLinePosition() {
		return linePosition;
	}

	public VLine setLinePosition(double linePosition) {
		getRegisterAtrributes().add("linePosition");
		this.linePosition = linePosition;
		return this;
	}

	public double getLabelPosition() {
		return labelPosition;
	}

	public VLine setLabelPosition(double labelPosition) {
		getRegisterAtrributes().add("labelPosition");
		this.labelPosition = labelPosition;
		return this;
	}

	public boolean isDashed() {
		return dashed;
	}

	public VLine setDashed(boolean dashed) {
		getRegisterAtrributes().add("dashed");
		this.dashed = dashed;
		return this;
	}

	public boolean isShowLabelBorder() {
		return showLabelBorder;
	}

	public VLine setShowLabelBorder(boolean showLabelBorder) {
		getRegisterAtrributes().add("showLabelBorder");
		this.showLabelBorder = showLabelBorder;
		return this;
	}

	public LabelHAlign getLabelHAlign() {
		return labelHAlign;
	}

	public VLine setLabelHAlign(LabelHAlign labelHAlign) {
		getRegisterAtrributes().add("labelHAlign");
		this.labelHAlign = labelHAlign;
		return this;
	}

	public String getLabel() {
		return label;
	}

	public VLine setLabel(String label) {
		this.label = label;
		getRegisterAtrributes().add("label");
		return this;
	}

	public LabelVAlign getLabelVAlign() {
		return labelVAlign;
	}

	public VLine setLabelVAlign(LabelVAlign labelVAlign) {
		this.labelVAlign = labelVAlign;
		getRegisterAtrributes().add("labelVAlign");
		return this;
	}
}
