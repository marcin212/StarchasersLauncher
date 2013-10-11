package pl.starchasers.launcher;

import java.io.File;

import pl.starchasers.launcher.launch.Launch;
import pl.starchasers.launcher.profiles.ProfileManager;
import pl.starchasers.launcher.skin.frame.ConsoleFrame;
import pl.starchasers.launcher.skin.frame.MainFrame;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Variable;

public class Main {
	private static ConsoleFrame console;
	private static ProfileManager profiles = new ProfileManager();
	private static MainFrame frame;
	private static Config conf;
	public static void main(String[] args) {
		
		
		
		testDir(Variable.workingDir);
		setConf(new Config(Variable.workingDir + "starchasers.properties"));
		setFrame(new MainFrame());
		if(conf.getProperty("console").equals("true")) setConsole(new ConsoleFrame());
		System.setOut(getConsole().getOut());
		System.setErr(getConsole().getOut());
		
		Launch.login();

		//Config.instance.store(Variable.workingDir + "starchasers.properties");

	}
	public static void testDir(String path){
		File f = new File(path);
		if(!f.exists()){
			f.mkdirs();
		}
	}
	public static MainFrame getFrame() {
		return frame;
	}
	public static void setFrame(MainFrame frame) {
		Main.frame = frame;
	}
	public static ConsoleFrame getConsole() {
		return console;
	}
	public static void setConsole(ConsoleFrame console) {
		Main.console = console;
	}
	public static ProfileManager getProfiles() {
		return profiles;
	}
	public static void setProfiles(ProfileManager profiles) {
		Main.profiles = profiles;
	}
	public static Config getConf() {
		return conf;
	}
	public static void setConf(Config conf) {
		Main.conf = conf;
	}

	
}
