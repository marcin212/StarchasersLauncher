package pl.starchasers.launcher.utils.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class MyFile {
	
	public static int fileSize(String path){
		try {
			return new FileInputStream(new File(path)).available();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
