package pl.starchasers.launcher.launch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pl.starchasers.launcher.skin.components.LoginTextField;
import pl.starchasers.launcher.skin.components.MyFrame;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.json.Libraries;
import pl.starchasers.launcher.utils.json.Version;

public class LaunchWrapper {
	public LaunchWrapper(String token, Version ver) {
		List<String> args = new ArrayList<String>();
		args.add("java ");
		args.add("-Xms" + Config.instance.getProperty("Xms"));
		args.add(" -Xmx" + Config.instance.getProperty("Xmx"));
		args.add(" -XX:MaxPermSize=" + Config.instance.getProperty("PermGen"));
		args.add(" -Djava.library.path=.\\starchasers\\minecraft\\bin\\natives\\");
		String libsString = " ";
		List<Libraries> libs = ver.getLibraries();
		Iterator<Libraries> it = libs.iterator();
		while (it.hasNext()) {
			String temp = it.next().getName();
			if(temp.contains("debug")||temp.contains("platform")){
				continue;
			}
			String[] libparts = temp.split(":");
			String lib = libparts[0].replace(".", "\\") + "\\" + libparts[1] + "\\" + libparts[2] + "\\" + libparts[1] + "-" + libparts[2] + ".jar";
			libsString += ".\\starchasers\\minecraft\\libraries\\" + lib + ";";
		}
		
		libsString += ".\\starchasers\\minecraft\\libraries\\"+"org\\ow2\\asm\\asm-all\\4.1\\asm-all-4.1.jar;";	
		libsString += ".\\starchasers\\minecraft\\libraries\\"+"lzma\\lzma\\0.0.1\\lzma-0.0.1.jar;";
		libsString += ".\\starchasers\\minecraft\\libraries\\"+"net\\minecraft\\launchwrapper\\1.3\\launchwrapper-1.3.jar;";
		libsString += ".\\starchasers\\minecraft\\libraries\\"+"net\\minecraftforge\\minecraftforge\\9.10.0.828\\minecraftforge-9.10.0.828.jar;";	
		libsString += ".\\starchasers\\minecraft\\bin\\minecraft.jar net.minecraft.launchwrapper.Launch";//net.minecraft.client.main.Main";
		args.add(" -cp " + libsString);
		args.add(" --username " + LoginTextField.instance.getText());
		args.add(" --session " + token);
		args.add(" --version 1.6.2");
		args.add(" --gameDir .\\starchasers\\minecraft\\");
		args.add(" --assetsDir .\\starchasers\\minecraft\\assets");
		
		args.add(" --tweakClass cpw.mods.fml.common.launcher.FMLTweaker");
		/*String args ="javaw -XX:MaxPermSize="
				+ Config.instance.getProperty("PermGen")
				+ " -Xms"
				+ Config.instance.getProperty("Xms").toString()
				+ " -Xmx"
				+ Config.instance.getProperty("Xmx").toString()
				+ " -Djava.library.path=.\\starchasers\\minecraft\\bin\\natives -cp .\\starchasers\\minecraft\\libraries\\net\\sf\\jopt-simple\\jopt-simple\\4.5\\jopt-simple-4.5.jar;.\\starchasers\\minecraft\\libraries\\com\\paulscode\\codecjorbis\\20101023\\codecjorbis-20101023.jar;.\\starchasers\\minecraft\\libraries\\com\\paulscode\\codecwav\\20101023\\codecwav-20101023.jar;.\\starchasers\\minecraft\\libraries\\com\\paulscode\\libraryjavasound\\20101123\\libraryjavasound-20101123.jar;.\\starchasers\\minecraft\\libraries\\com\\paulscode\\librarylwjglopenal\\20100824\\librarylwjglopenal-20100824.jar;.\\starchasers\\minecraft\\libraries\\com\\paulscode\\soundsystem\\20120107\\soundsystem-20120107.jar;.\\starchasers\\minecraft\\libraries\\argo\\argo\\2.25_fixed\\argo-2.25_fixed.jar;.\\starchasers\\minecraft\\libraries\\org\\bouncycastle\\bcprov-jdk15on\\1.47\\bcprov-jdk15on-1.47.jar;.\\starchasers\\minecraft\\libraries\\com\\google\\guava\\guava\\14.0\\guava-14.0.jar;.\\starchasers\\minecraft\\libraries\\org\\apache\\commons\\commons-lang3\\3.1\\commons-lang3-3.1.jar;.\\starchasers\\minecraft\\libraries\\commons-io\\commons-io\\2.4\\commons-io-2.4.jar;.\\starchasers\\minecraft\\libraries\\net\\java\\jinput\\jinput\\2.0.5\\jinput-2.0.5.jar;.\\starchasers\\minecraft\\libraries\\net\\java\\jutils\\jutils\\1.0.0\\jutils-1.0.0.jar;.\\starchasers\\minecraft\\libraries\\com\\google\\code\\gson\\gson\\2.2.2\\gson-2.2.2.jar;.\\starchasers\\minecraft\\libraries\\org\\lwjgl\\lwjgl\\lwjgl\\2.9.0\\lwjgl-2.9.0.jar;.\\starchasers\\minecraft\\libraries\\org\\lwjgl\\lwjgl\\lwjgl_util\\2.9.0\\lwjgl_util-2.9.0.jar;.\\starchasers\\minecraft\\bin\\minecraft.jar net.minecraft.client.main.Main "
				+ "--username "
				+ LoginTextField.instance.getText()
				+ " --session "
				+ token
				+ " --version 1.6.2 --gameDir .\\starchasers\\minecraft --assetsDir .\\starchasers\\minecraft\\assets";*/
		Runtime r = Runtime.getRuntime();
		String args2 = "";
		for(String s : args){
			args2 += s;
		}
		System.out.println(args2);
		try {
			final Process p = r.exec(args2);
			//MyFrame.instance.setVisible(false);
			final BufferedReader stderr = new BufferedReader(
					new InputStreamReader(p.getErrorStream()));
			final BufferedReader stdout = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
			final Runnable r2 = new Runnable() {
				public void run() {
					while (true) {
						try {
							if (p.getErrorStream().available() != 0) {
								System.out.println(stderr.readLine());
							}
							if (p.getInputStream().available() != 0) {
								System.out.println(stdout.readLine());
							}
							Thread.sleep(100);
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
				srt.interrupt();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
