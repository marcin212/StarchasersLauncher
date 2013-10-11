package pl.starchasers.launcher.utils;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Utils{

	public static String getSystem(){
		String system = System.getProperty("os.name").toLowerCase();
		if(system.contains("windows"))
			return "windows";
		if(system.contains("linux"))
			return "linux";
		if(system.contains("mac"))
			return "mac";
		return "error";
		
	}
	public static String getSeparator(){
		return System.getProperty("path.separator");	
	}
	public static void inheritIO(final InputStream src, final PrintStream dest) {
	   Thread a = new Thread(new Runnable() {
	        public void run() {
	            Scanner sc = new Scanner(src);
	            while (sc.hasNextLine()) {
	                dest.println(sc.nextLine());
	            }
	            sc.close();
	        }
	    });
	   a.setDaemon(true);
	   a.start();
	}
	
}
