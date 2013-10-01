package pl.starchasers.launcher.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.components.ActionLabel;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Utils;
import pl.starchasers.launcher.utils.Variable;
import pl.starchasers.launcher.utils.json.Libraries;
import pl.starchasers.launcher.utils.json.Version;

public class LaunchWrapper {
	public static String Forgeversion;
	public LaunchWrapper(String token, Version ver,String name) {
		List<String> args = new ArrayList<String>();
		String separator = "";
		String systemik = System.getProperty("os.name").toLowerCase();
		if(systemik.contains("windows")) separator = ";";
		if(systemik.contains("linux")) separator = ":";
		args.add("java");
		args.add("-Xms" + Config.instance.getProperty("Xms"));
		args.add("-Xmx" + Config.instance.getProperty("Xmx"));
		args.add("-XX:MaxPermSize=" + Config.instance.getProperty("PermGen"));
		args.add("-Djava.library.path=./bin/natives/");
		String libsString = "";
		List<Libraries> libs = ver.getLibraries();
		Iterator<Libraries> it = libs.iterator();
		while (it.hasNext()) {
			
			String temp = it.next().getName();
			System.out.println(temp);
			if(temp.contains("debug")||temp.contains("platform")){
				continue;
			}
			String[] libparts = temp.split(":");
			String lib = libparts[0].replace(".", "/") + "/" + libparts[1] + "/" + libparts[2] + "/" + libparts[1] + "-" + libparts[2] + ".jar";
			libsString += "./libraries/" + lib + separator;
		}
		if(Config.instance.getProperty("vanilla").equals("false"))
			libsString += "./libraries/"+"net/minecraftforge/minecraftforge/"+Forgeversion+"/minecraftforge-universal-1.6.2-"+Forgeversion+".jar"+separator;
			
		
		libsString += "./bin/"+Variable.minecraftVersion+".jar";
		args.add("-cp");
		args.add(libsString);
		if(Config.instance.getProperty("vanilla").equals("false")){args.add("net.minecraft.launchwrapper.Launch");}else{args.add("net.minecraft.client.main.Main");}
		args.add("--username");
		args.add(name);
		args.add("--session");
		args.add(token);
		args.add("--version");
		args.add(Variable.minecraftVersion);
		args.add("--gameDir");
		args.add("./");
		args.add("--assetsDir");
		args.add("./assets");
		if(Config.instance.getProperty("vanilla").equals("false")){args.add("--tweakClass"); args.add("cpw.mods.fml.common.launcher.FMLTweaker");}

		String args2 = "";
		for(String s : args){
			args2 += s;
		}
		System.out.println(args2);
		try {
			ProcessBuilder pb =new ProcessBuilder(args).directory(new File("./starchasers/minecraft/"));
			Process p = pb.start();
			Utils.inheritIO(p.getInputStream(),Main.console.getOut());
			Utils.inheritIO(p.getErrorStream(),Main.console.getOut());
			Main.getFrame().setVisible(false);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
				ActionLabel.instance.setAction("");
				Main.getFrame().setVisible(true);
		}

	}
}
