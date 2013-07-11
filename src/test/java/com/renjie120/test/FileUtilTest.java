package com.renjie120.test;

import org.junit.Test;

import brightmoon.util.FileUtil;
import brightmoon.util.Util;

public class FileUtilTest {

	// @Test
	public void appendTest() {
		String dirName = "e:\\FileList.txt";
		new FileUtil.WriteFileBuilder(dirName).encoding("GBK").build()
				.appendFile("你好，这是一个测试字符串");
	}

	@Test
	public void mySpringTest() throws Exception {
		String dirName = "d:\\test";
		String gbkFile = "d:\\test\\test-gbk.java";
		String utf8File = "d:\\test\\test-utf8.java";
		String content = "你好，这是一个测试字符串";
		// // 向指定文件写入字符串,默认为utf8格式
		// new
		// FileUtil.WriteFileBuilder("d:\\test\\test-utf8.java").create(true)
		// .build().writeFile(content);
		//
		new FileUtil.WriteFileBuilder("d:\\test\\test-gbk.java").create(true)
				.encoding("GBK").build().writeFile(content);
		//
		// // 输出一个目录下面的全部文件
		// System.out.println(FileUtil.allFilesInDir(dirName));
		// //
		// // // 按照指定编码读取指定数量的行数
		// Util.printStrArr(new
		// FileUtil.ReadFileBuilder(gbkFile).encoding("GBK")
		// .maxRow(20).build().readFile());
		// Util.printStrArr(new FileUtil.ReadFileBuilder(utf8File).maxRow(20)
		// .build().readFile());
		// //
		// // // 读取指定文件的编码
		// System.out.println(new FileUtil.ReadFileBuilder(gbkFile)
		// .encoding("GBK").build().readFile());
		// System.out.println(new FileUtil.ReadFileBuilder(utf8File).build()
		// .readFile());

		// 将指定文件以新的编码方式替换旧的编码方式.
		new FileUtil.ChangeFileCodingBuilder(dirName, "GBK", "utf-8")
				.fileFilter("\\.(java)$").build().changeCoding();

		// 将指定文件中的指定字符串用新串替换.
		// FileUtil.replaceFilesAnyStr(dirName, "myOwnLibrary", "brightmoon",
		// "utf-8");
	}
}
