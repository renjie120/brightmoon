package brightmoon.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 解压，压缩文件.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class ZipCompressor {
	static final int BUFFER = 8192;

	private File zipFile;

	public ZipCompressor(String pathName) {
		zipFile = new File(pathName);
	}

	public static void main(String[] args) {
		// File f = new File("d:\\123\\fute.exe");

		 
		System.out.println("完毕!");
		// ZipCompressor zipCompressor = new ZipCompressor("d:\\123\\test.zip");
		// zipCompressor.compress("d:\\123\\fute.exe");
		// f.delete();

		// String file = "e:\\assets.zip";
		// ZipTool tool = new ZipTool();
		// try {
		// tool.unZipFile(new File(file), new File("e:\\test_asserts"));
		// } catch (ZipException e) {
		// e.printStackTrace();
		// }
	}

	public void disCompress(String srcZipFile, String dirName)
			throws IOException {
		FileInputStream fi = new FileInputStream(srcZipFile);
		// CheckedInputStream:需要维护所读取数据校验和的输入流。校验和可用于验证输入数据的完整性。
		CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
		ZipInputStream in2 = new ZipInputStream(csumi);
		BufferedInputStream bis = new BufferedInputStream(in2);

		ZipEntry ze;
		while ((ze = in2.getNextEntry()) != null) {
			System.out.println("Reading file " + ze);
			int x;
			while ((x = bis.read()) != -1)
				System.out.write(x);
		}
	}

	public void compress(String srcPathName) {
		File file = new File(srcPathName);
		if (!file.exists())
			throw new RuntimeException(srcPathName + "不存在！");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,
					new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			String basedir = "";
			compress(file, out, basedir);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			this.compressDirectory(file, out, basedir);
		} else {
			this.compressFile(file, out, basedir);
		}
	}

	/** 压缩一个目录 */
	private void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/** 压缩一个文件 */
	private void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			String tempString = new String(
					(basedir + file.getName()).getBytes("UTF-8"), "gbk");
			ZipEntry entry = new ZipEntry(tempString);
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}