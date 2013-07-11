package brightmoon.flashchart;

public class Style extends FlashChartBase implements IBaseTag {
	private String name;
	private String type;
	private String param;

	public FlashChartBase end() {
		getStringBuffer().append("/>");
		return this;
	}

	public String getName() {
		return name;
	}

	public Style setName(String name) {
		this.name = name;
		getRegisterAtrributes().add("name");
		return this;
	}

	public String getType() {
		return type;
	}

	public Style setType(String type) {
		this.type = type;
		getRegisterAtrributes().add("type");
		return this;
	}

	public String getParam() {
		return param;
	}

	public Style setParam(String param) {
		this.param = param;
		getRegisterAtrributes().add("param");
		return this;
	}

	public FlashChartBase start() {
		getStringBuffer().append("<style ");
		return this;
	}

	public String toString() {
		start();
		if (getRegisterAtrributes().contains("name"))
			this.add("name=").addValue(name);
		if (getRegisterAtrributes().contains("type"))
			this.add("type=").addValue(type);
		if (getRegisterAtrributes().contains("param"))
			this.add("param=").addValue(param);
		end();
		return getString();
	}
}
