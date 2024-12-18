package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileUtils {

	public static BufferedReader getReader(String name) throws UnsupportedEncodingException, FileNotFoundException {
		return new BufferedReader(new InputStreamReader(new FileInputStream(getFileForName(name)), "UTF8"));
	}

	public static PrintWriter getPrintWriter(String name) throws UnsupportedEncodingException, FileNotFoundException {
		return new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFileForName(name)), "UTF8"));
	}

	public static File getFileForName(String name) {
		String dir = System.getProperty("user.dir");
		String sP = System.getProperty("file.separator");
		File dirProdavnica = new File(dir + sP+ "prodavnica");
		if (!dirProdavnica.exists()) {
			dirProdavnica.mkdirs();
		}
		File file = new File(dirProdavnica.getAbsolutePath() + sP+ name + ".csv");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
}
