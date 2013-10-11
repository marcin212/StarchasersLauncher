package pl.starchasers.launcher.launch;

import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.profiles.Profile;
import pl.starchasers.launcher.skin.panels.Contents;
import pl.starchasers.launcher.sync.DownloadFile;
import pl.starchasers.launcher.sync.DownloadJob;
import pl.starchasers.launcher.sync.Sync;
import pl.starchasers.launcher.utils.Http;
import pl.starchasers.launcher.utils.Variable;
import pl.starchasers.launcher.utils.json.Libraries;
import pl.starchasers.launcher.utils.json.MinecraftJson;
import pl.starchasers.launcher.utils.json.Version;

public class CheckFiles {
	private Contents components = Main.getFrame().getPanel();
	public static List<Libraries> libraries;
	public static Version ver;
	public String minecraftVersion;
	CheckFiles(Profile profile) {
		minecraftVersion = profile.getMinecraftversion();
		components.getActionLabel().setAction("checking files...");
		downloadForge(profile);
		String name = profile.getForgeName();
		 if(name == null || name.length()==0){
			 ver  = MinecraftJson.instance.getObjForVersion(minecraftVersion);
			 libraries = ver.getLibraries();
		 }else{
			  ver = MinecraftJson.instance.getObjForVersion(minecraftVersion,name);
			  libraries = ver.getLibraries();
			  new Sync(profile);
		 }
		downloadBin(profile);
		downloadLibraries(libraries,profile);	
		components.getActionLabel().setAction("downloading files...");
		DownloadJob.downloadJob(profile);
		components.getActionLabel().setZero();
	}

	public static String forgePath(String name){
		 String[] parts = name.split(":");
		 String dlpath = parts[0].replace('.', '/') + "/" + parts[1] + "/" + parts[2] + "/";
		return "./starchasers/minecraft/libraries/" + dlpath + parts[1]+"-"+parts[2]+".jar";
	}
		
	private void downloadBin(Profile profile) {
		String bindir = "./starchasers/minecraft/instances/"+profile.getDir()+"/bin";
		File f = new File(bindir);
		if (!f.exists()) f.mkdirs();
		f = new File(bindir + "/" + minecraftVersion+".jar");
		if(!f.exists() || (Main.getConf().getProperty("forceupdate").compareTo("true")==0 ?(Http.getRemoteSize("http://s3.amazonaws.com/Minecraft.Download/versions/"+minecraftVersion+"/"+minecraftVersion+".jar") != f.length()):false))
			DownloadJob.getList().add(new DownloadFile(
					"http://s3.amazonaws.com/Minecraft.Download/versions/"+minecraftVersion+"/"+minecraftVersion+".jar",
					bindir,null));
	}

	private void downloadLibraries(List<Libraries> libraries, Profile profile) {
		
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
				
				if(actLib.getUrl()!= null){actLib.setUrl((actLib.getUrl().equals("http://files.minecraftforge.net/maven/") ? "http://repo1.maven.org/maven2/":actLib.getUrl()));}
				String temp = (actLib.getUrl()==null ? Variable.librariesURL: actLib.getUrl()) + dlpath;
				
				File f= new File("./starchasers/minecraft/libraries/"+dlpath);
				//System.out.println(dlpath+":"+temp+":"+Http.getRemoteSize(temp)+":"+f.length());
				
				if( !f.exists() || (Main.getConf().getProperty("forceupdate").compareTo("true")==0 ? (Http.getRemoteSize(temp) != f.length()):false ) ){
					DownloadJob.getList().add(new DownloadFile(temp,"./starchasers/minecraft/libraries/"+localPath,null));
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
				File f2 = new File("./starchasers/minecraft/instances/"+profile.getDir()+"/bin/natives/" + dlpath);
				if (!f2.exists()) f2.mkdirs();
				String dlpath2 = dlpath+".";
				dlpath += parts[1] + "-" + parts[2] + "-" + nativesString + ".jar";
				String name = "./starchasers/minecraft/instances/"+profile.getDir()+"/bin/natives/" + dlpath;
				File f= new File(name);
				//System.out.println(name+":"+Variable.librariesURL + dlpath+":"+Http.getRemoteSize(Variable.librariesURL + dlpath)+":"+f.length());
				if( !f.exists() || (Main.getConf().getProperty("forceupdate").compareTo("true")==0 ?(Http.getRemoteSize(Variable.librariesURL + dlpath) != f.length()):false) ){
					DownloadJob.getList().add(new DownloadFile(Variable.librariesURL + dlpath, "./starchasers/minecraft/instances/"+profile.getDir()+"/bin/natives/" + dlpath2,null));				
					DownloadJob.nativesFile.add(name);
				}
					
			}
		}
	}
	 public void downloadForge(Profile profile){
		 String name = profile.getForgeName();
		 if(name == null || name.length()==0){
			 return;
		 }
		 System.out.println("kupa:"+name);
		 String[] parts = name.split(":");
		 String urltodownload = "http://files.minecraftforge.net/"+parts[1]+"/"+parts[1]+"-universal-"+profile.getMinecraftversion()+"-"+parts[2]+".jar";
		 String dlpath = parts[0].replace('.', '/') + "/" + parts[1] + "/" + parts[2] + "/";
		 String localPath = dlpath.trim();
		 
		 
		 
		 File f2a = new File("./starchasers/minecraft/libraries/"+localPath);
			if (!f2a.exists()) f2a.mkdirs();
		System.out.println("special:"+"./starchasers/minecraft/libraries/" + dlpath);
			File f= new File("./starchasers/minecraft/libraries/"+localPath,parts[1]+"-"+parts[2]+".jar");
			if( !f.exists() || (Main.getConf().getProperty("forceupdate").compareTo("true")==0 ?(Http.getRemoteSize(urltodownload) != f.length()):false) )
				DownloadJob.getList().add(new DownloadFile(urltodownload,"./starchasers/minecraft/libraries/"+localPath,parts[1]+"-"+parts[2]+".jar"));
	 }
}
