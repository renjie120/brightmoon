package com.renjie120.test;

import org.junit.Before;
import org.junit.Test;

import brightmoon.util.CoderUtil;

public class CoderUtilTest {
	String str;

	@Before
	public void init() {
		// str =
		// "如果你在某个领域有着自己的认识和经验想和大家分享和探讨，eoe社区非常欢迎你能将自己的知识和其他人分享，你可以联系我们提供你想教授的课程信息（包含课程大纲、联系方式和个人简介），我们会统一安排。联系方式：edu@eoemobile.com";
		str = "不同意";
	}

	@Test
	public void decryptBase64Test() throws Exception {
		String str2 = CoderUtil.encryptBase64(str);
		System.out.println("base64加密结果：" + str2);

		str2 = "c2FtcGxlIHRleHQ=";
		System.out.println("base64解密结果：" + CoderUtil.decryptBase64(str2));
	}

	@Test
	public void encryptShaTest() throws Exception {
		String str2 = CoderUtil.encryptSha(str);
		System.out.println("SHA加密结果：" + str2);
	}

	@Test
	public void encryptMd5Test() throws Exception {
		String str2 = CoderUtil.encryptMd5(str);
		System.out.println("MD5加密结果：" + str2);
	}

	@Test
	public void encryptHmacTest() throws Exception {
		String key = CoderUtil.initMackey();
		String str2 = CoderUtil.encryptHmac(str, key);
		System.out.println("HMAC加密结果：" + str2);
	}
}