package brightmoon.flashchart;


/**
 * @ClassName: Set
 * @Description: 存储数值的基础类
 * @author 419723443@qq.com
 * @date Jan 20, 2010 3:58:43 PM
 * 
 */
public class Data extends FlashChartBase implements IBaseTag {
	/** 显示值 */
	private String label;
	/** 实际数值 */
	private String value;
	/** 提示值 */
	private String displayValue;
	/** 颜色 */
	private String color;
	/** 链接 */
	private String link;
	/** 提示文本 */
	private String toolText;
	/** 是否显示label */
	private boolean showLabel;
	/** 是否显示实际值 */
	private boolean showValue;
	/** 是否显示虚线框 */
	private boolean dashed;
	/** 设置透明度(1-100) */
	private int alpha;
	/** Scatter图中的:x位置 */
	private double x;
	/** Scatter图中的:y位置 */
	private double y;
	// 下面是ScrollCombiDY2D等multi-series and combination chart中的属性
	/** specify "set" specific sides of the anchor */
	private int anchorSides;
	/** 锚点的半径 */
	private int anchorRadius;
	/** 锚点边界的厚度 */
	private int anchorBorderThickness;
	/** 锚点透明度0-100 */
	private int anchorAlpha;
	/** 锚点背景透明度 */
	private int anchorBgAlpha;
	/** 锚点边界颜色 */
	private String anchorBorderColor;
	/** 锚点背景颜色 */
	private String anchorBgColor;
	// 下面Bubble 泡泡图
	/** z轴的值 */
	private double z;
	/** 设置泡泡的名字 */
	private String name;

	public Data() {

	}

	public Data(String value) {
		getRegisterAtrributes().add("value");
		this.value = value;
	}

	public FlashChartBase end() {
		getStringBuffer().append("/>");
		return this;
	}

	public FlashChartBase start() {
		getStringBuffer().append("<set ");
		return this;
	}

	public String toString() {
		start();
		if (getRegisterAtrributes().contains("value"))
			this.add("value=").addValue(value);
		if (getRegisterAtrributes().contains("label"))
			this.add("label=").addValue(label);
		if (getRegisterAtrributes().contains("displayValue"))
			this.add("displayValue=").addValue(displayValue);
		if (getRegisterAtrributes().contains("color"))
			this.add("color=").addValue(color);
		if (getRegisterAtrributes().contains("link"))
			this.add("link=").addValue(link);
		if (getRegisterAtrributes().contains("toolText"))
			this.add("toolText=").addValue(toolText);
		if (getRegisterAtrributes().contains("showLabel"))
			this.add("showLabel=").addValue(showLabel);
		if (getRegisterAtrributes().contains("showValue"))
			this.add("showValue=").addValue(showValue);
		if (getRegisterAtrributes().contains("dashed"))
			this.add("dashed=").addValue(dashed);
		if (getRegisterAtrributes().contains("alpha"))
			this.add("alpha=").addValue(alpha);
		if (getRegisterAtrributes().contains("x"))
			this.add("x=").addValue(x);
		if (getRegisterAtrributes().contains("y"))
			this.add("y=").addValue(y);
		if (getRegisterAtrributes().contains("anchorSides"))
			this.add("anchorSides=").addValue(anchorSides);
		if (getRegisterAtrributes().contains("anchorRadius"))
			this.add("anchorRadius=").addValue(anchorRadius);
		if (getRegisterAtrributes().contains("anchorBorderThickness"))
			this.add("anchorBorderThickness=").addValue(anchorBorderThickness);
		if (getRegisterAtrributes().contains("anchorAlpha"))
			this.add("anchorAlpha=").addValue(anchorAlpha);
		if (getRegisterAtrributes().contains("anchorBgAlpha"))
			this.add("anchorBgAlpha=").addValue(anchorBgAlpha);
		if (getRegisterAtrributes().contains("anchorBorderColor"))
			this.add("anchorBorderColor=").addValue(anchorBorderColor);
		if (getRegisterAtrributes().contains("anchorBgColor"))
			this.add("anchorBgColor=").addValue(anchorBgColor);
		if (getRegisterAtrributes().contains("name"))
			this.add("name=").addValue(name);
		if (getRegisterAtrributes().contains("z"))
			this.add("z=").addValue(z);
		end();
		return this.getString();
	}

	public static void main(String[] args) {
		Data s = new Data();
		s.setValue("aaa");
		s.setLabel("aaa");
		s.setDashed(false);
		s.setLink("www.baidu.com");
		System.out.println(s.toString());
	}

	public String getValue() {
		return value;
	}

	public Data setValue(String value) {
		getRegisterAtrributes().add("value");
		this.value = value;
		return this;
	}

	public String getLabel() {
		return label;
	}

	public Data setLabel(String label) {
		getRegisterAtrributes().add("label");
		this.label = label;
		return this;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public Data setDisplayValue(String displayValue) {
		getRegisterAtrributes().add("displayValue");
		this.displayValue = displayValue;
		return this;
	}

	public String getColor() {
		return color;
	}

	public Data setColor(String color) {
		getRegisterAtrributes().add("color");
		this.color = color;
		return this;
	}

	public String getLink() {
		return link;
	}

	public Data setLink(String link) {
		getRegisterAtrributes().add("link");
		this.link = link;
		return this;
	}

	public String getToolText() {
		return toolText;
	}

	public Data setToolText(String toolText) {
		getRegisterAtrributes().add("toolText");
		this.toolText = toolText;
		return this;
	}

	public boolean isShowLabel() {
		return showLabel;
	}

	public Data setShowLabel(boolean showLabel) {
		getRegisterAtrributes().add("showLabel");
		this.showLabel = showLabel;
		return this;
	}

	public boolean isShowValue() {
		return showValue;
	}

	public Data setShowValue(boolean showValue) {
		getRegisterAtrributes().add("showValue");
		this.showValue = showValue;
		return this;
	}

	public boolean isDashed() {
		return dashed;
	}

	public Data setDashed(boolean dashed) {
		getRegisterAtrributes().add("dashed");
		this.dashed = dashed;
		return this;
	}

	public int getAlpha() {
		return alpha;
	}

	public Data setAlpha(int alpha) {
		getRegisterAtrributes().add("alpha");
		this.alpha = alpha;
		return this;
	}

	public double getX() {
		return x;
	}

	public Data setX(double x) {
		getRegisterAtrributes().add("x");
		this.x = x;
		return this;
	}

	public double getY() {
		return y;
	}

	public Data setY(double y) {
		getRegisterAtrributes().add("y");
		this.y = y;
		return this;
	}

	public int getAnchorSides() {
		return anchorSides;
	}

	public Data setAnchorSides(int anchorSides) {
		getRegisterAtrributes().add("anchorSides");
		this.anchorSides = anchorSides;
		return this;
	}

	public int getAnchorRadius() {
		return anchorRadius;
	}

	public Data setAnchorRadius(int anchorRadius) {
		getRegisterAtrributes().add("anchorRadius");
		this.anchorRadius = anchorRadius;
		return this;
	}

	public int getAnchorBorderThickness() {
		return anchorBorderThickness;
	}

	public Data setAnchorBorderThickness(int anchorBorderThickness) {
		this.anchorBorderThickness = anchorBorderThickness;
		getRegisterAtrributes().add("anchorBorderThickness");
		return this;
	}

	public int getAnchorAlpha() {
		return anchorAlpha;
	}

	public Data setAnchorAlpha(int anchorAlpha) {
		getRegisterAtrributes().add("anchorAlpha");
		this.anchorAlpha = anchorAlpha;
		return this;
	}

	public int getAnchorBgAlpha() {
		return anchorBgAlpha;
	}

	public Data setAnchorBgAlpha(int anchorBgAlpha) {
		getRegisterAtrributes().add("anchorBgAlpha");
		this.anchorBgAlpha = anchorBgAlpha;
		return this;
	}

	public String getAnchorBorderColor() {
		return anchorBorderColor;
	}

	public Data setAnchorBorderColor(String anchorBorderColor) {
		this.anchorBorderColor = anchorBorderColor;
		getRegisterAtrributes().add("anchorBorderColor");
		return this;
	}

	public String getAnchorBgColor() {
		return anchorBgColor;
	}

	public Data setAnchorBgColor(String anchorBgColor) {
		this.anchorBgColor = anchorBgColor;
		getRegisterAtrributes().add("anchorBgColor");
		return this;
	}

	public double getZ() {
		return z;
	}

	public Data setZ(double z) {
		this.z = z;
		getRegisterAtrributes().add("z");
		return this;
	}

	public String getName() {
		return name;
	}

	public Data setName(String name) {
		this.name = name;
		getRegisterAtrributes().add("name");
		return this;
	}
}
