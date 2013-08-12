package pl.starchasers.launcher;

import java.io.File;

import pl.starchasers.launcher.skin.frame.MainFrame;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Console;
import pl.starchasers.launcher.utils.Variable;

public class Main {
	public static void main(String[] args) {
		new Config(Variable.workingDir + "starchasers.properties");
		new MainFrame();
		
		new Console();
		
		File f = new File(Variable.workingDir);
		if(!f.exists()){
			f.mkdirs();
		}
		
		Config.instance.store(Variable.workingDir + "starchasers.properties");
		
	}
}