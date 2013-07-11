package brightmoon.flashchart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: Chart
 * @Description:chart标签类
 * @author 419723443@qq.com
 * @date Jan 20, 2010 4:00:27 PM
 * 
 */
public class Chart extends FlashChartBase implements IBaseTag, IComplicateTag {
	private List datas;
	private List dataSets;
	private List categorieses;
	private List trendLines;
	private List vTrendLines;
	private List lines;
	private List styleses;
	private Set registerAtrributes = new HashSet();

	/**
	 * 添加子标签VLine
	 * 
	 * @param line
	 */
	public Chart addVLine(VLine line) {
		if (lines == null)
			lines = new ArrayList();
		lines.add(line);
		return this;
	}

	/**
	 * 添加子标签Styles
	 * 
	 * @param styles
	 */
	public Chart addStyles(Styles styles) {
		if (styleses == null)
			styleses = new ArrayList();
		styleses.add(styles);
		return this;
	}

	/**
	 * 添加子标签DataSet
	 * 
	 * @param dataSet
	 */
	public Chart addDataSet(DataSet dataSet) {
		if (dataSets == null)
			dataSets = new ArrayList();
		dataSets.add(dataSet);
		return this;
	}

	/**
	 * 添加标签TrendLines
	 * 
	 * @param lines
	 */
	public Chart addTrendLines(TrendLines lines) {
		if (trendLines == null)
			trendLines = new ArrayList();
		trendLines.add(lines);
		return this;
	}

	/**
	 * 添加标签VTrendLines
	 * 
	 * @param lines
	 */
	public Chart addVTrendLines(VTrendLines lines) {
		if (vTrendLines == null)
			vTrendLines = new ArrayList();
		vTrendLines.add(lines);
		return this;
	}

	/**
	 * 添加子标签Categories
	 * 
	 * @param categories
	 */
	public Chart addCategories(Categories categories) {
		if (categorieses == null)
			categorieses = new ArrayList();
		categorieses.add(categories);
		return this;
	}

	/**
	 * 添加子标签:set
	 * 
	 * @param set
	 */
	public Chart addSet(Data set) {
		if (datas == null)
			datas = new ArrayList();
		datas.add(set);
		return this;
	}

	public FlashChartBase endTag() {
		getStringBuffer().append("</chart>");
		return this;
	}

	public FlashChartBase end() {
		getStringBuffer().append(">");
		return this;
	}

	public FlashChartBase start() {
		getStringBuffer().append("<chart ");
		return this;
	}

	public String toString() {
		start();
		if (registerAtrributes.contains("showZeroPies"))
			this.add("showZeroPies=").addValue(showZeroPies);
		if (registerAtrributes.contains("showPercentValues"))
			this.add("showPercentValues=").addValue(showPercentValues);
		if (registerAtrributes.contains("showPercentInToolTip"))
			this.add("showPercentInToolTip=").addValue(showPercentInToolTip);
		if (registerAtrributes.contains("showValues"))
			this.add("showValues=").addValue(showValues);
		if (registerAtrributes.contains("labelSepChar"))
			this.add("labelSepChar=").addValue(labelSepChar);		
		if (registerAtrributes.contains("bgAngle"))
			this.add("bgAngle =").addValue(bgAngle);
		if (registerAtrributes.contains("showLabels"))
			this.add("showLabels =").addValue(showLabels);
		if (registerAtrributes.contains("logoURL"))
			this.add("logoURL =").addValue(logoURL);
		if (registerAtrributes.contains("bgAlpha"))
			this.add("bgAlpha =").addValue(bgAlpha);
		if (registerAtrributes.contains("yAxisName"))
			this.add("yAxisName =").addValue(yAxisName);
		if (registerAtrributes.contains("formatNumberScale"))
			this.add("formatNumberScale =").addValue(formatNumberScale);
		if (registerAtrributes.contains("logoLink"))
			this.add("logoLink =").addValue(logoLink);
		if (registerAtrributes.contains("showLimits"))
			this.add("showLimits =").addValue(showLimits);
		if (registerAtrributes.contains("subCaption"))
			this.add("subCaption =").addValue(subCaption);
		if (registerAtrributes.contains("defaultAnimation"))
			this.add("defaultAnimation =").addValue(defaultAnimation);
		if (registerAtrributes.contains("categories"))
			this.add("categories =").addValue(categories);
		if (registerAtrributes.contains("showShadow"))
			this.add("showShadow =").addValue(showShadow);
		if (registerAtrributes.contains("showNames"))
			this.add("showNames =").addValue(showNames);
		if (registerAtrributes.contains("showAboutMenuItem"))
			this.add("showAboutMenuItem =").addValue(showAboutMenuItem);
		if (registerAtrributes.contains("caption"))
			this.add("caption =").addValue(caption);
		if (registerAtrributes.contains("showDivLineValues"))
			this.add("showDivLineValues =").addValue(showDivLineValues);
		if (registerAtrributes.contains("rotateLabels"))
			this.add("rotateLabels =").addValue(rotateLabels);
		if (registerAtrributes.contains("animation"))
			this.add("animation =").addValue(animation);
		if (registerAtrributes.contains("yAxisMinValue"))
			this.add("yAxisMinValue =").addValue(yAxisMinValue);
		if (registerAtrributes.contains("slantLabels"))
			this.add("slantLabels =").addValue(slantLabels);
		if (registerAtrributes.contains("bgColor"))
			this.add("bgColor =").addValue(bgColor);
		if (registerAtrributes.contains("showVlaues"))
			this.add("showVlaues =").addValue(showVlaues);
		if (registerAtrributes.contains("labelStep"))
			this.add("labelStep =").addValue(labelStep);
		if (registerAtrributes.contains("showYAxisValues"))
			this.add("showYAxisValues =").addValue(showYAxisValues);
		if (registerAtrributes.contains("rotateYAxisName"))
			this.add("rotateYAxisName =").addValue(rotateYAxisName);
		if (registerAtrributes.contains("borderThickness"))
			this.add("borderThickness =").addValue(borderThickness);
		if (registerAtrributes.contains("bgRatio"))
			this.add("bgRatio =").addValue(bgRatio);
		if (registerAtrributes.contains("xAxisName"))
			this.add("xAxisName =").addValue(xAxisName);
		if (registerAtrributes.contains("palette"))
			this.add("palette =").addValue(palette);
		if (registerAtrributes.contains("borderColor"))
			this.add("borderColor =").addValue(borderColor);
		if (registerAtrributes.contains("aboutMenuItemLink"))
			this.add("aboutMenuItemLink =").addValue(aboutMenuItemLink);
		if (registerAtrributes.contains("rotateValues"))
			this.add("rotateValues =").addValue(rotateValues);
		if (registerAtrributes.contains("yAxisMaxValue"))
			this.add("yAxisMaxValue =").addValue(yAxisMaxValue);
		if (registerAtrributes.contains("yAxisNameWidth"))
			this.add("yAxisNameWidth =").addValue(yAxisNameWidth);
		if (registerAtrributes.contains("showBorder"))
			this.add("showBorder =").addValue(showBorder);
		if (registerAtrributes.contains("paletteColors"))
			this.add("paletteColors =").addValue(paletteColors);
		if (registerAtrributes.contains("placeValuesInside"))
			this.add("placeValuesInside =").addValue(placeValuesInside);
		if (registerAtrributes.contains("baseFontSize"))
			this.add("baseFontSize =").addValue(baseFontSize);
		if (registerAtrributes.contains("aboutMenuItemLabel"))
			this.add("aboutMenuItemLabel=").addValue(aboutMenuItemLabel);
		if (registerAtrributes.contains("clickURL"))
			this.add("clickURL =").addValue(clickURL);
		if (registerAtrributes.contains("dataSet"))
			this.add("dataSet =").addValue(dataSet);
		if (registerAtrributes.contains("setAdaptiveYMin"))
			this.add("setAdaptiveYMin =").addValue(setAdaptiveYMin);
		if (registerAtrributes.contains("staggerLines"))
			this.add("staggerLines =").addValue(staggerLines);
		if (registerAtrributes.contains("yAxisValuesStep"))
			this.add("yAxisValuesStep =").addValue(yAxisValuesStep);
		if (registerAtrributes.contains("logoPosition"))
			this.add("logoPosition=").addValue(logoPosition);
		if (registerAtrributes.contains("is2D"))
			this.add("is2D=").addValue(is2D);
		if (registerAtrributes.contains("clustered"))
			this.add("clustered=").addValue(clustered);
		if (registerAtrributes.contains("chartOrder"))
			this.add("chartOrder=").addValue(chartOrder);
		if (registerAtrributes.contains("chartOnTop"))
			this.add("chartOnTop=").addValue(chartOnTop);
		if (registerAtrributes.contains("autoScaling"))
			this.add("autoScaling=").addValue(autoScaling);
		if (registerAtrributes.contains("allowScaling"))
			this.add("allowScaling=").addValue(allowScaling);
		if (registerAtrributes.contains("startAngX"))
			this.add("startAngX=").addValue(startAngX);
		if (registerAtrributes.contains("startAngY"))
			this.add("startAngY=").addValue(startAngY);
		if (registerAtrributes.contains("endAngX"))
			this.add("endAngX=").addValue(endAngX);
		if (registerAtrributes.contains("endAngY"))
			this.add("endAngY=").addValue(endAngY);
		if (registerAtrributes.contains("cameraAngX"))
			this.add("cameraAngX=").addValue(cameraAngX);
		if (registerAtrributes.contains("cameraAngY"))
			this.add("cameraAngY=").addValue(cameraAngY);
		if (registerAtrributes.contains("lightAngX"))
			this.add("lightAngX=").addValue(lightAngX);
		if (registerAtrributes.contains("lightAngY"))
			this.add("lightAngY=").addValue(lightAngY);
		if (registerAtrributes.contains("intensity"))
			this.add("intensity=").addValue(intensity);
		if (registerAtrributes.contains("bright2D"))
			this.add("bright2D=").addValue(bright2D);
		if (registerAtrributes.contains("allowRotation"))
			this.add("allowRotation=").addValue(allowRotation);
		if (registerAtrributes.contains("constrainVerticalRotation"))
			this.add("constrainVerticalRotation=").addValue(
					constrainVerticalRotation);
		if (registerAtrributes.contains("minVerticalRotAngle"))
			this.add("minVerticalRotAngle=").addValue(minVerticalRotAngle);
		if (registerAtrributes.contains("maxVerticalRotAngle"))
			this.add("maxVerticalRotAngle=").addValue(maxVerticalRotAngle);
		if (registerAtrributes.contains("constrainHorizontalRotation"))
			this.add("constrainHorizontalRotation=").addValue(
					constrainHorizontalRotation);
		if (registerAtrributes.contains("minHorizontalRotAngle"))
			this.add("minHorizontalRotAngle=").addValue(minHorizontalRotAngle);
		if (registerAtrributes.contains("maxHorizontalRotAngle"))
			this.add("maxHorizontalRotAngle=").addValue(maxHorizontalRotAngle);
		if (registerAtrributes.contains("zDepth"))
			this.add("zDepth=").addValue(zDepth);
		if (registerAtrributes.contains("zGapPlot"))
			this.add("zGapPlot=").addValue(zGapPlot);
		if (registerAtrributes.contains("showPlotBorder"))
			this.add("showPlotBorder=").addValue(showPlotBorder);
		if (registerAtrributes.contains("yzWallDepth"))
			this.add("yzWallDepth=").addValue(yzWallDepth);
		if (registerAtrributes.contains("zxWallDepth"))
			this.add("zxWallDepth=").addValue(zxWallDepth);
		if (registerAtrributes.contains("xyWallDepth"))
			this.add("xyWallDepth=").addValue(xyWallDepth);
		if (registerAtrributes.contains("numDivLines"))
			this.add("numDivLines=").addValue(numDivLines);
		if (registerAtrributes.contains("divLineThickness"))
			this.add("divLineThickness=").addValue(divLineThickness);
		if (registerAtrributes.contains("divLineAlpha"))
			this.add("divLineAlpha=").addValue(divLineAlpha);
		if (registerAtrributes.contains("zeroPlaneAlpha"))
			this.add("zeroPlaneAlpha=").addValue(zeroPlaneAlpha);
		if (registerAtrributes.contains("zeroPlaneMesh"))
			this.add("zeroPlaneMesh=").addValue(zeroPlaneMesh);
		if (registerAtrributes.contains("showAlternateHGridColor"))
			this.add("showAlternateHGridColor=").addValue(
					showAlternateHGridColor);
		if (registerAtrributes.contains("divLineEffect"))
			this.add("divLineEffect=").addValue(divLineEffect);
		if (registerAtrributes.contains("divLineColor"))
			this.add("divLineColor=").addValue(divLineColor);
		if (registerAtrributes.contains("zeroPlaneColor"))
			this.add("zeroPlaneColor=").addValue(zeroPlaneColor);
		if (registerAtrributes.contains("alternateHGridColor"))
			this.add("alternateHGridColor=").addValue(alternateHGridColor);
		if (registerAtrributes.contains("showLegend"))
			this.add("showLegend=").addValue(showLegend);
		if (registerAtrributes.contains("interactiveLegend"))
			this.add("interactiveLegend=").addValue(interactiveLegend);
		if (registerAtrributes.contains("legendMarkerCircle"))
			this.add("legendMarkerCircle=").addValue(legendMarkerCircle);
		if (registerAtrributes.contains("legendShadow"))
			this.add("legendShadow=").addValue(legendShadow);
		if (registerAtrributes.contains("legendAllowDrag"))
			this.add("legendAllowDrag=").addValue(legendAllowDrag);
		if (registerAtrributes.contains("legendPosition"))
			this.add("legendPosition=").addValue(legendPosition);
		if (registerAtrributes.contains("legendCaption"))
			this.add("legendCaption=").addValue(legendCaption);
		if (registerAtrributes.contains("legendBgColor"))
			this.add("legendBgColor=").addValue(legendBgColor);
		if (registerAtrributes.contains("legendBorderColor"))
			this.add("legendBorderColor=").addValue(legendBorderColor);
		if (registerAtrributes.contains("legendScrollBgColor"))
			this.add("legendScrollBgColor=").addValue(legendScrollBgColor);
		if (registerAtrributes.contains("legendScrollBarColor"))
			this.add("legendScrollBarColor=").addValue(legendScrollBarColor);
		if (registerAtrributes.contains("legendScrollBtnColor"))
			this.add("legendScrollBtnColor=").addValue(legendScrollBtnColor);
		if (registerAtrributes.contains("legendBgAlpha"))
			this.add("legendBgAlpha=").addValue(legendBgAlpha);
		if (registerAtrributes.contains("legendBorderThickness"))
			this.add("legendBorderThickness=").addValue(legendBorderThickness);
		if (registerAtrributes.contains("legendBorderAlpha"))
			this.add("legendBorderAlpha=").addValue(legendBorderAlpha);
		if (registerAtrributes.contains("formatNumber"))
			this.add("formatNumber=").addValue(formatNumber);
		if (registerAtrributes.contains("forceDecimals"))
			this.add("forceDecimals=").addValue(forceDecimals);
		if (registerAtrributes.contains("defaultNumberScale"))
			this.add("defaultNumberScale=").addValue(defaultNumberScale);
		if (registerAtrributes.contains("inThousandSeparator"))
			this.add("inThousandSeparator=").addValue(inThousandSeparator);
		if (registerAtrributes.contains("numberScaleUnit"))
			this.add("numberScaleUnit=").addValue(numberScaleUnit);
		if (registerAtrributes.contains("numberScaleValue"))
			this.add("numberScaleValue=").addValue(numberScaleValue);
		if (registerAtrributes.contains("numberPrefix"))
			this.add("numberPrefix=").addValue(numberPrefix);
		if (registerAtrributes.contains("numberSuffix"))
			this.add("numberSuffix=").addValue(numberSuffix);
		if (registerAtrributes.contains("decimalSeparator"))
			this.add("decimalSeparator=").addValue(decimalSeparator);
		if (registerAtrributes.contains("thousandSeparator"))
			this.add("thousandSeparator=").addValue(thousandSeparator);
		if (registerAtrributes.contains("inDecimalSeparator"))
			this.add("inDecimalSeparator=").addValue(inDecimalSeparator);
		if (registerAtrributes.contains("decimals"))
			this.add("decimals=").addValue(decimals);
		if (registerAtrributes.contains("yAxisValueDecimals"))
			this.add("yAxisValueDecimals=").addValue(yAxisValueDecimals);
		if (registerAtrributes.contains("baseFont"))
			this.add("baseFont=").addValue(baseFont);
		if (registerAtrributes.contains("baseFontColor"))
			this.add("baseFontColor=").addValue(baseFontColor);
		if (registerAtrributes.contains("outCnvBaseFont"))
			this.add("outCnvBaseFont=").addValue(outCnvBaseFont);
		if (registerAtrributes.contains("outCnvBaseFontColor"))
			this.add("outCnvBaseFontColor=").addValue(outCnvBaseFontColor);
		if (registerAtrributes.contains("outCnvBaseFontSize"))
			this.add("outCnvBaseFontSize=").addValue(outCnvBaseFontSize);
		if (registerAtrributes.contains("baseFontSize"))
			this.add("baseFontSize=").addValue(baseFontSize);
		if (registerAtrributes.contains("showToolTip"))
			this.add("showToolTip=").addValue(showToolTip);
		if (registerAtrributes.contains("seriesNameInToolTip"))
			this.add("seriesNameInToolTip=").addValue(seriesNameInToolTip);
		if (registerAtrributes.contains("showToolTipShadow"))
			this.add("showToolTipShadow=").addValue(showToolTipShadow);
		if (registerAtrributes.contains("toolTipBgColor"))
			this.add("toolTipBgColor=").addValue(toolTipBgColor);
		if (registerAtrributes.contains("dynamicShading"))
			this.add("dynamicShading=").addValue(dynamicShading);
		if (registerAtrributes.contains("toolTipSepChar"))
			this.add("toolTipSepChar=").addValue(toolTipSepChar);
		if (registerAtrributes.contains("toolTipBorderColor"))
			this.add("toolTipBorderColor=").addValue(toolTipBorderColor);
		if (registerAtrributes.contains("captionPadding"))
			this.add("captionPadding=").addValue(captionPadding);
		if (registerAtrributes.contains("xAxisNamePadding"))
			this.add("xAxisNamePadding=").addValue(xAxisNamePadding);
		if (registerAtrributes.contains("yAxisNamePadding"))
			this.add("yAxisNamePadding=").addValue(yAxisNamePadding);
		if (registerAtrributes.contains("yAxisValuesPadding"))
			this.add("yAxisValuesPadding=").addValue(yAxisValuesPadding);
		if (registerAtrributes.contains("labelPadding"))
			this.add("labelPadding=").addValue(labelPadding);
		if (registerAtrributes.contains("valuePadding"))
			this.add("valuePadding=").addValue(valuePadding);
		if (registerAtrributes.contains("plotSpacePercent"))
			this.add("plotSpacePercent=").addValue(plotSpacePercent);
		if (registerAtrributes.contains("chartLeftMargin"))
			this.add("chartLeftMargin=").addValue(chartLeftMargin);
		if (registerAtrributes.contains("chartRightMargin"))
			this.add("chartRightMargin=").addValue(chartRightMargin);
		if (registerAtrributes.contains("chartTopMargin"))
			this.add("chartTopMargin=").addValue(chartTopMargin);
		if (registerAtrributes.contains("chartBottomMargin"))
			this.add("chartBottomMargin=").addValue(chartBottomMargin);
		if (registerAtrributes.contains("canvasPadding"))
			this.add("canvasPadding=").addValue(canvasPadding);
		if (registerAtrributes.contains("canvasLeftMargin"))
			this.add("canvasLeftMargin=").addValue(canvasLeftMargin);
		if (registerAtrributes.contains("canvasRightMargin"))
			this.add("canvasRightMargin=").addValue(canvasRightMargin);
		if (registerAtrributes.contains("canvasTopMargin"))
			this.add("canvasTopMargin=").addValue(canvasTopMargin);
		if (registerAtrributes.contains("canvasBottomMargin"))
			this.add("canvasBottomMargin=").addValue(canvasBottomMargin);
		if (registerAtrributes.contains("drawAnchors"))
			this.add("drawAnchors=").addValue(drawAnchors);
		if (registerAtrributes.contains("anchorSides"))
			this.add("anchorSides=").addValue(anchorSides);
		if (registerAtrributes.contains("anchorRadius"))
			this.add("anchorRadius=").addValue(anchorRadius);
		if (registerAtrributes.contains("anchorBorderThickness"))
			this.add("anchorBorderThickness=").addValue(anchorBorderThickness);
		if (registerAtrributes.contains("anchorAlpha"))
			this.add("anchorAlpha=").addValue(anchorAlpha);
		if (registerAtrributes.contains("anchorBgAlpha"))
			this.add("anchorBgAlpha=").addValue(anchorBgAlpha);
		if (registerAtrributes.contains("anchorBorderColor"))
			this.add("anchorBorderColor=").addValue(anchorBorderColor);
		if (registerAtrributes.contains("anchorBgColor"))
			this.add("anchorBgColor=").addValue(anchorBgColor);
		if (registerAtrributes.contains("divLineIsDashed"))
			this.add("divLineIsDashed=").addValue(divLineIsDashed);
		if (registerAtrributes.contains("vDivLineIsDashed"))
			this.add("vDivLineIsDashed=").addValue(vDivLineIsDashed);
		if (registerAtrributes.contains("showAlternateVGridColor"))
			this.add("showAlternateVGridColor=").addValue(
					showAlternateVGridColor);
		if (registerAtrributes.contains("divLineDashLen"))
			this.add("divLineDashLen=").addValue(divLineDashLen);
		if (registerAtrributes.contains("divLineDashGap"))
			this.add("divLineDashGap=").addValue(divLineDashGap);
		if (registerAtrributes.contains("zeroPlaneThickness"))
			this.add("zeroPlaneThickness=").addValue(zeroPlaneThickness);
		if (registerAtrributes.contains("alternateHGridAlpha"))
			this.add("alternateHGridAlpha=").addValue(alternateHGridAlpha);
		if (registerAtrributes.contains("numVDivLines"))
			this.add("numVDivLines=").addValue(numVDivLines);
		if (registerAtrributes.contains("vDivLineThickness"))
			this.add("vDivLineThickness=").addValue(vDivLineThickness);
		if (registerAtrributes.contains("vDivLineAlpha"))
			this.add("vDivLineAlpha=").addValue(vDivLineAlpha);
		if (registerAtrributes.contains("vDivLineDashLen"))
			this.add("vDivLineDashLen=").addValue(vDivLineDashLen);
		if (registerAtrributes.contains("vDivLineDashGap"))
			this.add("vDivLineDashGap=").addValue(vDivLineDashGap);
		if (registerAtrributes.contains("reverseLegend "))
			this.add("reverseLegend=").addValue(reverseLegend);
		if (registerAtrributes.contains("alternateVGridAlpha"))
			this.add("alternateVGridAlpha=").addValue(alternateVGridAlpha);
		if (registerAtrributes.contains("vDivLineColor"))
			this.add("vDivLineColor=").addValue(vDivLineColor);
		if (registerAtrributes.contains("alternateVGridColor"))
			this.add("alternateVGridColor=").addValue(alternateVGridColor);
		end();
		if (datas != null) {
			for (int i = 0, j = datas.size(); i < j; i++) {
				this.add(datas.get(i));
			}
		}
		if (dataSets != null) {
			for (int i = 0, j = dataSets.size(); i < j; i++) {
				this.add(dataSets.get(i));
			}
		}
		if (trendLines != null) {
			for (int i = 0, j = trendLines.size(); i < j; i++) {
				this.add(trendLines.get(i));
			}
		}
		if (categorieses != null) {
			for (int i = 0, j = categorieses.size(); i < j; i++) {
				this.add(categorieses.get(i));
			}
		}
		if (lines != null) {
			for (int i = 0, j = lines.size(); i < j; i++) {
				this.add(lines.get(i));
			}
		}
		if (vTrendLines != null) {
			for (int i = 0, j = vTrendLines.size(); i < j; i++) {
				this.add(vTrendLines.get(i));
			}
		}
		String str =  this.endTag().getString().replace(">", ">\n"); 
		return str;
	}

	// 下面是Main部分
	/** flash的标题 */
	private String caption;
	/** 副标题 */
	private String subCaption;
	/** x轴描述 */
	private String xAxisName;
	/** y轴描述 */
	private String yAxisName;
	/** 在饼图中如果值为0是否显示出来 */
	private boolean showZeroPies;
	/** 是否在label中显示百分比 */
	private boolean showPercentValues;
	/** 是否显示百分比在提示中出现 */
	private boolean showPercentInToolTip;
	/** 实现显示value */
	private boolean showValues;
	/** 数据和标签的间隔符号 */
	private String labelSepChar;
	// 下面是Function部分
	/** 支持动画 */
	private boolean animation;
	/** 颜色板(1-5) */
	private int palette;
	/** 自定义颜色板的字符串 */
	private String paletteColors;
	/** 是否显示右键菜单 */
	private boolean showAboutMenuItem;
	/** 右键菜单的说明 */
	private String aboutMenuItemLabel;
	/** 右键菜单链接 */
	private String aboutMenuItemLink;
	/** label是否可以旋转 */
	private boolean rotateLabels;
	/** 如果设置了可以旋转,这里就控制是否可以倾斜 */
	private boolean slantLabels;
	/** label跳跃显示的间隔(正整数) */
	private int labelStep;
	/** 设置间隔显示的行背景色(>=2的正整数) */
	private int staggerLines;
	/** 是否显示名字 */
	private boolean showNames;
	/** 是否旋转value */
	private boolean rotateValues;
	/** value标签是否放在列里面 */
	private boolean placeValuesInside;
	/** 是否显示纵坐标 */
	private boolean showYAxisValues;
	/** 是否显示极限值 */
	private boolean showLimits;
	/** 是否显示div线 */
	private boolean showDivLineValues;
	/** 是否显示阴影 */
	private boolean showShadow;
	/** 是否可以旋转纵坐标名 */
	private boolean rotateYAxisName;
	/** 是否默认显示动画效果 */
	private boolean defaultAnimation;
	/** 纵坐标宽度 */
	private int yAxisNameWidth;
	/** 设置纵坐标的最小值(不设置就自动计算最小值) */
	private int yAxisMinValue;
	/** 设置纵坐标的最大值(不设置就自动计算最大值) */
	private int yAxisMaxValue;
	/** 设置纵坐标值最小是不是0 */
	private boolean setAdaptiveYMin;
	/** 设置点击flash的链接 */
	private String clickURL;
	/** 纵坐标值的跳跃值 */
	private int yAxisValuesStep;
	/** 是否显示提示值 */
	private boolean showLabels;
	/** 是否显示数值值 */
	private boolean showVlaues;
	// 下面是Cosmetics部分
	/** 背景颜色 */
	private String bgColor;
	/** 透明度(0-100 ) */
	private int bgAlpha;
	/** 背景颜色的渐变度(0-100 ) */
	private int bgRatio;
	/** 背景角度?(0-360) */
	private int bgAngle;
	/** 是否显示边界 */
	private boolean showBorder;
	/** 边界颜色 */
	private String borderColor;
	/** 边界的厚度 */
	private int borderThickness;
	/** logo图片所在的链接 */
	private String logoURL;
	/** logo位置 */
	private LogoPosition logoPosition;
	/** 点击logo指向的链接地址 */
	private String logoLink;
	/** 种类描述信息 */
	private Categories categories;
	/** 数据集合,可以有多行 */
	private DataSet dataSet;

	// 下面是3D的功能设置部分
	/** 是否画2D图形 */
	private boolean is2D;
	/**
	 * It lets you to set if multicolumn datasets will be rendered in a clusterd
	 * format or manhattan format
	 */
	private boolean clustered;
	/** chart的顺序,例如"column, area, line" */
	private boolean chartOrder;
	/**
	 * It allows the user to decide whether or not the chart canvas will be
	 * placed over the extra chart elements (caption, subcaption, legend).
	 */
	private boolean chartOnTop;
	/** 自动以合适的顺序展示多个chart */
	private boolean autoScaling;
	/** 是否允许放大或缩小(只适合windows操作系统) */
	private boolean allowScaling;
	/** 3D模式下开始展示时显示的X角度.(取值:-360-0-360.默认:30) */
	private int startAngX;
	/** 3D模式下开始展示时显示的Y角度.(取值:-360-0-360.默认:-45) */
	private int startAngY;
	/** 3D模式下结束展示时显示的X角度.(取值:-360-0-360.默认:30) */
	private int endAngX;
	/** 3D模式下结束展示时显示的Y角度.(取值:-360-0-360.默认:-45) */
	private int endAngY;
	/** animat3D=0时,视角所在的x角度(取值:-360-0-360) */
	private int cameraAngX;
	/** animat3D=0时,视角所在的y角度(取值:-360-0-360) */
	private int cameraAngY;
	/** 光线所在的x轴角度(取值:-360-0-360) */
	private int lightAngX;
	/** 光线所在的y轴角度(取值:-360-0-360) */
	private int lightAngY;
	/** 光强度的设置(0-10,默认:2.5) */
	private double intensity;
	/** dynamicShading=0就提供一个静态的光源. */
	private boolean dynamicShading;
	/** dynamicShading=1的时候,设置是否显示为2D模式. */
	private boolean bright2D;
	/** 是否允许互动的旋转 */
	private boolean allowRotation;
	/** 是否强迫用户只可以垂直上下旋转 */
	private boolean constrainVerticalRotation;
	/** 垂直旋转的最小角度(0-360) */
	private int minVerticalRotAngle;
	/** 垂直旋转的最大角度(0-360) */
	private int maxVerticalRotAngle;
	/** 是否强迫用户只可以水平左右旋转 */
	private boolean constrainHorizontalRotation;
	/** 水平旋转最小角度(0-360) */
	private int minHorizontalRotAngle;
	/** 水平旋转最大角度(0-360) */
	private int maxHorizontalRotAngle;
	// 3D Plot Attributes
	/** 设置每一个dataSet的厚度 */
	private int zDepth;
	/** 设置两个图层之间的间隙 */
	private int zGapPlot;
	/** 是否显示列图,饼图的边框 */
	private boolean showPlotBorder;
	// 3D Wall Attributes
	/** 决定y-z轴的深度 */
	private int yzWallDepth;
	/** 决定x-z轴的深度 */
	private int zxWallDepth;
	/** 决定x-y轴的深度 */
	private int xyWallDepth;
	// 下面控制Divisional Lines, Grids & Zero Plane
	/** 横坐标要区分的段数(>0) */
	private int numDivLines;
	/** 分段的厚度(1-5) */
	private int divLineThickness;
	/** Alpha of divisional lines. (0-100) */
	private int divLineAlpha;
	/** Alpha of zero plane.(0-100) */
	private int zeroPlaneAlpha;
	/** Whether to draw the zeroplane as a wireframe mesh or as a filled plane. */
	private boolean zeroPlaneMesh;
	/** 是否水平方向交替以不同颜色显示 */
	private boolean showAlternateHGridColor;
	/** div分段的效果 */
	private DivLineEffect divLineEffect;
	/** 分段的颜色 */
	private String divLineColor;
	/** 底平面的颜色 */
	private String zeroPlaneColor;
	/** 水平面交替出现的颜色 */
	private String alternateHGridColor;
	/** div分割线是不是虚线 */
	private boolean divLineIsDashed;
	/** 垂直分割线是不是虚线 */
	private boolean vDivLineIsDashed;
	/** 是否交替显示分割表格的颜色 */
	private boolean showAlternateVGridColor;
	/** div分割虚线的长度 */
	private int divLineDashLen;
	/** div分割虚线的间隔长度 */
	private int divLineDashGap;
	/** 0平面的厚度 */
	private int zeroPlaneThickness;
	/** 交替水平表格的透明度0-100 */
	private int alternateHGridAlpha;
	/** 垂直分割线的数目 */
	private int numVDivLines;
	/** 垂直分割线的厚度 */
	private int vDivLineThickness;
	/** 垂直分割线的透明度 */
	private int vDivLineAlpha;
	/** 垂直分割线虚线框的长度 */
	private int vDivLineDashLen;
	/** 垂直分割线虚线框之间的间隔 */
	private int vDivLineDashGap;
	/** 交替垂直表格的透明度0-100 */
	private int alternateVGridAlpha;
	/** 垂直分割线的颜色 */
	private String vDivLineColor;
	/** 垂直交替表格的颜色 */
	private String alternateVGridColor;
	// 下面控制Legend Properties
	/**
	 * In combination charts, the series name of each data-set shows up in the
	 * legend of the chart. 下面属性控制是否出现legend
	 */
	private boolean showLegend;
	/** legend是否交互出现 */
	private boolean interactiveLegend;
	/** legend是方形(0,可能是默认)的还是圆形(1) */
	private boolean legendMarkerCircle;
	/** 是否出现阴影 */
	private boolean legendShadow;
	/** 是否允许拖拽 */
	private boolean legendAllowDrag;
	/** legend出现的位置(bottom或者right) */
	private LegendPosition legendPosition;
	/** legend标题 */
	private String legendCaption;
	/** legend背景色 */
	private String legendBgColor;
	/** legend边框色 */
	private String legendBorderColor;
	/** legend滚动条背景色 */
	private String legendScrollBgColor;
	/** legend滚动条的颜色 */
	private String legendScrollBarColor;
	/** legend滚动条按钮颜色 */
	private String legendScrollBtnColor;
	/** legend背景色的透明度(0-100) */
	private int legendBgAlpha;
	/** legend边界厚度 */
	private int legendBorderThickness;
	/** legend边界透明度 */
	private int legendBorderAlpha;
	/** 是否翻转legend */
	private boolean reverseLegend;
	// 下面控制Number 格式
	/** 控制是否要进行数字的格式化显示 */
	private boolean formatNumber;
	/** 是否在数字后面补充0,例如2.4变为2.40 */
	private boolean forceDecimals;
	/** 是否要在数字后面加后缀,K,M等 */
	private boolean formatNumberScale;
	/** 设置默认的数字格式化的单位,可以有:s,bits,inches选择. */
	private DefaultNumberScale defaultNumberScale;
	/** 接受的千分位的字符标示 */
	private String inThousandSeparator;
	/** 设置数字各个单位的字符表示形式 */
	private String numberScaleUnit;
	/** 设置数字各个单位的换算比率,例如'1000,1000,1000'就是千分位表示 */
	private String numberScaleValue;
	/** 数字前缀,例如'$' */
	private String numberPrefix;
	/** 数字后缀 */
	private String numberSuffix;
	/** 小数点符号 */
	private String decimalSeparator;
	/** 千分位符号 */
	private String thousandSeparator;
	/** 接受的小数点的字符标示 */
	private String inDecimalSeparator;
	/** 要显示的小数点位数(0-10) */
	private int decimals;
	/** 设置div分割区的小数点位数(0-10) */
	private int yAxisValueDecimals;
	// 下面控制font
	/** 图表字体 */
	private String baseFont;
	/** 图表字体颜色 */
	private String baseFontColor;
	/** 图表外围字体 */
	private String outCnvBaseFont;
	/** 图表外围字体颜色(0-72) */
	private String outCnvBaseFontColor;
	/** 图表外围字体大小 */
	private int outCnvBaseFontSize;
	/** 文本大小0-72 */
	private int baseFontSize;
	// 下面控制Tool-tip
	/** 是否显示提示 */
	private boolean showToolTip;
	/** 是否显示多层图表中的每层提示 */
	private boolean seriesNameInToolTip;
	/** 是否出现提示的阴影效果 */
	private boolean showToolTipShadow;
	/** 提示背景色 */
	private String toolTipBgColor;
	/** 提示的值和提示信息直接的分割符号 */
	private String toolTipSepChar;
	/** 提示边界色 */
	private String toolTipBorderColor;
	// Chart Padding & Margins
	/** 标题之间的空白大小(单位:像素) */
	private int captionPadding;
	/** x轴底距离图表底的空白大小 */
	private int xAxisNamePadding;
	/**
	 * set the distance between the right end of y-axis title and the start of
	 * y-axis values
	 */
	private int yAxisNamePadding;
	/**
	 * set the horizontal space between the canvas left edge and the y-axis
	 * values or trend line values
	 */
	private int yAxisValuesPadding;
	/** datas the vertical space between the labels and canvas bottom edge */
	private int labelPadding;
	/**
	 * datas the vertical space between the end of columns and start of value
	 * textboxes
	 */
	private int valuePadding;
	/**
	 * On a column chart, there is spacing defined between two columns. By
	 * default, the spacing is set to 20% of canvas width
	 */
	/** (0-80%) */
	private double plotSpacePercent;
	/** 图表左边的空白 */
	private int chartLeftMargin;
	/** 图表右边的空白 */
	private int chartRightMargin;
	/** 图表上边的空白 */
	private int chartTopMargin;
	/** 图表下面的空白 */
	private int chartBottomMargin;
	/** set empty space on the left and right side of columns on the chart canvas */
	private int canvasPadding;
	/**
	 * control the space between the start of chart canvas and the start (x) of
	 * chart
	 */
	private int canvasLeftMargin;
	/** set the space between end of canvas and end of chart. */
	private int canvasRightMargin;
	/** set the space between top of canvas and top of chart. */
	private int canvasTopMargin;
	/** set the space between bottom of canvas and bottom of chart. */
	private int canvasBottomMargin;
	// Anchors:line/area 图中,转折点出现的点
	/** 是否显示转折点 */
	private boolean drawAnchors;
	/** 转折点的边(3-20) */
	private int anchorSides;
	/** 转折点的半径 */
	private int anchorRadius;
	/** 转折点厚度 */
	private int anchorBorderThickness;
	/** 转折点透明度0-100 */
	private int anchorAlpha;
	/** 转折点半径透明度0-100 */
	private int anchorBgAlpha;
	/** 转折点边界颜色 */
	private String anchorBorderColor;
	/** 转折点背景色 */
	private String anchorBgColor;

	public List getLines() {
		return lines;
	}

	public Chart setLines(List lines) {
		this.lines = lines;
		registerAtrributes.add("palette");
		return this;
	}

	public boolean isDrawAnchors() {
		return drawAnchors;
	}

	public Chart setDrawAnchors(boolean drawAnchors) {
		this.drawAnchors = drawAnchors;
		registerAtrributes.add("drawAnchors");
		return this;
	}

	public int getAnchorSides() {
		return anchorSides;
	}

	public Chart setAnchorSides(int anchorSides) {
		this.anchorSides = anchorSides;
		registerAtrributes.add("anchorSides");
		return this;
	}

	public int getAnchorRadius() {
		return anchorRadius;
	}

	public Chart setAnchorRadius(int anchorRadius) {
		this.anchorRadius = anchorRadius;
		registerAtrributes.add("anchorRadius");
		return this;
	}

	public int getAnchorBorderThickness() {
		return anchorBorderThickness;
	}

	public Chart setAnchorBorderThickness(int anchorBorderThickness) {
		this.anchorBorderThickness = anchorBorderThickness;
		registerAtrributes.add("anchorBorderThickness");
		return this;
	}

	public int getAnchorAlpha() {
		return anchorAlpha;
	}

	public Chart setAnchorAlpha(int anchorAlpha) {
		this.anchorAlpha = anchorAlpha;
		registerAtrributes.add("anchorAlpha");
		return this;
	}

	public int getAnchorBgAlpha() {
		return anchorBgAlpha;
	}

	public Chart setAnchorBgAlpha(int anchorBgAlpha) {
		registerAtrributes.add("anchorBgAlpha");
		this.anchorBgAlpha = anchorBgAlpha;
		return this;
	}

	public String getAnchorBorderColor() {
		return anchorBorderColor;
	}

	public Chart setAnchorBorderColor(String anchorBorderColor) {
		this.anchorBorderColor = anchorBorderColor;
		registerAtrributes.add("anchorBorderColor");
		return this;
	}

	public String getAnchorBgColor() {
		return anchorBgColor;
	}

	public Chart setAnchorBgColor(String anchorBgColor) {
		this.anchorBgColor = anchorBgColor;
		registerAtrributes.add("anchorBgColor");
		return this;
	}

	public String getCaption() {
		return caption;
	}

	public Chart setCaption(String caption) {
		registerAtrributes.add("caption");
		this.caption = caption;
		return this;
	}

	public String getSubCaption() {
		return subCaption;
	}

	public Chart setSubCaption(String subCaption) {
		registerAtrributes.add("subCaption");
		this.subCaption = subCaption;
		return this;
	}

	public String getXAxisName() {
		return xAxisName;
	}

	public Chart setXAxisName(String axisName) {
		registerAtrributes.add("xAxisName");
		xAxisName = axisName;
		return this;
	}

	public String getYAxisName() {
		return yAxisName;
	}

	public Chart setYAxisName(String axisName) {
		registerAtrributes.add("yAxisName");
		yAxisName = axisName;
		return this;
	}

	public boolean isAnimation() {
		return animation;
	}

	public Chart setAnimation(boolean animation) {
		registerAtrributes.add("animation");
		this.animation = animation;
		return this;
	}

	public int getPalette() {
		return palette;
	}

	public Chart setPalette(int palette) {
		registerAtrributes.add("palette");
		this.palette = palette;
		return this;
	}

	public String getPaletteColors() {
		return paletteColors;
	}

	public Chart setPaletteColors(String paletteColors) {
		registerAtrributes.add("paletteColors");
		this.paletteColors = paletteColors;
		return this;
	}

	public boolean isShowAboutMenuItem() {
		return showAboutMenuItem;
	}

	public Chart setShowAboutMenuItem(boolean showAboutMenuItem) {
		registerAtrributes.add("showAboutMenuItem");
		this.showAboutMenuItem = showAboutMenuItem;
		return this;
	}

	public String getAboutMenuItemLabel() {
		return aboutMenuItemLabel;
	}

	public Chart setAboutMenuItemLabel(String aboutMenuItemLabel) {
		registerAtrributes.add("aboutMenuItemLabel");
		this.aboutMenuItemLabel = aboutMenuItemLabel;
		return this;
	}

	public String getAboutMenuItemLink() {
		return aboutMenuItemLink;
	}

	public Chart setAboutMenuItemLink(String aboutMenuItemLink) {
		registerAtrributes.add("aboutMenuItemLink");
		this.aboutMenuItemLink = aboutMenuItemLink;
		return this;
	}

	public boolean isRotateLabels() {
		return rotateLabels;
	}

	public Chart setRotateLabels(boolean rotateLabels) {
		registerAtrributes.add("rotateLabels");
		this.rotateLabels = rotateLabels;
		return this;
	}

	public boolean isSlantLabels() {
		return slantLabels;
	}

	public Chart setSlantLabels(boolean slantLabels) {
		registerAtrributes.add("slantLabels");
		this.slantLabels = slantLabels;
		return this;
	}

	public int getLabelStep() {
		return labelStep;
	}

	public Chart setLabelStep(int labelStep) {
		registerAtrributes.add("labelStep");
		this.labelStep = labelStep;
		return this;
	}

	public int getStaggerLines() {
		return staggerLines;
	}

	public Chart setStaggerLines(int staggerLines) {
		registerAtrributes.add("staggerLines");
		this.staggerLines = staggerLines;
		return this;
	}

	public boolean isShowNames() {
		return showNames;
	}

	public Chart setShowNames(boolean showNames) {
		registerAtrributes.add("showNames");
		this.showNames = showNames;
		return this;
	}

	public boolean isRotateValues() {
		return rotateValues;
	}

	public Chart setRotateValues(boolean rotateValues) {
		registerAtrributes.add("rotateValues");
		this.rotateValues = rotateValues;
		return this;
	}

	public boolean isPlaceValuesInside() {
		return placeValuesInside;
	}

	public Chart setPlaceValuesInside(boolean placeValuesInside) {
		registerAtrributes.add("placeValuesInside");
		this.placeValuesInside = placeValuesInside;
		return this;
	}

	public boolean isShowYAxisValues() {
		return showYAxisValues;
	}

	public Chart setShowYAxisValues(boolean showYAxisValues) {
		registerAtrributes.add("showYAxisValues");
		this.showYAxisValues = showYAxisValues;
		return this;
	}

	public boolean isShowLimits() {
		return showLimits;
	}

	public Chart setShowLimits(boolean showLimits) {
		registerAtrributes.add("showLimits");
		this.showLimits = showLimits;
		return this;
	}

	public boolean isShowDivLineValues() {
		return showDivLineValues;
	}

	public Chart setShowDivLineValues(boolean showDivLineValues) {
		registerAtrributes.add("showDivLineValues");
		this.showDivLineValues = showDivLineValues;
		return this;
	}

	public boolean isShowShadow() {
		return showShadow;
	}

	public Chart setShowShadow(boolean showShadow) {
		registerAtrributes.add("showShadow");
		this.showShadow = showShadow;
		return this;
	}

	public boolean isRotateYAxisName() {
		return rotateYAxisName;
	}

	public Chart setRotateYAxisName(boolean rotateYAxisName) {
		registerAtrributes.add("rotateYAxisName");
		this.rotateYAxisName = rotateYAxisName;
		return this;
	}

	public boolean isDefaultAnimation() {
		return defaultAnimation;
	}

	public Chart setDefaultAnimation(boolean defaultAnimation) {
		this.defaultAnimation = defaultAnimation;
		registerAtrributes.add("defaultAnimation");
		return this;
	}

	public int getYAxisNameWidth() {
		return yAxisNameWidth;
	}

	public Chart setYAxisNameWidth(int yAxisNameWidth) {
		registerAtrributes.add("yAxisNameWidth");
		yAxisNameWidth = yAxisNameWidth;
		return this;
	}

	public int getYAxisMinValue() {
		return yAxisMinValue;
	}

	public Chart setYAxisMinValue(int yAxisMinValue) {
		registerAtrributes.add("yAxisMinValue");
		yAxisMinValue = yAxisMinValue;
		return this;
	}

	public int getYAxisMaxValue() {
		return yAxisMaxValue;
	}

	public Chart setYAxisMaxValue(int yAxisMaxValue) {
		registerAtrributes.add("yAxisMaxValue");
		yAxisMaxValue = yAxisMaxValue;
		return this;
	}

	public boolean isSetAdaptiveYMin() {
		return setAdaptiveYMin;
	}

	public Chart setSetAdaptiveYMin(boolean setAdaptiveYMin) {
		registerAtrributes.add("setAdaptiveYMin");
		this.setAdaptiveYMin = setAdaptiveYMin;
		return this;
	}

	public String getClickURL() {
		return clickURL;
	}

	public Chart setClickURL(String clickURL) {
		registerAtrributes.add("clickURL");
		this.clickURL = clickURL;
		return this;
	}

	public int getYAxisValuesStep() {
		return yAxisValuesStep;
	}

	public Chart setYAxisValuesStep(int yAxisValuesStep) {
		registerAtrributes.add("yAxisValuesStep");
		yAxisValuesStep = yAxisValuesStep;
		return this;
	}

	public boolean isShowLabels() {
		return showLabels;
	}

	public Chart setShowLabels(boolean showLabels) {
		registerAtrributes.add("showLabels");
		this.showLabels = showLabels;
		return this;
	}

	public boolean isShowVlaues() {
		return showVlaues;
	}

	public Chart setShowVlaues(boolean showVlaues) {
		registerAtrributes.add("showVlaues");
		this.showVlaues = showVlaues;
		return this;
	}

	public String getBgColor() {
		return bgColor;
	}

	public Chart setBgColor(String bgColor) {
		registerAtrributes.add("bgColor");
		this.bgColor = bgColor;
		return this;
	}

	public int getBgAlpha() {
		return bgAlpha;
	}

	public Chart setBgAlpha(int bgAlpha) {
		registerAtrributes.add("bgAlpha");
		this.bgAlpha = bgAlpha;
		return this;
	}

	public int getBgRatio() {
		return bgRatio;
	}

	public Chart setBgRatio(int bgRatio) {
		registerAtrributes.add("bgRatio");
		this.bgRatio = bgRatio;
		return this;
	}

	public int getBgAngle() {
		return bgAngle;
	}

	public Chart setBgAngle(int bgAngle) {
		registerAtrributes.add("bgAngle");
		this.bgAngle = bgAngle;
		return this;
	}

	public boolean isShowBorder() {
		return showBorder;
	}

	public Chart setShowBorder(boolean showBorder) {
		registerAtrributes.add("showBorder");
		this.showBorder = showBorder;
		return this;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public Chart setBorderColor(String borderColor) {
		registerAtrributes.add("borderColor");
		this.borderColor = borderColor;
		return this;
	}

	public int getBorderThickness() {
		return borderThickness;
	}

	public Chart setBorderThickness(int borderThickness) {
		registerAtrributes.add("borderThickness");
		this.borderThickness = borderThickness;
		return this;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public Chart setLogoURL(String logoURL) {
		this.logoURL = logoURL;
		registerAtrributes.add("logoURL");
		return this;
	}

	public LogoPosition getLogoPosition() {
		return logoPosition;
	}

	public Chart setLogoPosition(LogoPosition logoPosition) {
		registerAtrributes.add("logoPosition");
		this.logoPosition = logoPosition;
		return this;
	}

	public String getLogoLink() {
		return logoLink;
	}

	public Chart setLogoLink(String logoLink) {
		registerAtrributes.add("logoLink");
		this.logoLink = logoLink;
		return this;
	}

	public boolean getFormatNumberScale() {
		return formatNumberScale;
	}

	public Chart setFormatNumberScale(boolean formatNumberScale) {
		registerAtrributes.add("formatNumberScale");
		this.formatNumberScale = formatNumberScale;
		return this;
	}

	public Categories getCategories() {
		return categories;
	}

	public Chart setCategories(Categories categories) {
		registerAtrributes.add("categories");
		this.categories = categories;
		return this;
	}

	public DataSet getDataSet() {
		return dataSet;
	}

	public Chart setDataSet(DataSet dataSet) {
		registerAtrributes.add("dataSet");
		this.dataSet = dataSet;
		return this;
	}

	public int getBaseFontSize() {
		return baseFontSize;
	}

	public Chart setBaseFontSize(int baseFontSize) {
		registerAtrributes.add("baseFontSize");
		this.baseFontSize = baseFontSize;
		return this;
	}

	public List getSets() {
		return datas;
	}

	public Chart setSets(List datas) {
		this.datas = datas;
		return this;
	}

	public List getDataSets() {
		return dataSets;
	}

	public Chart setDataSets(List dataSets) {
		this.dataSets = dataSets;
		return this;
	}

	public List getCategorieses() {
		return categorieses;
	}

	public Chart setCategorieses(List categorieses) {
		this.categorieses = categorieses;
		return this;
	}

	public boolean isIs2D() {
		return is2D;
	}

	public Chart setIs2D(boolean is2D) {
		registerAtrributes.add("is2D");
		this.is2D = is2D;
		return this;
	}

	public boolean isClustered() {
		return clustered;
	}

	public Chart setClustered(boolean clustered) {
		registerAtrributes.add("clustered");
		this.clustered = clustered;
		return this;
	}

	public boolean isChartOrder() {
		return chartOrder;
	}

	public Chart setChartOrder(boolean chartOrder) {
		registerAtrributes.add("chartOrder");
		this.chartOrder = chartOrder;
		return this;
	}

	public boolean isChartOnTop() {
		return chartOnTop;
	}

	public Chart setChartOnTop(boolean chartOnTop) {
		registerAtrributes.add("chartOnTop");
		this.chartOnTop = chartOnTop;
		return this;
	}

	public boolean isAutoScaling() {
		return autoScaling;
	}

	public Chart setAutoScaling(boolean autoScaling) {
		registerAtrributes.add("autoScaling");
		this.autoScaling = autoScaling;
		return this;
	}

	public boolean isAllowScaling() {
		return allowScaling;
	}

	public Chart setAllowScaling(boolean allowScaling) {
		registerAtrributes.add("allowScaling");
		this.allowScaling = allowScaling;
		return this;
	}

	public int getStartAngX() {
		return startAngX;
	}

	public Chart setStartAngX(int startAngX) {
		registerAtrributes.add("startAngX");
		this.startAngX = startAngX;
		return this;
	}

	public int getStartAngY() {
		return startAngY;
	}

	public Chart setStartAngY(int startAngY) {
		registerAtrributes.add("startAngY");
		this.startAngY = startAngY;
		return this;
	}

	public int getEndAngX() {
		return endAngX;
	}

	public Chart setEndAngX(int endAngX) {
		registerAtrributes.add("endAngX");
		this.endAngX = endAngX;
		return this;
	}

	public int getEndAngY() {
		return endAngY;
	}

	public Chart setEndAngY(int endAngY) {
		registerAtrributes.add("endAngY");
		this.endAngY = endAngY;
		return this;
	}

	public int getCameraAngX() {
		return cameraAngX;
	}

	public Chart setCameraAngX(int cameraAngX) {
		registerAtrributes.add("cameraAngX");
		this.cameraAngX = cameraAngX;
		return this;
	}

	public int getCameraAngY() {
		return cameraAngY;
	}

	public Chart setCameraAngY(int cameraAngY) {
		registerAtrributes.add("cameraAngY");
		this.cameraAngY = cameraAngY;
		return this;
	}

	public int getLightAngX() {
		return lightAngX;
	}

	public Chart setLightAngX(int lightAngX) {
		registerAtrributes.add("lightAngX");
		this.lightAngX = lightAngX;
		return this;
	}

	public int getLightAngY() {
		return lightAngY;
	}

	public Chart setLightAngY(int lightAngY) {
		registerAtrributes.add("lightAngY");
		this.lightAngY = lightAngY;
		return this;
	}

	public double getIntensity() {
		return intensity;
	}

	public Chart setIntensity(double intensity) {
		registerAtrributes.add("intensity");
		this.intensity = intensity;
		return this;
	}

	public boolean isBright2D() {
		return bright2D;
	}

	public Chart setBright2D(boolean bright2D) {
		this.bright2D = bright2D;
		registerAtrributes.add("bright2D");
		return this;
	}

	public boolean isAllowRotation() {
		return allowRotation;
	}

	public Chart setAllowRotation(boolean allowRotation) {
		registerAtrributes.add("allowRotation");
		this.allowRotation = allowRotation;
		return this;
	}

	public boolean isConstrainVerticalRotation() {
		return constrainVerticalRotation;
	}

	public Chart setConstrainVerticalRotation(boolean constrainVerticalRotation) {
		registerAtrributes.add("constrainVerticalRotation");
		this.constrainVerticalRotation = constrainVerticalRotation;
		return this;
	}

	public int getMinVerticalRotAngle() {
		return minVerticalRotAngle;
	}

	public Chart setMinVerticalRotAngle(int minVerticalRotAngle) {
		registerAtrributes.add("minVerticalRotAngle");
		this.minVerticalRotAngle = minVerticalRotAngle;
		return this;
	}

	public int getMaxVerticalRotAngle() {
		return maxVerticalRotAngle;
	}

	public Chart setMaxVerticalRotAngle(int maxVerticalRotAngle) {
		registerAtrributes.add("maxVerticalRotAngle");
		this.maxVerticalRotAngle = maxVerticalRotAngle;
		return this;
	}

	public boolean isConstrainHorizontalRotation() {
		return constrainHorizontalRotation;
	}

	public Chart setConstrainHorizontalRotation(
			boolean constrainHorizontalRotation) {
		registerAtrributes.add("constrainHorizontalRotation");
		this.constrainHorizontalRotation = constrainHorizontalRotation;
		return this;
	}

	public int getMinHorizontalRotAngle() {
		return minHorizontalRotAngle;
	}

	public Chart setMinHorizontalRotAngle(int minHorizontalRotAngle) {
		registerAtrributes.add("minHorizontalRotAngle");
		this.minHorizontalRotAngle = minHorizontalRotAngle;
		return this;
	}

	public int getMaxHorizontalRotAngle() {
		return maxHorizontalRotAngle;
	}

	public Chart setMaxHorizontalRotAngle(int maxHorizontalRotAngle) {
		registerAtrributes.add("maxHorizontalRotAngle");
		this.maxHorizontalRotAngle = maxHorizontalRotAngle;
		return this;
	}

	public int getZDepth() {
		return zDepth;
	}

	public Chart setZDepth(int depth) {
		registerAtrributes.add("zDepth");
		zDepth = depth;
		return this;
	}

	public int getZGapPlot() {
		return zGapPlot;
	}

	public Chart setZGapPlot(int gapPlot) {
		registerAtrributes.add("zGapPlot");
		zGapPlot = gapPlot;
		return this;
	}

	public boolean isShowPlotBorder() {
		return showPlotBorder;
	}

	public Chart setShowPlotBorder(boolean showPlotBorder) {
		registerAtrributes.add("showPlotBorder");
		this.showPlotBorder = showPlotBorder;
		return this;
	}

	public int getYzWallDepth() {
		return yzWallDepth;
	}

	public Chart setYzWallDepth(int yzWallDepth) {
		registerAtrributes.add("yzWallDepth");
		this.yzWallDepth = yzWallDepth;
		return this;
	}

	public int getZxWallDepth() {
		return zxWallDepth;
	}

	public Chart setZxWallDepth(int zxWallDepth) {
		this.zxWallDepth = zxWallDepth;
		registerAtrributes.add("zxWallDepth");
		return this;
	}

	public int getXyWallDepth() {
		return xyWallDepth;
	}

	public Chart setXyWallDepth(int xyWallDepth) {
		this.xyWallDepth = xyWallDepth;
		registerAtrributes.add("xyWallDepth");
		return this;
	}

	public int getNumDivLines() {
		return numDivLines;
	}

	public Chart setNumDivLines(int numDivLines) {
		this.numDivLines = numDivLines;
		registerAtrributes.add("numDivLines");
		return this;
	}

	public int getDivLineThickness() {
		return divLineThickness;
	}

	public Chart setDivLineThickness(int divLineThickness) {
		this.divLineThickness = divLineThickness;
		registerAtrributes.add("divLineThickness");
		return this;
	}

	public int getDivLineAlpha() {
		return divLineAlpha;
	}

	public Chart setDivLineAlpha(int divLineAlpha) {
		this.divLineAlpha = divLineAlpha;
		registerAtrributes.add("divLineAlpha");
		return this;
	}

	public int getZeroPlaneAlpha() {
		return zeroPlaneAlpha;
	}

	public Chart setZeroPlaneAlpha(int zeroPlaneAlpha) {
		this.zeroPlaneAlpha = zeroPlaneAlpha;
		registerAtrributes.add("zeroPlaneAlpha");
		return this;
	}

	public boolean isZeroPlaneMesh() {
		return zeroPlaneMesh;
	}

	public Chart setZeroPlaneMesh(boolean zeroPlaneMesh) {
		this.zeroPlaneMesh = zeroPlaneMesh;
		registerAtrributes.add("zeroPlaneMesh");
		return this;
	}

	public boolean isShowAlternateHGridColor() {
		return showAlternateHGridColor;
	}

	public Chart setShowAlternateHGridColor(boolean showAlternateHGridColor) {
		this.showAlternateHGridColor = showAlternateHGridColor;
		registerAtrributes.add("showAlternateHGridColor");
		return this;
	}

	public DivLineEffect getDivLineEffect() {
		return divLineEffect;
	}

	public Chart setDivLineEffect(DivLineEffect divLineEffect) {
		this.divLineEffect = divLineEffect;
		registerAtrributes.add("divLineEffect");
		return this;
	}

	public String getDivLineColor() {
		return divLineColor;
	}

	public Chart setDivLineColor(String divLineColor) {
		this.divLineColor = divLineColor;
		registerAtrributes.add("divLineColor");
		return this;
	}

	public String getZeroPlaneColor() {
		return zeroPlaneColor;
	}

	public Chart setZeroPlaneColor(String zeroPlaneColor) {
		this.zeroPlaneColor = zeroPlaneColor;
		registerAtrributes.add("zeroPlaneColor");
		return this;
	}

	public String getAlternateHGridColor() {
		return alternateHGridColor;
	}

	public Chart setAlternateHGridColor(String alternateHGridColor) {
		this.alternateHGridColor = alternateHGridColor;
		registerAtrributes.add("alternateHGridColor");
		return this;
	}

	public boolean isShowLegend() {
		return showLegend;
	}

	public Chart setShowLegend(boolean showLegend) {
		this.showLegend = showLegend;
		registerAtrributes.add("showLegend");
		return this;
	}

	public boolean getInteractiveLegend() {
		return interactiveLegend;
	}

	public Chart setInteractiveLegend(boolean interactiveLegend) {
		registerAtrributes.add("interactiveLegend");
		this.interactiveLegend = interactiveLegend;
		return this;
	}

	public boolean isLegendMarkerCircle() {
		return legendMarkerCircle;
	}

	public Chart setLegendMarkerCircle(boolean legendMarkerCircle) {
		registerAtrributes.add("legendMarkerCircle");
		this.legendMarkerCircle = legendMarkerCircle;
		return this;
	}

	public boolean isLegendShadow() {
		return legendShadow;
	}

	public Chart setLegendShadow(boolean legendShadow) {
		registerAtrributes.add("legendShadow");
		this.legendShadow = legendShadow;
		return this;
	}

	public boolean isLegendAllowDrag() {
		return legendAllowDrag;
	}

	public Chart setLegendAllowDrag(boolean legendAllowDrag) {
		registerAtrributes.add("legendAllowDrag");
		this.legendAllowDrag = legendAllowDrag;
		return this;
	}

	public LegendPosition getLegendPosition() {
		return legendPosition;
	}

	public Chart setLegendPosition(LegendPosition legendPosition) {
		registerAtrributes.add("legendPosition");
		this.legendPosition = legendPosition;
		return this;
	}

	public String getLegendCaption() {
		return legendCaption;
	}

	public Chart setLegendCaption(String legendCaption) {
		registerAtrributes.add("legendCaption");
		this.legendCaption = legendCaption;
		return this;
	}

	public String getLegendBgColor() {
		return legendBgColor;
	}

	public Chart setLegendBgColor(String legendBgColor) {
		registerAtrributes.add("legendBgColor");
		this.legendBgColor = legendBgColor;
		return this;
	}

	public String getLegendBorderColor() {
		return legendBorderColor;
	}

	public Chart setLegendBorderColor(String legendBorderColor) {
		registerAtrributes.add("legendBorderColor");
		this.legendBorderColor = legendBorderColor;
		return this;
	}

	public String getLegendScrollBgColor() {
		return legendScrollBgColor;
	}

	public Chart setLegendScrollBgColor(String legendScrollBgColor) {
		registerAtrributes.add("legendScrollBgColor");
		this.legendScrollBgColor = legendScrollBgColor;
		return this;
	}

	public String getLegendScrollBarColor() {
		return legendScrollBarColor;
	}

	public Chart setLegendScrollBarColor(String legendScrollBarColor) {
		registerAtrributes.add("legendScrollBarColor");
		this.legendScrollBarColor = legendScrollBarColor;
		return this;
	}

	public String getLegendScrollBtnColor() {
		return legendScrollBtnColor;
	}

	public Chart setLegendScrollBtnColor(String legendScrollBtnColor) {
		registerAtrributes.add("legendScrollBtnColor");
		this.legendScrollBtnColor = legendScrollBtnColor;
		return this;
	}

	public int getLegendBgAlpha() {
		return legendBgAlpha;
	}

	public Chart setLegendBgAlpha(int legendBgAlpha) {
		registerAtrributes.add("legendBgAlpha");
		this.legendBgAlpha = legendBgAlpha;
		return this;
	}

	public int getLegendBorderThickness() {
		return legendBorderThickness;
	}

	public Chart setLegendBorderThickness(int legendBorderThickness) {
		registerAtrributes.add("legendBorderThickness");
		this.legendBorderThickness = legendBorderThickness;
		return this;
	}

	public int getLegendBorderAlpha() {
		return legendBorderAlpha;
	}

	public Chart setLegendBorderAlpha(int legendBorderAlpha) {
		registerAtrributes.add("legendBorderAlpha");
		this.legendBorderAlpha = legendBorderAlpha;
		return this;
	}

	public boolean isFormatNumber() {
		return formatNumber;
	}

	public Chart setFormatNumber(boolean formatNumber) {
		registerAtrributes.add("formatNumber");
		this.formatNumber = formatNumber;
		return this;
	}

	public boolean isForceDecimals() {
		return forceDecimals;
	}

	public Chart setForceDecimals(boolean forceDecimals) {
		this.forceDecimals = forceDecimals;
		registerAtrributes.add("forceDecimals");
		return this;
	}

	public DefaultNumberScale getDefaultNumberScale() {
		return defaultNumberScale;
	}

	public Chart setDefaultNumberScale(DefaultNumberScale defaultNumberScale) {
		registerAtrributes.add("defaultNumberScale");
		this.defaultNumberScale = defaultNumberScale;
		return this;
	}

	public String getInThousandSeparator() {
		return inThousandSeparator;
	}

	public Chart setInThousandSeparator(String inThousandSeparator) {
		registerAtrributes.add("inThousandSeparator");
		this.inThousandSeparator = inThousandSeparator;
		return this;
	}

	public String getNumberScaleUnit() {
		return numberScaleUnit;
	}

	public Chart setNumberScaleUnit(String numberScaleUnit) {
		registerAtrributes.add("numberScaleUnit");
		this.numberScaleUnit = numberScaleUnit;
		return this;
	}

	public String getNumberScaleValue() {
		return numberScaleValue;
	}

	public Chart setNumberScaleValue(String numberScaleValue) {
		registerAtrributes.add("numberScaleValue");
		this.numberScaleValue = numberScaleValue;
		return this;
	}

	public String getNumberPrefix() {
		return numberPrefix;
	}

	public Chart setNumberPrefix(String numberPrefix) {
		registerAtrributes.add("numberPrefix");
		this.numberPrefix = numberPrefix;
		return this;
	}

	public String getNumberSuffix() {
		return numberSuffix;
	}

	public Chart setNumberSuffix(String numberSuffix) {
		registerAtrributes.add("numberSuffix");
		this.numberSuffix = numberSuffix;
		return this;
	}

	public String getDecimalSeparator() {
		return decimalSeparator;
	}

	public Chart setDecimalSeparator(String decimalSeparator) {
		registerAtrributes.add("decimalSeparator");
		this.decimalSeparator = decimalSeparator;
		return this;
	}

	public String getThousandSeparator() {
		return thousandSeparator;
	}

	public Chart setThousandSeparator(String thousandSeparator) {
		this.thousandSeparator = thousandSeparator;
		registerAtrributes.add("thousandSeparator");
		return this;
	}

	public String getInDecimalSeparator() {
		return inDecimalSeparator;
	}

	public Chart setInDecimalSeparator(String inDecimalSeparator) {
		registerAtrributes.add("inDecimalSeparator");
		this.inDecimalSeparator = inDecimalSeparator;
		return this;
	}

	public int getDecimals() {
		return decimals;
	}

	public Chart setDecimals(int decimals) {
		registerAtrributes.add("decimals");
		this.decimals = decimals;
		return this;
	}

	public int getYAxisValueDecimals() {
		return yAxisValueDecimals;
	}

	public Chart setYAxisValueDecimals(int axisValueDecimals) {
		registerAtrributes.add("yAxisValueDecimals");
		yAxisValueDecimals = axisValueDecimals;
		return this;
	}

	public String getBaseFont() {
		return baseFont;
	}

	public Chart setBaseFont(String baseFont) {
		registerAtrributes.add("baseFont");
		this.baseFont = baseFont;
		return this;
	}

	public String getBaseFontColor() {
		return baseFontColor;
	}

	public Chart setBaseFontColor(String baseFontColor) {
		this.baseFontColor = baseFontColor;
		registerAtrributes.add("baseFontColor");
		return this;
	}

	public String getOutCnvBaseFont() {
		return outCnvBaseFont;
	}

	public Chart setOutCnvBaseFont(String outCnvBaseFont) {
		this.outCnvBaseFont = outCnvBaseFont;
		registerAtrributes.add("outCnvBaseFont");
		return this;
	}

	public String getOutCnvBaseFontColor() {
		return outCnvBaseFontColor;
	}

	public Chart setOutCnvBaseFontColor(String outCnvBaseFontColor) {
		this.outCnvBaseFontColor = outCnvBaseFontColor;
		registerAtrributes.add("outCnvBaseFontColor");
		return this;
	}

	public int getOutCnvBaseFontSize() {
		return outCnvBaseFontSize;
	}

	public Chart setOutCnvBaseFontSize(int outCnvBaseFontSize) {
		registerAtrributes.add("outCnvBaseFontSize");
		this.outCnvBaseFontSize = outCnvBaseFontSize;
		return this;
	}

	public boolean isShowToolTip() {
		return showToolTip;
	}

	public Chart setShowToolTip(boolean showToolTip) {
		registerAtrributes.add("showToolTip");
		this.showToolTip = showToolTip;
		return this;
	}

	public boolean isSeriesNameInToolTip() {
		return seriesNameInToolTip;
	}

	public Chart setSeriesNameInToolTip(boolean seriesNameInToolTip) {
		registerAtrributes.add("seriesNameInToolTip");
		this.seriesNameInToolTip = seriesNameInToolTip;
		return this;
	}

	public boolean isShowToolTipShadow() {
		return showToolTipShadow;
	}

	public Chart setShowToolTipShadow(boolean showToolTipShadow) {
		registerAtrributes.add("showToolTipShadow");
		this.showToolTipShadow = showToolTipShadow;
		return this;
	}

	public String getToolTipBgColor() {
		return toolTipBgColor;
	}

	public Chart setToolTipBgColor(String toolTipBgColor) {
		registerAtrributes.add("toolTipBgColor");
		this.toolTipBgColor = toolTipBgColor;
		return this;
	}

	public String getToolTipSepChar() {
		return toolTipSepChar;
	}

	public Chart setToolTipSepChar(String toolTipSepChar) {
		this.toolTipSepChar = toolTipSepChar;
		registerAtrributes.add("toolTipSepChar");
		return this;
	}

	public String getToolTipBorderColor() {
		return toolTipBorderColor;
	}

	public Chart setToolTipBorderColor(String toolTipBorderColor) {
		registerAtrributes.add("toolTipBorderColor");
		this.toolTipBorderColor = toolTipBorderColor;
		return this;
	}

	public boolean isDynamicShading() {
		return dynamicShading;
	}

	public Chart setDynamicShading(boolean dynamicShading) {
		registerAtrributes.add("dynamicShading");
		this.dynamicShading = dynamicShading;
		return this;
	}

	public Set getRegisterAtrributes() {
		return registerAtrributes;
	}

	public Chart setRegisterAtrributes(Set registerAtrributes) {
		this.registerAtrributes = registerAtrributes;
		registerAtrributes.add("registerAtrributes");
		return this;
	}

	public int getCaptionPadding() {
		return captionPadding;
	}

	public Chart setCaptionPadding(int captionPadding) {
		this.captionPadding = captionPadding;
		registerAtrributes.add("captionPadding");
		return this;
	}

	public int getXAxisNamePadding() {
		return xAxisNamePadding;
	}

	public Chart setXAxisNamePadding(int axisNamePadding) {
		xAxisNamePadding = axisNamePadding;
		registerAtrributes.add("xAxisNamePadding");
		return this;
	}

	public int getYAxisNamePadding() {
		return yAxisNamePadding;
	}

	public Chart setYAxisNamePadding(int axisNamePadding) {
		yAxisNamePadding = axisNamePadding;
		registerAtrributes.add("yAxisNamePadding");
		return this;
	}

	public int getYAxisValuesPadding() {
		return yAxisValuesPadding;
	}

	public Chart setYAxisValuesPadding(int axisValuesPadding) {
		yAxisValuesPadding = axisValuesPadding;
		registerAtrributes.add("yAxisValuesPadding");
		return this;
	}

	public int getLabelPadding() {
		return labelPadding;
	}

	public Chart setLabelPadding(int labelPadding) {
		this.labelPadding = labelPadding;
		registerAtrributes.add("labelPadding");
		return this;
	}

	public int getValuePadding() {
		return valuePadding;
	}

	public Chart setValuePadding(int valuePadding) {
		this.valuePadding = valuePadding;
		registerAtrributes.add("valuePadding");
		return this;
	}

	public double getPlotSpacePercent() {
		return plotSpacePercent;
	}

	public Chart setPlotSpacePercent(double plotSpacePercent) {
		this.plotSpacePercent = plotSpacePercent;
		registerAtrributes.add("plotSpacePercent");
		return this;
	}

	public int getChartLeftMargin() {
		return chartLeftMargin;
	}

	public Chart setChartLeftMargin(int chartLeftMargin) {
		this.chartLeftMargin = chartLeftMargin;
		registerAtrributes.add("chartLeftMargin");
		return this;
	}

	public int getChartRightMargin() {
		return chartRightMargin;
	}

	public Chart setChartRightMargin(int chartRightMargin) {
		this.chartRightMargin = chartRightMargin;
		registerAtrributes.add("chartRightMargin");
		return this;
	}

	public int getChartTopMargin() {
		return chartTopMargin;
	}

	public Chart setChartTopMargin(int chartTopMargin) {
		registerAtrributes.add("chartTopMargin");
		this.chartTopMargin = chartTopMargin;
		return this;
	}

	public int getChartBottomMargin() {
		return chartBottomMargin;
	}

	public Chart setChartBottomMargin(int chartBottomMargin) {
		registerAtrributes.add("chartBottomMargin");
		this.chartBottomMargin = chartBottomMargin;
		return this;
	}

	public int getCanvasPadding() {
		return canvasPadding;
	}

	public Chart setCanvasPadding(int canvasPadding) {
		registerAtrributes.add("canvasPadding");
		this.canvasPadding = canvasPadding;
		return this;
	}

	public int getCanvasLeftMargin() {
		return canvasLeftMargin;
	}

	public Chart setCanvasLeftMargin(int canvasLeftMargin) {
		registerAtrributes.add("canvasLeftMargin");
		this.canvasLeftMargin = canvasLeftMargin;
		return this;
	}

	public int getCanvasRightMargin() {
		return canvasRightMargin;
	}

	public Chart setCanvasRightMargin(int canvasRightMargin) {
		this.canvasRightMargin = canvasRightMargin;
		registerAtrributes.add("canvasRightMargin");
		return this;
	}

	public int getCanvasTopMargin() {
		return canvasTopMargin;
	}

	public Chart setCanvasTopMargin(int canvasTopMargin) {
		this.canvasTopMargin = canvasTopMargin;
		registerAtrributes.add("canvasTopMargin");
		return this;
	}

	public int getCanvasBottomMargin() {
		return canvasBottomMargin;
	}

	public Chart setCanvasBottomMargin(int canvasBottomMargin) {
		this.canvasBottomMargin = canvasBottomMargin;
		registerAtrributes.add("canvasBottomMargin");
		return this;
	}

	public List getTrendLines() {
		return trendLines;
	}

	public Chart setTrendLines(List trendLines) {
		this.trendLines = trendLines;
		registerAtrributes.add("trendLines");
		return this;
	}

	public boolean isDivLineIsDashed() {
		return divLineIsDashed;
	}

	public Chart setDivLineIsDashed(boolean divLineIsDashed) {
		this.divLineIsDashed = divLineIsDashed;
		registerAtrributes.add("divLineIsDashed");
		return this;
	}

	public boolean isVDivLineIsDashed() {
		return vDivLineIsDashed;
	}

	public Chart setVDivLineIsDashed(boolean divLineIsDashed) {
		vDivLineIsDashed = divLineIsDashed;
		registerAtrributes.add("vDivLineIsDashed");
		return this;
	}

	public boolean isShowAlternateVGridColor() {
		return showAlternateVGridColor;
	}

	public Chart setShowAlternateVGridColor(boolean showAlternateVGridColor) {
		this.showAlternateVGridColor = showAlternateVGridColor;
		registerAtrributes.add("showAlternateVGridColor");
		return this;
	}

	public int getDivLineDashLen() {
		return divLineDashLen;
	}

	public Chart setDivLineDashLen(int divLineDashLen) {
		this.divLineDashLen = divLineDashLen;
		registerAtrributes.add("divLineDashLen");
		return this;
	}

	public int getDivLineDashGap() {
		return divLineDashGap;
	}

	public Chart setDivLineDashGap(int divLineDashGap) {
		this.divLineDashGap = divLineDashGap;
		registerAtrributes.add("divLineDashGap");
		return this;
	}

	public int getZeroPlaneThickness() {
		return zeroPlaneThickness;
	}

	public Chart setZeroPlaneThickness(int zeroPlaneThickness) {
		this.zeroPlaneThickness = zeroPlaneThickness;
		registerAtrributes.add("zeroPlaneThickness");
		return this;
	}

	public int getAlternateHGridAlpha() {
		return alternateHGridAlpha;
	}

	public Chart setAlternateHGridAlpha(int alternateHGridAlpha) {
		this.alternateHGridAlpha = alternateHGridAlpha;
		registerAtrributes.add("alternateHGridAlpha");
		return this;
	}

	public int getNumVDivLines() {
		return numVDivLines;
	}

	public Chart setNumVDivLines(int numVDivLines) {
		this.numVDivLines = numVDivLines;
		registerAtrributes.add("numVDivLines");
		return this;
	}

	public int getVDivLineThickness() {
		return vDivLineThickness;
	}

	public Chart setVDivLineThickness(int divLineThickness) {
		vDivLineThickness = divLineThickness;
		registerAtrributes.add("vDivLineThickness");
		return this;
	}

	public int getVDivLineAlpha() {
		return vDivLineAlpha;
	}

	public Chart setVDivLineAlpha(int divLineAlpha) {
		vDivLineAlpha = divLineAlpha;
		registerAtrributes.add("vDivLineAlpha");
		return this;
	}

	public int getVDivLineDashLen() {
		return vDivLineDashLen;
	}

	public Chart setVDivLineDashLen(int divLineDashLen) {
		vDivLineDashLen = divLineDashLen;
		registerAtrributes.add("vDivLineDashLen");
		return this;
	}

	public int getVDivLineDashGap() {
		return vDivLineDashGap;
	}

	public Chart setVDivLineDashGap(int divLineDashGap) {
		vDivLineDashGap = divLineDashGap;
		registerAtrributes.add("vDivLineDashGap");
		return this;
	}

	public int getAlternateVGridAlpha() {
		return alternateVGridAlpha;
	}

	public Chart setAlternateVGridAlpha(int alternateVGridAlpha) {
		this.alternateVGridAlpha = alternateVGridAlpha;
		registerAtrributes.add("alternateVGridAlpha");
		return this;
	}

	public String getVDivLineColor() {
		return vDivLineColor;
	}

	public Chart setVDivLineColor(String divLineColor) {
		vDivLineColor = divLineColor;
		registerAtrributes.add("vDivLineColor");
		return this;
	}

	public String getAlternateVGridColor() {
		return alternateVGridColor;
	}

	public Chart setAlternateVGridColor(String alternateVGridColor) {
		this.alternateVGridColor = alternateVGridColor;
		registerAtrributes.add("alternateVGridColor");
		return this;
	}

	public boolean isReverseLegend() {
		return reverseLegend;
	}

	public Chart setReverseLegend(boolean reverseLegend) {
		this.reverseLegend = reverseLegend;
		registerAtrributes.add("registerAtrributes");
		return this;
	}

	public boolean isShowZeroPies() {
		return showZeroPies;
	}

	public Chart setShowZeroPies(boolean showZeroPies) {
		this.showZeroPies = showZeroPies;
		registerAtrributes.add("registerAtrributes");
		return this;
	}

	public boolean isShowPercentValues() {
		return showPercentValues;
	}

	public Chart setShowPercentValues(boolean showPercentValues) {
		this.showPercentValues = showPercentValues;
		registerAtrributes.add("showPercentValues");
		return this;
	}

	public boolean isShowPercentInToolTip() {
		return showPercentInToolTip;
	}

	public Chart setShowPercentInToolTip(boolean showPercentInToolTip) {
		this.showPercentInToolTip = showPercentInToolTip;
		registerAtrributes.add("showPercentInToolTip");
		return this;
	}

	public boolean isShowValues() {
		return showValues;
	}

	public Chart setShowValues(boolean showValues) {
		this.showValues = showValues;
		registerAtrributes.add("showValues");
		return this;
	}

	public String getLabelSepChar() {
		return labelSepChar;
	}

	public Chart setLabelSepChar(String labelSepChar) {
		this.labelSepChar = labelSepChar;
		registerAtrributes.add("labelSepChar");
		return this;
	}
}
