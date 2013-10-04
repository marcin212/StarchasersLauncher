package pl.starchasers.launcher.launch;

import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.panels.Contents;
import pl.starchasers.launcher.sync.DownloadFile;
import pl.starchasers.launcher.sync.DownloadJob;
import pl.starchasers.launcher.sync.Sync;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Http;
import pl.starchasers.launcher.utils.Variable;
import pl.starchasers.launcher.utils.json.Libraries;
import pl.starchasers.launcher.utils.json.MinecraftJson;

public class CheckFiles {
	private Contents components = Main.getFrame().getPanel();
	CheckFiles() {
		components.getActionLabel().setAction("checking files...");
		List<Libraries> libraries = MinecraftJson.instance.getObjForVersion(Variable.minecraftVersion).getLibraries();
		downloadBin();
		downloadLibraries(libraries);
			if(Config.instance.getProperty("vanilla").equals("false")){
			downloadForge();
			new Sync();		
		}
			components.getActionLabel().setAction("downloading files...");
		DownloadJob.downloadJob();
		components.getActionLabel().setZero();
	}

		
	private void downloadBin() {
		String bindir = "./starchasers/minecraft/bin";
		File f = new File(bindir);
		if (!f.exists()) f.mkdirs();
		f = new File(bindir + "/" + Variable.minecraftVersion+".jar");
		//System.out.println("http://s3.amazonaws.com/Minecraft.Download/versions/"+Variable.minecraftVersion+"/"+Variable.minecraftVersion+".jar"+":"+Http.getRemoteSize("http://s3.amazonaws.com/Minecraft.Download/versions/"+Variable.minecraftVersion+"/"+Variable.minecraftVersion+".jar") +":"+ f.length());
		if(!f.exists() || (Config.instance.getProperty("forceupdate").compareTo("true")==0 ?(Http.getRemoteSize("http://s3.amazonaws.com/Minecraft.Download/versions/"+Variable.minecraftVersion+"/"+Variable.minecraftVersion+".jar") != f.length()):false))
			DownloadJob.getList().add(new DownloadFile(
					"http://s3.amazonaws.com/Minecraft.Download/versions/"+Variable.minecraftVersion+"/"+Variable.minecraftVersion+".jar",
					"./starchasers/minecraft/bin"));
	}

	private void downloadLibraries(List<Libraries> libraries) {
		
		for (Libraries actLib : libraries) {
			if (actLib.getName().contains("debug"))
				continue;
			
			if (actLib.getNatives() == null) {
				String[] parts = actLib.getName().split(":");
				String dlpath = parts[0].replace('.', '/') + "/" + parts[1] + "/" + parts[2] + "/";
				String localPath = dlpath;
				File f2 = new File("./starchasers/minecraft/libraries/" + dlpath);
				
				if (!f2.exists()) f2.mkdirs();
				
				dlpath += parts[1] + "-" + parts[2] + ".jar";
				String temp = (actLib.getUrl()==null ? Variable.librariesURL: actLib.getUrl()) + dlpath;
				File f= new File("./starchasers/minecraft/libraries/"+dlpath);
				//System.out.println(dlpath+":"+temp+":"+Http.getRemoteSize(temp)+":"+f.length());
				
				if( !f.exists() || (Config.instance.getProperty("forceupdate").compareTo("true")==0 ? (Http.getRemoteSize(temp) != f.length()):false ) ){
					DownloadJob.getList().add(new DownloadFile(temp,"./starchasers/minecraft/libraries/"+localPath));
				}
				
			} else {

				String osname = System.getProperty("os.name"), nativesString = null;
				if (osname.toLowerCase().contains("windows")) {
					nativesString = "natives-windows";
				} else if (osname.toLowerCase().contains("linux")) {
					nativesString = "natives-linux";
				} else if (osname.toLowerCase().contains("os")) {
					nativesString = "natives-osx";
				} else {
					Object[] offlineButton = { "Windows", "Linux", "OS X" };
					int n = JOptionPane
							.showOptionDialog(
									null,
									"Couldn't detect your operating system, select from below:",
									"Couldn't detect OS!",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.WARNING_MESSAGE, null,
									offlineButton, offlineButton[0]);
					switch (n) {
					case 0:
						nativesString = "natives-windows";
						break;
					case 1:
						nativesString = "natives-linux";
						break;
					case 2:
						nativesString = "natives-osx";
						break;
					}
				}
				String[] parts = actLib.getName().split(":");
				String dlpath = parts[0].replace('.', '/') + "/" + parts[1] + "/" + parts[2] + "/";
				File f2 = new File("./starchasers/minecraft/bin/natives/" + dlpath);
				if (!f2.exists()) f2.mkdirs();
				String dlpath2 = dlpath+".";
				dlpath += parts[1] + "-" + parts[2] + "-" + nativesString + ".jar";
				String name = "./starchasers/minecraft/bin/natives/" + dlpath;
				File f= new File(name);
				//System.out.println(name+":"+Variable.librariesURL + dlpath+":"+Http.getRemoteSize(Variable.librariesURL + dlpath)+":"+f.length());
				if( !f.exists() || (Config.instance.getProperty("forceupdate").compareTo("true")==0 ?(Http.getRemoteSize(Variable.librariesURL + dlpath) != f.length()):false) ){
					DownloadJob.getList().add(new DownloadFile(Variable.librariesURL + dlpath, "./starchasers/minecraft/bin/natives/" + dlpath2));				
					DownloadJob.nativesFile.add(name);
				}
					
			}
		}
	}
	 public static void downloadForge(){
		 String[] parts = MinecraftJson.instance.getLibsForge().getForge().split(":");
		 String dlpath = parts[0].replace('.', '/') + "/" + parts[1] + "/" + parts[2] + "/";
		 String localPath = dlpath;
		 File f2 = new File("./starchasers/minecraft/libraries/" + dlpath);
			if (!f2.exists()) f2.mkdirs();
			
			//dlpath += parts[1] + "-" + parts[2] + ".jar";
			String temp = "http://files.minecraftforge.net/minecraftforge/" + parts[1] + "-"+"universal-1.6.2-" + parts[2] + ".jar";
			//System.out.println("./starchasers/minecraft/libraries/" + dlpath+ parts[1] + "-"+"universal-1.6.2-" + parts[2] + ".jar");
			File f= new File("./starchasers/minecraft/libraries/" + dlpath+ parts[1] + "-"+"universal-1.6.2-" + parts[2] + ".jar");
			System.out.println("FORGE:"+temp);
			LaunchWrapper.Forgeversion = parts[2];
			if( !f.exists() || (Config.instance.getProperty("forceupdate").compareTo("true")==0 ?(Http.getRemoteSize(temp) != f.length()):false) )
				DownloadJob.getList().add(new DownloadFile(temp,"./starchasers/minecraft/libraries/"+localPath));
	 }
}
