package pl.starchasers.launcher;

import java.io.File;

import pl.starchasers.launcher.skin.components.MyFrame;
import pl.starchasers.launcher.skin.frame.MainFrame;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Console;

public class Main {
	public static void main(String[] args) {
		new MainFrame();
		/*
		new Console();
		String workingDir = "./starchasers/";
		File f = new File(workingDir);
		if(!f.exists()){
			f.mkdirs();
		}
		Config config = new Config(workingDir + "starchasers.properties");
		config.store(workingDir + "starchasers.properties");
		*/
	}
}
