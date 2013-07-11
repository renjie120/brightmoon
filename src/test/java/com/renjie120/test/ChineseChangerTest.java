package com.renjie120.test;

import java.sql.SQLException;

import org.junit.Test;

import brightmoon.util.ChineseChanger;

public class ChineseChangerTest {
	@Test
	public void replaceFileTest() throws SQLException {
		ChineseChanger change = new ChineseChanger();
		System.out.println(change.converterToSpell("汇明网络"));
		String test = "出差申请";
		System.out.println(test.length());
		System.out.println(change.converterToSpell(test));
	}
}
