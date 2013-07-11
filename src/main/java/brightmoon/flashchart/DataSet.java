package brightmoon.flashchart;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DataSet
 * @Description: DataSet标签
 * @author 419723443@qq.com
 * @date Jan 21, 2010 3:31:04 PM
 * 
 */
public class DataSet extends FlashChartBase implements IBaseTag, IComplicateTag {
	private List sets;
	private List lines;

	/**
	 * 添加子标签Vline
	 * 
	 * @param line
	 */
	public DataSet addVLine(VLine line) {
		if (lines == null)
			lines = new ArrayList();
		lines.add(line);
		return this;
	}

	/**
	 * 添加子标签set
	 * 
	 * @param set
	 */
	public DataSet addSet(Data set) {
		if (sets == null)
			sets = new ArrayList();
		sets.add(set);
		return this;
	}

	public FlashChartBase endTag() {
		getStringBuffer().append("</dataset>");
		return this;
	}

	/** 在MSCombi3D中可以设置渲染的样式,有三种选择 */
	private RenderAs renderAs;
	/** 指定类型的名称 */
	private String seriesName;
	/** 颜色(Hex Code ) */
	private String color;
	/** 设置透明度(0-100) */
	private String alpha;
	/** 设置颜色渐变 */
	private String ratio;
	/** 是否显示值 */
	private boolean showValues;
	/** 是否设置虚线框 */
	private boolean dashed;
	/** 是否在图片里面显示名称 */
	private boolean includeInLegend;
	// 下面在Scatter.swf中有
	/** 是否有锚点 */
	private boolean drawAnchors;
	/** 是否画线 */
	private boolean drawLine;
	/** 是否有虚线框 */
	private boolean lineDashed;
	/** 锚点的边 */
	private int anchorSides;
	/** 锚点的半径 */
	private int anchorRadius;
	/** 锚点边界的厚度 */
	private int anchorBorderThickness;
	/** 锚点的透明度 */
	private int anchorAlpha;
	/** 锚点的背景透明度 */
	private int anchorBgAlpha;
	/** 线的透明度 */
	private int lineAlpha;
	/** 线的厚度 */
	private int lineThickness;
	/** 线的虚线长度 */
	private int lineDashLen;
	/** 线的虚线的空白间隔 */
	private int lineDashGap;
	/** 锚点边界颜色 */
	private String anchorBorderColor;
	/** 锚点背景色 */
	private String anchorBgColor;
	/** 线的颜色 */
	private String lineColor;
	
	//新增加的属性
	private String parentYAxis;
	private String parentXAxis;
	
	public String getParentYAxis() {
		return parentYAxis;
	}

	public DataSet setParentYAxis(String parentYAxis) {
		this.parentYAxis = parentYAxis;
		getRegisterAtrributes().add("parentYAxis");
		return this;
	}

	public String getParentXAxis() {
		return parentXAxis;
	}

	public DataSet setParentXAxis(String parentXAxis) {
		getRegisterAtrributes().add("parentXAxis");
		this.parentXAxis = parentXAxis;
		return this;
	}

	public String toString() {
		start();
		if (getRegisterAtrributes().contains("seriesName"))
			this.add("seriesName=").addValue(seriesName);
		if (getRegisterAtrributes().contains("parentYAxis"))
			this.add("parentYAxis=").addValue(parentYAxis);
		if (getRegisterAtrributes().contains("parentXAxis"))
			this.add("parentXAxis=").addValue(parentXAxis);
		if (getRegisterAtrributes().contains("color"))
			this.add("color=").addValue(color);
		if (getRegisterAtrributes().contains("alpha"))
			this.add("alpha=").addValue(alpha);
		if (getRegisterAtrributes().contains("ratio"))
			this.add("ratio=").addValue(ratio);
		if (getRegisterAtrributes().contains("showValues"))
			this.add("showValues=").addValue(showValues);
		if (getRegisterAtrributes().contains("dashed"))
			this.add("dashed=").addValue(dashed);
		if (getRegisterAtrributes().contains("renderAs"))
			this.add("renderAs=").addValue(renderAs);
		if (getRegisterAtrributes().contains("includeInLegend"))
			this.add("includeInLegend=").addValue(includeInLegend);
		if (getRegisterAtrributes().contains("drawAnchors"))
			this.add("drawAnchors=").addValue(drawAnchors);
		if (getRegisterAtrributes().contains("drawLine"))
			this.add("drawLine=").addValue(drawLine);
		if (getRegisterAtrributes().contains("lineDashed"))
			this.add("lineDashed=").addValue(lineDashed);
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
		if (getRegisterAtrributes().contains("lineAlpha"))
			this.add("lineAlpha=").addValue(lineAlpha);
		if (getRegisterAtrributes().contains("lineThickness"))
			this.add("lineThickness=").addValue(lineThickness);
		if (getRegisterAtrributes().contains("lineDashLen"))
			this.add("lineDashLen=").addValue(lineDashLen);
		if (getRegisterAtrributes().contains("lineDashGap"))
			this.add("lineDashGap=").addValue(lineDashGap);
		if (getRegisterAtrributes().contains("anchorBorderColor"))
			this.add("anchorBorderColor=").addValue(anchorBorderColor);
		if (getRegisterAtrributes().contains("anchorBgColor"))
			this.add("anchorBgColor=").addValue(anchorBgColor);
		if (getRegisterAtrributes().contains("lineColor"))
			this.add("lineColor=").addValue(lineColor);
		end();
		if (sets != null) {
			for (int i = 0, j = sets.size(); i < j; i++) {
				this.add(sets.get(i));
			}
		}
		if (lines != null) {
			for (int i = 0, j = lines.size(); i < j; i++) {
				this.add(lines.get(i));
			}
		}
		return this.endTag().getString();
	}

	public FlashChartBase end() {
		getStringBuffer().append(">");
		return this;
	}

	public FlashChartBase start() {
		getStringBuffer().append("<dataset ");
		return this;
	}

	public List getSets() {
		return sets;
	}

	public DataSet setSets(List sets) {
		this.sets = sets;
		getRegisterAtrributes().add("sets");
		return this;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public DataSet setSeriesName(String seriesName) {
		this.seriesName = seriesName;
		getRegisterAtrributes().add("seriesName");
		return this;
	}

	public String getColor() {
		return color;
	}

	public DataSet setColor(String color) {
		this.color = color;
		getRegisterAtrributes().add("color");
		return this;
	}

	public String getAlpha() {
		return alpha;
	}

	public DataSet setAlpha(String alpha) {
		this.alpha = alpha;
		getRegisterAtrributes().add("alpha");
		return this;
	}

	public String getRatio() {
		return ratio;
	}

	public DataSet setRatio(String ratio) {
		this.ratio = ratio;
		getRegisterAtrributes().add("ratio");
		return this;
	}

	public boolean isShowValues() {
		return showValues;
	}

	public DataSet setShowValues(boolean showValues) {
		this.showValues = showValues;
		getRegisterAtrributes().add("showValues");
		return this;
	}

	public boolean isDashed() {
		return dashed;
	}

	public DataSet setDashed(boolean dashed) {
		this.dashed = dashed;
		getRegisterAtrributes().add("dashed");
		return this;
	}

	public boolean isIncludeInLegend() {
		return includeInLegend;
	}

	public DataSet setIncludeInLegend(boolean includeInLegend) {
		this.includeInLegend = includeInLegend;
		getRegisterAtrributes().add("includeInLegend");
		return this;
	}

	public RenderAs getRenderAs() {
		return renderAs;
	}

	public DataSet setRenderAs(RenderAs renderAs) {
		getRegisterAtrributes().add("renderAs");
		this.renderAs = renderAs;
		return this;
	}

	public boolean isDrawAnchors() {
		return drawAnchors;
	}

	public DataSet setDrawAnchors(boolean drawAnchors) {
		this.drawAnchors = drawAnchors;
		getRegisterAtrributes().add("drawAnchors");
		return this;
	}

	public boolean isDrawLine() {
		return drawLine;
	}

	public DataSet setDrawLine(boolean drawLine) {
		getRegisterAtrributes().add("drawLine");
		this.drawLine = drawLine;
		return this;
	}

	public boolean isLineDashed() {
		return lineDashed;
	}

	public DataSet setLineDashed(boolean lineDashed) {
		getRegisterAtrributes().add("lineDashed");
		this.lineDashed = lineDashed;
		return this;
	}

	public int getAnchorSides() {
		return anchorSides;
	}

	public DataSet setAnchorSides(int anchorSides) {
		getRegisterAtrributes().add("anchorSides");
		this.anchorSides = anchorSides;
		return this;
	}

	public int getAnchorRadius() {
		return anchorRadius;
	}

	public DataSet setAnchorRadius(int anchorRadius) {
		getRegisterAtrributes().add("anchorRadius");
		this.anchorRadius = anchorRadius;
		return this;
	}

	public int getAnchorBorderThickness() {
		return anchorBorderThickness;
	}

	public DataSet setAnchorBorderThickness(int anchorBorderThickness) {
		this.anchorBorderThickness = anchorBorderThickness;
		getRegisterAtrributes().add("anchorBorderThickness");
		return this;
	}

	public int getAnchorAlpha() {
		return anchorAlpha;
	}

	public DataSet setAnchorAlpha(int anchorAlpha) {
		getRegisterAtrributes().add("anchorAlpha");
		this.anchorAlpha = anchorAlpha;
		return this;
	}

	public int getAnchorBgAlpha() {
		return anchorBgAlpha;
	}

	public DataSet setAnchorBgAlpha(int anchorBgAlpha) {
		getRegisterAtrributes().add("anchorBgAlpha");
		this.anchorBgAlpha = anchorBgAlpha;
		return this;
	}

	public int getLineAlpha() {
		return lineAlpha;
	}

	public DataSet setLineAlpha(int lineAlpha) {
		this.lineAlpha = lineAlpha;
		getRegisterAtrributes().add("lineAlpha");
		return this;
	}

	public int getLineThickness() {
		return lineThickness;
	}

	public DataSet setLineThickness(int lineThickness) {
		this.lineThickness = lineThickness;
		getRegisterAtrributes().add("lineThickness");
		return this;
	}

	public int getLineDashLen() {
		return lineDashLen;
	}

	public DataSet setLineDashLen(int lineDashLen) {
		this.lineDashLen = lineDashLen;
		getRegisterAtrributes().add("lineDashLen");
		return this;
	}

	public int getLineDashGap() {
		return lineDashGap;
	}

	public DataSet setLineDashGap(int lineDashGap) {
		this.lineDashGap = lineDashGap;
		getRegisterAtrributes().add("lineDashGap");
		return this;
	}

	public String getAnchorBorderColor() {
		return anchorBorderColor;
	}

	public DataSet setAnchorBorderColor(String anchorBorderColor) {
		this.anchorBorderColor = anchorBorderColor;
		getRegisterAtrributes().add("anchorBorderColor");
		return this;
	}

	public String getAnchorBgColor() {
		return anchorBgColor;
	}

	public DataSet setAnchorBgColor(String anchorBgColor) {
		getRegisterAtrributes().add("anchorBgColor");
		this.anchorBgColor = anchorBgColor;
		return this;
	}

	public String getLineColor() {
		return lineColor;
	}

	public DataSet setLineColor(String lineColor) {
		getRegisterAtrributes().add("lineColor");
		this.lineColor = lineColor;
		return this;
	}
}
