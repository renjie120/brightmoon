package com.renjie120.test;

import org.junit.Test;

import brightmoon.util.PropUtil;

public class PropUtilTest {
	@Test
	public void readTest() throws Exception {
		// 地址默认是class的同级目录。。
		System.out
				.println(PropUtil.getProperty("/db.properties", "driverName"));
	}
}
