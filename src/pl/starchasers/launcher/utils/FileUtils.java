package pl.starchasers.launcher.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtils {
	@SuppressWarnings("rawtypes")
	public static void unzip(String filePath, String unzipPath) {
		Enumeration entries;
		ZipFile zipFile;
		BufferedOutputStream out = null;
		try {
			zipFile = new ZipFile(filePath);
			entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				if (entry.isDirectory()
						|| entry.getName().startsWith("META-INF")) {
					continue;
				}
				out = new BufferedOutputStream(new FileOutputStream(unzipPath
						+ entry.getName()));
				byte[] buffer = new byte[1024];
				int len;
				while ((len = zipFile.getInputStream(entry).read(buffer)) >= 0) {
					out.write(buffer, 0, len);
				}
			}
			zipFile.close();
			out.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
			return;
		}
	}
	public static void unzipNatives(String filePath, String unzipPath) {
		Enumeration entries;
		ZipFile zipFile;
		BufferedOutputStream out = null;
		try {
			zipFile = new ZipFile(filePath);
			entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				if (entry.isDirectory()
						|| entry.getName().startsWith("META-INF")) {
					continue;
				}
				out = new BufferedOutputStream(new FileOutputStream(unzipPath
						+ entry.getName().substring(entry.getName().lastIndexOf("/"))));
				byte[] buffer = new byte[1024];
				int len;
				while ((len = zipFile.getInputStream(entry).read(buffer)) >= 0) {
					out.write(buffer, 0, len);
				}
			}
			zipFile.close();
			out.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
			return;
		}
	}
}
