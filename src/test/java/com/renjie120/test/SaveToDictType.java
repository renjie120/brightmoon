package com.renjie120.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.record.Record;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import brightmoon.util.FileUtil;
import brightmoon.util.Util;

public class SaveToDictType {

	public static List<Record> getReport() {
		String dirName = "123.html";
		String str = new FileUtil.ReadFileBuilder(dirName).encoding("GBK")
				.build().readFileAsString();
		System.out.println("str===" + str);
		Document doc = Jsoup.parse(str);
		Element reportTable = doc.getElementById("reportTable");
		Elements heads = reportTable.getElementsByTag("thead").first()
				.getElementsByTag("th");
		List<String> types = new ArrayList<String>();
		List<Record> results = new ArrayList<Record>();
		for (int i = 2, j = heads.size(); i < j; i++) {
			Element td = heads.get(i);
			String s = td.text();
			types.add(s.substring(0, s.indexOf("(")));
		}

		Elements bodyContents = doc.getElementsByTag("tbody").first()
				.getElementsByTag("tr");
		for (Element e : bodyContents) {
			Elements tds = e.getElementsByTag("td");
			String year = "";
			String month = "";
			for (int i = 0, j = tds.size(); i < j; i++) {
				Element td = tds.get(i);
				if (i == 0) {
					year = td.getElementsByTag("a").text();
					if (Util.isEmpty(year))
						break;
				} else if (i == 1) {
					month = td.getElementsByTag("a").text();
					if (Util.isEmpty(month))
						break;
				} else {
					// Record record = new Record();
					// record.year = year;
					// record.month = month;
					// record.money = td.text();
					// record.type = types.get(i - 2);
					// results.add(record);
				}
			}
		}
		return results;
	}

	@Test
	public void chineseChangeTest() throws Exception {
		SaveToDictType.getReport();
	}

}
