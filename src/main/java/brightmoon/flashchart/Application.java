package brightmoon.flashchart;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Application
 * @Description: Application标签
 * @author 419723443@qq.com
 * @date Jan 22, 2010 2:31:32 PM
 * 
 */
public class Application extends FlashChartBase implements IBaseTag,
		IComplicateTag {
	private List applies;

	/**
	 * 添加子标签Apply
	 * 
	 * @param apply
	 */
	public void addApply(Apply apply) {
		if (applies == null)
			applies = new ArrayList();
		applies.add(apply);
	}

	public FlashChartBase end() {
		getStringBuffer().append(">");
		return this;
	}

	public FlashChartBase start() {
		getStringBuffer().append("<application ");
		return this;
	}

	public FlashChartBase endTag() {
		getStringBuffer().append("</application>");
		return this;
	}

	public String toString() {
		start();
		end();
		if (applies != null) {
			for (int i = 0, j = applies.size(); i < j; i++) {
				this.add(applies.get(i));
			}
		}
		return this.endTag().getString();
	}
}
