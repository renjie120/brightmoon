package brightmoon.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.JavaIdentifierTransformer;

/**
 * 使用json-lib进行java与json串的转换。 序列化与反序列化.
 * 
 * @author lsq
 * 
 */
public class NewJsonUtil {
	/**
	 * 将一个Java对象转换为JSON对象.
	 * 
	 * @param c
	 * @return
	 */
	public static JSON javaToJson(Object c) {
		return JSONSerializer.toJSON(c);
	}

	/**
	 * 将一个数组转换为JSON对象.
	 * 
	 * @param c
	 * @return
	 */
	public static JSON arrToJson(Object c) {
		return JSONArray.fromObject(c);
	}

	/**
	 * json字符串反序列化为java对象.
	 * 
	 * @param json
	 * @return
	 */
	public static Object jsonToJava(String json, Type clazz) {
		 	JSONObject obj = JSONObject.fromObject(json);
			return jsonToJava(obj,clazz);
	}

	/**
	 * 将json对象转换为java对象.
	 * @param obj
	 * @param clazz
	 * @return
	 */
	public static Object jsonToJava(JSONObject obj, Type clazz) {
		try {
			JsonConfig config = new JsonConfig();
			// 对json的属性中的首字母进行处理
			config.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {
				@Override
				public String transformToJavaIdentifier(String str) {
					char[] chars = str.toCharArray();
					chars[0] = Character.toLowerCase(chars[0]);
					if(chars.length>1)
						chars[1] = Character.toLowerCase(chars[1]);
					return new String(chars);
				}
			});
			// 处理嵌套java类型的情况.
			config.setRootClass(Class.forName(((Class<?>) clazz).getName()));
			return JSONObject.toBean(obj, config);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * json字符串反序列化为复杂的java对象.
	 * 
	 * @param json
	 * @param clazz
	 * @param classMap
	 * @return
	 */
	public static Object jsonToJava(String json, Class clazz, Map classMap) {
		return JSONObject.toBean(JSONObject.fromObject(json), clazz, classMap);
	}

	/**
	 * 将json串转换为对象数组.
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static Object jsonToArr(String json, Class clazz) {
		return JSONArray.toArray(JSONArray.fromObject(json), clazz);
	}

	/**
	 * json转换为对象数组.
	 * 
	 * @param json
	 * @return
	 */
	public static Object jsonToArr(String json) {
		return JSONArray.toArray(JSONArray.fromObject(json));
	}

	/**
	 * json转换为List.
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static List jsonToList(String json, Class clazz) {
		return JSONArray.toList(JSONArray.fromObject(json), clazz);
	}

	public static void main(String[] args) {
//		ApiProcess<TBase_Session> sss2 = new ApiProcess<TBase_Session>() {
//		};
//
//		String js = "{\"PassportID\":\"f03cb196-6d77-40f5-afa7-5ee264d5450a\",\"AccountID\":\"cc194f51-7395-478e-9243-3cb8760d608a\",\"AppID\":\"4a935fa4-4f55-4b01-89e2-d2f0b6e8ba39\",\"passport\":\"wangzhen@hvming.com\",\"LoginTime\":\"2012/12/12 13:01:37\",\"LoginIP\":\"WIN-KAMLO3HGEPT\",\"Expire\":\"2112/12/12 13:01:37\",\"ActiveMinutes\":0,\"Status\":1,\"SessionStatus\":1,\"LoginCount\":48,\"WebServerIP\":\"192.168.93.114\",\"RequestUrl\":\"\",\"LastVisit\":\"2012/12/12 13:01:37\",\"Name\":\"\",\"ID\":\"17facefd-b1cf-400b-afb7-a93e0efabdd3\"}";
//		TBase_Session sss = (TBase_Session) JsonUtil.jsonToJava(js,
//				TBase_Session.class);
//		System.out.println(sss.getPassport());
	}
}
