package brightmoon.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 属性文件读取工具类.
 * @author lsq
 *
 */
public class PropUtil {
	private static Properties prop = new Properties();

	public static String getProperty(String fileName, String key) {
		try {
			InputStream in = PropUtil.class.getResourceAsStream(fileName);
			// .getResourceAsStream("/key.properties");
			prop.load(in);
			in.close();
		} catch (Exception e) {
			System.out.println("Error of create input stream");
		}
		String value = "";
		try {
			value = prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

}
