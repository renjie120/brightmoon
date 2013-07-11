package brightmoon.flashchart;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Categories
 * @Description: Categories标签
 * @author 419723443@qq.com
 * @date Jan 21, 2010 2:47:38 PM
 * 
 */
public class Categories extends FlashChartBase implements IBaseTag,
		IComplicateTag {
	private List categories;
	private List lines;

	/**
	 * 添加子标签Vline
	 * 
	 * @param line
	 */
	public void addVLine(VLine line) {
		if (lines == null)
			lines = new ArrayList();
		lines.add(line);
	}

	/**
	 * 添加子标签Category
	 * 
	 * @param category
	 */
	public Categories addCategory(Category category) {
		if (categories == null)
			categories = new ArrayList();
		categories.add(category);
		return this;
	}

	public FlashChartBase endTag() {
		getStringBuffer().append("</categories>");
		return this;
	}

	/** x轴字体 */
	private String font;
	/** x轴字体大小 */
	private int fontSize;
	/** x轴字体颜色 */
	private String fontColor;
	// 下面配置适用于scatter (XY Plot)/Bubble chart
	/** 垂直线是否有虚线框 */
	private boolean verticalLineDashed;
	/** 垂直线的颜色 */
	private String verticalLineColor;
	/** 垂直线的厚度 */
	private int verticalLineThickness;
	/** 垂直线的透明度0-100 */
	private int verticalLineAlpha;
	/** 垂直线虚线的长度 */
	private int verticalLineDashGap;
	/** 垂直线虚线之间的长度 */
	private int verticalLineDashLen;

	public String getFont() {
		return font;
	}

	public String toString() {
		start();
		if (getRegisterAtrributes().contains("font"))
			this.add("font =").addValue(font);
		if (getRegisterAtrributes().contains("fontSize"))
			this.add("fontSize =").addValue(fontSize);
		if (getRegisterAtrributes().contains("fontColor"))
			this.add("fontColor =").addValue(fontColor);
		if (getRegisterAtrributes().contains("verticalLineDashed"))
			this.add("verticalLineDashed =").addValue(verticalLineDashed);
		if (getRegisterAtrributes().contains("verticalLineColor"))
			this.add("verticalLineColor =").addValue(verticalLineColor);
		if (getRegisterAtrributes().contains("verticalLineThickness"))
			this.add("verticalLineThickness =").addValue(verticalLineThickness);
		if (getRegisterAtrributes().contains("verticalLineAlpha"))
			this.add("verticalLineAlpha =").addValue(verticalLineAlpha);
		if (getRegisterAtrributes().contains("verticalLineDashGap"))
			this.add("verticalLineDashGap =").addValue(verticalLineDashGap);
		if (getRegisterAtrributes().contains("verticalLineDashLen"))
			this.add("verticalLineDashLen =").addValue(verticalLineDashLen);
		end();
		if (categories != null) {
			for (int i = 0, j = categories.size(); i < j; i++) {
				this.add(categories.get(i));
			}
		}
		endTag();
		return this.getString();
	}

	public Categories setFont(String font) {
		getRegisterAtrributes().add("font");
		this.font = font;
		return this;
	}

	public int getFontSize() {
		return fontSize;
	}

	public Categories setFontSize(int fontSize) {
		getRegisterAtrributes().add("fontSize");
		this.fontSize = fontSize;
		return this;
	}

	public String getFontColor() {
		return fontColor;
	}

	public Categories setFontColor(String fontColor) {
		getRegisterAtrributes().add("fontColor");
		this.fontColor = fontColor;
		return this;
	}

	public FlashChartBase end() {
		getStringBuffer().append(">");
		return this;
	}

	public FlashChartBase start() {
		getStringBuffer().append("<categories ");
		return this;
	}

	public boolean isVerticalLineDashed() {
		return verticalLineDashed;
	}

	public Categories setVerticalLineDashed(boolean verticalLineDashed) {
		this.verticalLineDashed = verticalLineDashed;
		getRegisterAtrributes().add("verticalLineDashed");
		return this;
	}

	public String getVerticalLineColor() {
		return verticalLineColor;
	}

	public Categories setVerticalLineColor(String verticalLineColor) {
		this.verticalLineColor = verticalLineColor;
		getRegisterAtrributes().add("verticalLineColor");
		return this;
	}

	public int getVerticalLineThickness() {
		return verticalLineThickness;
	}

	public Categories setVerticalLineThickness(int verticalLineThickness) {
		this.verticalLineThickness = verticalLineThickness;
		getRegisterAtrributes().add("verticalLineThickness");
		return this;
	}

	public int getVerticalLineAlpha() {
		return verticalLineAlpha;
	}

	public Categories setVerticalLineAlpha(int verticalLineAlpha) {
		this.verticalLineAlpha = verticalLineAlpha;
		getRegisterAtrributes().add("verticalLineAlpha");
		return this;
	}

	public int getVerticalLineDashGap() {
		return verticalLineDashGap;
	}

	public Categories setVerticalLineDashGap(int verticalLineDashGap) {
		this.verticalLineDashGap = verticalLineDashGap;
		getRegisterAtrributes().add("verticalLineDashGap");
		return this;
	}

	public int getVerticalLineDashLen() {
		return verticalLineDashLen;
	}

	public Categories setVerticalLineDashLen(int verticalLineDashLen) {
		this.verticalLineDashLen = verticalLineDashLen;
		getRegisterAtrributes().add("verticalLineDashLen");
		return this;
	}

}
