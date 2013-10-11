package pl.starchasers.launcher.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.profiles.Profile;
import pl.starchasers.launcher.skin.panels.Contents;
import pl.starchasers.launcher.utils.Utils;
import pl.starchasers.launcher.utils.json.Libraries;
import pl.starchasers.launcher.utils.json.Version;
/*TODO
 * ostatnio wybrany profil
 */
public class LaunchWrapper {
	public static String Forgeversion;
	public Contents elements = Main.getFrame().getPanel(); 
	private Profile profile;
	private Version verInfo;
	private String separator = Utils.getSeparator(); 
	
	public LaunchWrapper(String token, Version ver,String name, Profile profile) {
		this.profile = profile;
		this.verInfo = ver;
		PrepareAndStartCommand(token, name);
	}
	
	public void PrepareAndStartCommand(String token,String name){
		List<String> command = new ArrayList<String>();
		command.add("java");
		command.add("-Xms" + profile.getXms());
		command.add("-Xmx" + profile.getXmx());
		command.add("-XX:MaxPermSize=" + profile.getPermgen());
		command.add("-Djava.library.path=./bin/natives/");
		command.add("-cp");
		command.add(addLibs());
		command.add(verInfo.getMainClass());
		String mcargs = verInfo.getMinecraftArguments();
		mcargs = mcargs.replace("${auth_player_name}", name);
		mcargs = mcargs.replace("${auth_session}",token);
		mcargs = mcargs.replace("${version_name}",profile.getMinecraftversion());
		mcargs = mcargs.replace("${game_directory}","./");
		mcargs = mcargs.replace("${game_assets}","../../assets");
		for(String element : mcargs.split(" ")){
			command.add(element);
		}
		startCommand(command);
		
	}
	private String addLibs(){
		String libsString = "";
		List<Libraries> libs = verInfo.getLibraries();
		Iterator<Libraries> it = libs.iterator();
		while (it.hasNext()) {
			String temp = it.next().getName();
			if(temp.contains("debug")||temp.contains("platform")){
				continue;
			}
			String[] libparts = temp.split(":");
			String lib = libparts[0].replace(".", "/") + "/" + libparts[1] + "/" + libparts[2] + "/" + libparts[1] + "-" + libparts[2] + ".jar";
			libsString += "../../libraries/" + lib + separator;
		}
		libsString += "./bin/"+profile.getMinecraftversion()+".jar";
		return libsString;
	}
	private void startCommand(List<String> command){
		try {
			ProcessBuilder pb =new ProcessBuilder(command).directory(new File("./starchasers/minecraft/instances/"+profile.getDir()));
			Process p = pb.start();
			Utils.inheritIO(p.getInputStream(),Main.getConsole().getOut());
			Utils.inheritIO(p.getErrorStream(),Main.getConsole().getOut());
			Main.getFrame().setVisible(false);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
				Main.getFrame().getPanel().getActionLabel().setAction("");
				Main.getFrame().setVisible(true);
		}
	}

}
