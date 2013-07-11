package brightmoon.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipTool {
	private static final int BUFFER = 2048;
	private int level;

	public ZipTool() {
		level = 0;
	}

	/**
	 * setLevel
	 * 
	 * @param level
	 *            int
	 */
	public void setLevel(int level) {
		this.level = level;

	}

	/**
	 * zip a File or Directory
	 * 
	 * @param inputFile
	 *            File
	 * @param outputFile
	 *            File
	 * @throws ZipException
	 */
	public void zipFile(File inputFile, File outputFile) throws ZipException {
		try {
			BufferedOutputStream bout = new BufferedOutputStream(
					new FileOutputStream(outputFile), BUFFER);
			ZipOutputStream out = new ZipOutputStream(bout);
			zip(out, inputFile, inputFile.getName());
			out.close();
		} catch (IOException ex) {
			throw new ZipException(ex.getMessage());
		}
	}

	/**
	 * zip some Files
	 * 
	 * @param inputFiles
	 *            File[]
	 * @param outputFile
	 *            File
	 * @throws ZipException
	 */
	public void zipFiles(File[] inputFiles, File outputFile)
			throws ZipException {
		try {
			BufferedOutputStream bout = new BufferedOutputStream(
					new FileOutputStream(outputFile), BUFFER);
			ZipOutputStream out = new ZipOutputStream(bout);
			for (int i = 0; i < inputFiles.length; i++) {
				zip(out, inputFiles[i], inputFiles[i].getName());
			}
			out.close();
		} catch (IOException ex) {
			throw new ZipException(ex.getMessage());
		}
	}

	/**
	 * unzip a File
	 * 
	 * @param inputFile
	 *            File
	 * @param outputFile
	 *            File
	 * @throws ZipException
	 */
	public void unZipFile(File inputFile, File outputFile) throws ZipException {
		try {
			FileInputStream tin = new FileInputStream(inputFile);
			CheckedInputStream cin = new CheckedInputStream(tin, new CRC32());
			BufferedInputStream bufferIn = new BufferedInputStream(cin, BUFFER);
			ZipInputStream in = new ZipInputStream(bufferIn);
			ZipEntry z = in.getNextEntry();

			while (z != null) {
				String name = z.getName();
				System.out.println(name);
				if (z.isDirectory()) {
					File f = new File(outputFile.getPath() + File.separator
							+ name);
					f.mkdir();
				} else {
					File f = new File(outputFile.getPath() + File.separator
							+ name);
					f.createNewFile();
					FileOutputStream out = new FileOutputStream(f);
					byte data[] = new byte[BUFFER];
					int b;

					while ((b = in.read(data, 0, BUFFER)) != -1) {
						out.write(data, 0, b);
					} 
					out.close();
				}
				z = in.getNextEntry();
			}

			in.close();
		}

		catch (IOException ex) {
			throw new ZipException(ex.getMessage());
		}

	}

	private void zip(ZipOutputStream out, File f, String base)
			throws FileNotFoundException, ZipException {
		if (level != 0) {
			out.setLevel(level);
		}
		if (f.isDirectory()) {
			zipDirectory(out, f, base);
		} else {
			if (base == null || "".equals(base)) {
				base = f.getName();
			}
			zipLeapFile(out, f, base);
		}

	}

	private void zipDirectory(ZipOutputStream out, File f, String base)
			throws FileNotFoundException, ZipException {
		File[] files = f.listFiles();
		if (level != 0) {
			out.setLevel(level);
		}
		try {
			out.putNextEntry(new ZipEntry(base + "/"));
		} catch (IOException ex) {
			throw new ZipException(ex.getMessage());
		}
		if (base == null || "".equals(base)) {
			base = new String();
		} else {
			base = base + "/";
		}

		for (int i = 0; i < files.length; i++) {
			zip(out, files[i], base + files[i].getName());
		} 
	}

	private void zipLeapFile(ZipOutputStream out, File f, String base)
			throws FileNotFoundException, ZipException {
		if (level != 0) {
			out.setLevel(level);
		}
		try {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			BufferedInputStream bufferIn = new BufferedInputStream(in, BUFFER);
			byte[] data = new byte[BUFFER];
			int b;
			while ((b = bufferIn.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, b);
			}
			bufferIn.close();
		} catch (IOException ex) {
			throw new ZipException(ex.getMessage());
		}
	}
}