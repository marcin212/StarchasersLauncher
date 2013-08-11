package pl.starchasers.launcher.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.UUID;

public class Config extends Properties{
	private static final long serialVersionUID = 1L;
	public Config(String path) {
		this.putAll(DefConfig());
		File f = new File(path);
		if (f.exists() && f.isFile()) {
			Reader reader;
			try {
				reader = new FileReader(f);
				load(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			store(path);
		}
		if(this.getProperty("clientToken") == ""){
			this.setProperty("clientToken", UUID.randomUUID().toString());
		}
	}
	public void store(String path) {
		File f = new File(path);
		try {
			store(new FileOutputStream(f), "Starchasers Config");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static Properties DefConfig() {
		Properties p = new Properties();
		p.setProperty("Width", "800");
		p.setProperty("Height", "600");
		p.setProperty("Xms", "512M");
		p.setProperty("Xmx", "1024M");
		p.setProperty("PermGen", "128M");
		p.setProperty("AddJVMArgs", "");
		p.setProperty("clientToken", "");
		p.setProperty("nickname","");
		p.setProperty("rememberPass", "false");
		return p;
	}
	public static void SaveConfig() {

	}
	public void setPropertyInt(String key, int value) {
		this.setProperty(key, Integer.toString(value));
	}
	public int getPropertyInt(String key, int def) {
		return Integer.parseInt(this.getProperty(key, Integer.toString(def)));
	}
	public int getPropertyInt(String key) {
		return Integer.parseInt(this.getProperty(key));
	}
}
