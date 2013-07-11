package com.renjie120.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import brightmoon.jdbc.DataHandler;
import brightmoon.jdbc.MyDbUtil;
import brightmoon.util.NewJsonUtil;
import brightmoon.util.Util;
import brightmoon.web.HttpClientUtil;

public class DbTemplateTest {
	// @Test
	public void replaceFileTest() throws SQLException {
		MyDbUtil db = new MyDbUtil();

		int test = (int) (Double.parseDouble("" + Math.random() * 1000000));
		db.updateRecords("insert into hh values(" + test + ","
				+ System.currentTimeMillis() + "," + System.currentTimeMillis()
				+ ")");

		db.updateRecords("update hh set something = 44 where id = " + test);

		List ans = db.queryList("select * from hh", new DataHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Object[] objs = new Object[2];
				objs[0] = rs.getString(1);
				objs[1] = rs.getString(2);
				addRecord(objs);
			}
		});

		System.out.println(NewJsonUtil.javaToJson(ans).toString());
		if (ans != null)
			for (int i = 0, j = ans.size(); i < j; i++) {
				Object[] aa = (Object[]) ans.get(i);
				System.out.println(aa[0] + ",,,," + aa[1]);
			}
	}

	String exportOneYear = "select to_char(money_time,'yyyy-MM-dd'),money,money_type,money_desc  from money_detail_t t  where booktype=1 and useful=1 and to_char(t.money_time, 'yyyy') = ? and money <1000";

	// 将一年的数据全部保存到远程服务端！
	// @Test
	public void moneyTest() throws SQLException {
		MyDbUtil db = new MyDbUtil();
		List arg = new ArrayList();
		arg.add("2013");
		final StringBuffer buf = new StringBuffer();
		db.queryList(exportOneYear, arg, new DataHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Object[] objs = new Object[4];
				objs[0] = rs.getString(1);
				objs[1] = rs.getString(2);
				objs[2] = rs.getString(3);
				objs[3] = rs.getString(4);
				buf.append(rs.getString(1) + "$," + rs.getString(2) + "$,"
						+ rs.getString(3) + "$,"
						+ Util.notBlank(rs.getString(4)) + "$,0$;");
			}
		});
		StringBuffer result = buf;
		if (result.length() > 0) {
			HttpClientUtil util = new HttpClientUtil();
			Map params = new HashMap(10);
			params.put("method", "addMonyeFromPhone");
			params.put("arg", "{arg1:1}");
			String temp = result.substring(0, result.lastIndexOf("$"));
			System.out.println(temp);
			params.put("data", temp);
			System.out.println(""
					+ util.postUrlWithParams(
							"http://renjie120.com/management/syn!doAdd.do",
							params, true, "GBK"));

		} else
			System.out.println("没有数据");
		// System.out.println(NewJsonUtil.javaToJson(list).toString());
	}

	@Test
	public void dbTest() throws SQLException {
		MyDbUtil db = new MyDbUtil();
		List arg = new ArrayList();
		arg.add(Integer.parseInt("123"));
		arg.add("2222");
		String sql = " insert into comments values(?,?)";
		db.updateRecords(sql, arg);
//		int count = db.queryForInt("select count(1) from Process_Define");
//		System.out.println(count);
	}
}