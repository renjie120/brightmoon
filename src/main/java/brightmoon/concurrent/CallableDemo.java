package brightmoon.concurrent;

import java.io.File;
import java.util.concurrent.Callable;

import brightmoon.util.FileUtil;
import brightmoon.web.HttpClientUtil;

/**
 * 一个demo，将远程url的内容保存到本地文件，结果返回为本地文件路径.
 * 
 * @author lsq
 * 
 */
public class CallableDemo implements Callable<String> {
	private String url;
	private String fileName;

	public CallableDemo(String url, String fileName) {
		this.url = url;
		this.fileName = fileName;
	}

	public String call() throws Exception {
		String content = HttpClientUtil.getUrl(url);
		new FileUtil.WriteFileBuilder(fileName).build().writeFile(content);
		File f = new File(fileName);
		return "保存文件为：" + f.getAbsolutePath();
	}
}
