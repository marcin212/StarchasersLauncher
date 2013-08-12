package pl.starchasers.launcher.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
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
						+ entry.getName().substring(entry.getName().lastIndexOf("/")+1)));
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
	static public void extractFolder(String zipFile, String newPath) throws ZipException, IOException 
	{
	    System.out.println(zipFile);
	    int BUFFER = 2048;
	    File file = new File(zipFile);

	    ZipFile zip = new ZipFile(file);
	    //String newPath = zipFile.substring(0, zipFile.length() - 4);

	    new File(newPath).mkdir();
	    Enumeration zipFileEntries = zip.entries();

	    // Process each entry
	    while (zipFileEntries.hasMoreElements())
	    {
	        // grab a zip file entry
	        ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
	        String currentEntry = entry.getName();
	        File destFile = new File(newPath, currentEntry);
	        //destFile = new File(newPath, destFile.getName());
	        File destinationParent = destFile.getParentFile();

	        // create the parent directory structure if needed
	        destinationParent.mkdirs();

	        if (!entry.isDirectory())
	        {
	            BufferedInputStream is = new BufferedInputStream(zip
	            .getInputStream(entry));
	            int currentByte;
	            // establish buffer for writing file
	            byte data[] = new byte[BUFFER];

	            // write the current file to disk
	            FileOutputStream fos = new FileOutputStream(destFile);
	            BufferedOutputStream dest = new BufferedOutputStream(fos,
	            BUFFER);

	            // read and write until last byte is encountered
	            while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
	                dest.write(data, 0, currentByte);
	            }
	            dest.flush();
	            dest.close();
	            is.close();
	        }

	        if (currentEntry.endsWith(".zip"))
	        {
	            // found a zip file, try to open
	            extractFolder(destFile.getAbsolutePath(),newPath);
	        }
	    }
	}
	
	
}
