package pl.starchasers.launcher;

import java.io.File;

import pl.starchasers.launcher.launch.Launch;
import pl.starchasers.launcher.profiles.ProfileManager;
import pl.starchasers.launcher.skin.frame.ConsoleFrame;
import pl.starchasers.launcher.skin.frame.MainFrame;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Variable;

public class Main {
	public static ConsoleFrame console;
	public static ProfileManager profiles = new ProfileManager();
	public static void main(String[] args) {
		File f = new File(Variable.workingDir);
		if(!f.exists()){
			f.mkdirs();
		}
		new Config(Variable.workingDir + "starchasers.properties");
		new MainFrame();
		console = new ConsoleFrame();
		System.setOut(Main.console.getOut());
		System.setErr(Main.console.getOut());
		//new ProfileManager();
		Launch.login();

		//Config.instance.store(Variable.workingDir + "starchasers.properties");

	}
}
