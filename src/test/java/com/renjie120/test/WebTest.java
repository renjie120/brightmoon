package com.renjie120.test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import brightmoon.web.HttpClientUtil;
import brightmoon.web.MultiProcess;
import brightmoon.web.UrlClientUtil;

public class WebTest {

	String bookType = "1";
	String pass = "870420";
	// String traveler = "1";
	static String BIADU = "http://www.baidu.com";
	static String LOGIN = "http://127.0.0.1:1988/NewHibernateMoney/login!login.action";
	static String GUPIAO = "http://money.finance.sina.com.cn/corp/go.php/vFD_BalanceSheet/stockid/600031/ctrl/part/displaytype/4.phtml";
	static String TUPIAN = "https://raw.github.com/renjie120/group_sms/master/res/drawable/logo.png";

	// @Test
	public void urlConnectTest() {
		String str = UrlClientUtil.getDocumentAt(LOGIN, "GBK");
		System.out.println(str);

		String hangye = "http://stock.eastmoney.com/hangye.html";
		String str2 = UrlClientUtil.getDocumentAt(hangye);
		System.out.println(str2);
	}

	// @Test
	public void httpClientGetTest() {
		HttpClientUtil util = new HttpClientUtil();
		String zijinliuru = "http://hq.sinajs.cn/list=s_sh600718";
		System.out.println(util.getUrl(zijinliuru, "GBK"));

	}

	// @Test
	public void getFileTest() {
		HttpClientUtil.getFile(TUPIAN, "d:\\test.png");
	}

	// @Test
	public void threadHttpClientGetTest() {
		String zijinliuru = "http://hq.sinajs.cn/list=s_sh600718";
		String zijinliuru2 = "http://hq.sinajs.cn/list=s_sh000001";
		System.out.println(HttpClientUtil.getUrl(zijinliuru, "GBK"));
		System.out.println(HttpClientUtil.getUrl(zijinliuru2, "GBK"));
	}

	// @Test
	public void httpClientPostTest() {
		HttpClientUtil util = new HttpClientUtil();
		util.postUrl(LOGIN, "GBK");
		System.out.println(util.getStauts(LOGIN, 1));

	}

	@Test
	public void MultiProcessTest() throws InterruptedException {
		CountDownLatch count = new CountDownLatch(2);
		new Thread(
				new MultiProcess(
						"http://money.finance.sina.com.cn/corp/go.php/vFD_BalanceSheet/stockid/600031/ctrl/part/displaytype/4.phtml",
						0, count, 0)).start();
		new Thread(
				new MultiProcess(
						"http://money.finance.sina.com.cn/corp/go.php/vFD_BalanceSheet/stockid/600031/ctrl/part/displaytype/4.phtml",
						1, count, 0)).start();
		// 如果不加下面的进行等待的话，因为主线程main已经结束，而上面的多线程也就被迫结束，而不会出现结果了！！
		// 奇怪的是，在test模块中才存在这个问题，在main里面不会有这样的问题。
		while (true) {
			if (count.getCount() < 1)
				break;
			Thread.sleep(50);
		}
	}

	// @Test
	public void postUrlWithParamsTest() {
		HttpClientUtil util = new HttpClientUtil();
		Map params = new HashMap(10);
		params.put("method", "addMonyeFromPhone");
		params.put("arg", "{arg1:0}");
		params.put("data", "2014-11-4$,444$,所得税$,$,0$");
		System.out.println(""
				+ util.postUrlWithParams(
						"http://renjie120.com/management/syn!doAdd.do", params,
						true, "GBK"));

		params.put("method", "getAllNewMoneys");
		params.put("arg", "{arg1:1,arg2:1,arg3:3}");
		System.out.println(""
				+ util.postUrlWithParams(
						"http://renjie120.com/management/syn!doAdd.do", params,
						true, "GBK"));

		// params.put("method", "updateAllNewMoneys");
		// params.put("arg", "{arg1:0,arg2:2}");
		// System.out.println(""+util.postUrlWithParams(
		// "http://renjie120.com/management/syn!doAdd.do", params, true,"GBK"));

	}

	// @Test
	public void postUrlWithParamsWithSessionTest() {
		HttpClientUtil util = new HttpClientUtil();
		String bookType = "1";
		String pass = "870420";
		String traveler = "0";
		Map params = new HashMap(10);
		params.put("bookType", bookType);
		params.put("pass", pass);
		// params.put("traveler", traveler);
		String queryDetail = "http://127.0.0.1:1988/NewHibernateMoney/grid!initMoneyDetailGrid.action";
		String queryReport = "http://127.0.0.1:1988/NewHibernateMoney/detailReport!reportDetail.action";
		String test3 = "http://hq.sinajs.cn/list=s_sh600000";
		String test4 = "http://view.news.qq.com/zt2012/ldbf/index.htm?pgv_ref=aio2012&ptlang=2052";
		// 登录
		HttpClientUtil.postUrlWithParams(LOGIN, params);
		// HttpClientUtil.postUrlWithParams(LOGIN, params);
		System.out.println(HttpClientUtil.getUrl(queryReport));
	}

	// @Test
	public void getUrl2Test() throws Exception {
		HttpClientUtil util = new HttpClientUtil();
		System.out.println(util.getUrl("http://www.baidu.com"));
	}

	// @Test
	public void peekUrlTest() {
		HttpClientUtil util = new HttpClientUtil();
		util.peekUrl("http://www.baidu.com");
	}

}
