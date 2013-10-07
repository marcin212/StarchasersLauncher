package pl.starchasers.launcher.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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
			//Thread.sleep(1000);
			store(new FileOutputStream(f), "Starchasers Config");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Properties DefConfig() {
		Properties p = new Properties();
		p.setProperty("Width", "800");
		p.setProperty("Height", "600");
		p.setProperty("clientToken", "");
		p.setProperty("playerUUID", "");
		p.setProperty("accessToken", "");
		p.setProperty("forceupdate", "true");
		p.setProperty("console", "true");
		p.setProperty("nickname","");
		return p;
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
