package pl.starchasers.launcher.launch;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pl.starchasers.launcher.skin.components.ActionLabel;
import pl.starchasers.launcher.skin.components.MyFrame;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Variable;
import pl.starchasers.launcher.utils.json.Libraries;
import pl.starchasers.launcher.utils.json.Version;

public class LaunchWrapper {
	@SuppressWarnings("deprecation")
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
			if(temp.contains("debug")||temp.contains("platform")){
				continue;
			}
			String[] libparts = temp.split(":");
			String lib = libparts[0].replace(".", "/") + "/" + libparts[1] + "/" + libparts[2] + "/" + libparts[1] + "-" + libparts[2] + ".jar";
			libsString += "./libraries/" + lib + separator;
		}
		
		libsString += "./libraries/"+"org/ow2/asm/asm-all/4.1/asm-all-4.1.jar"+separator;	
		libsString += "./libraries/"+"lzma/lzma/0.0.1/lzma-0.0.1.jar"+separator;
		libsString += "./libraries/"+"net/minecraftforge/minecraftforge/9.10.0.828/minecraftforge-9.10.0.828.jar"+separator;
		libsString += "./libraries/"+"net/minecraft/launchwrapper/1.3/launchwrapper-1.3.jar"+separator;
		libsString += "./bin/"+Variable.minecraftVersion+".jar";
		args.add("-cp");
		args.add(libsString);
		args.add("net.minecraft.launchwrapper.Launch");
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
		args.add("--tweakClass");
		args.add("cpw.mods.fml.common.launcher.FMLTweaker");

		
		//Runtime r = Runtime.getRuntime();
		String args2 = "";
		for(String s : args){
			args2 += s;
		}
		System.out.println(args2);
		try {
			
			final Process p =new ProcessBuilder(args).directory(new File("./starchasers/minecraft/")).start(); //r.exec(args2,envp);
			MyFrame.instance.setVisible(false);
			final BufferedReader stderr = new BufferedReader(
					new InputStreamReader(p.getErrorStream()));
			final BufferedReader stdout = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
			final Runnable r2 = new Runnable() {
				public void run() {
					while (true) {
						try {
								Thread.sleep(1);
							if (p.getErrorStream().available() != 0) {
								System.out.println(stderr.readLine());
							}
							if (p.getInputStream().available() != 0) {
								System.out.println(stdout.readLine());
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			};
			Thread srt = new Thread(r2);
			srt.start();
			try {
				System.out.println(p.waitFor());
				srt.stop();
				ActionLabel.instance.setAction("");
				MyFrame.instance.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
