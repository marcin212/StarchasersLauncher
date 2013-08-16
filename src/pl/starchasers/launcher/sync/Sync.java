package pl.starchasers.launcher.sync;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import pl.starchasers.launcher.skin.components.ActionLabel;
import pl.starchasers.launcher.sync.mods.FileInfo;
import pl.starchasers.launcher.sync.mods.FileList;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.json.Http;

import com.google.gson.Gson;

public class Sync {
	public FileList fileList = new FileList();
	public List<FileInfo> fileMod = new ArrayList<FileInfo>();
	public List<FileInfo> fileConfig = new ArrayList<FileInfo>();
	public String json = Http.excuteGet("http://176.9.140.54/starchasers/a.json");
	public Gson gson = new Gson();
	public FileList fileListExternal = gson.fromJson(json, FileList.class);
	public List<String> toDelete = new ArrayList<String>();

	public Sync() {
		if(Config.instance.getProperty("sync-server")!=""){
			ActionLabel.instance.setAction("checking files...");
			searchFiles("./starchasers/minecraft/config", true, fileConfig);
			searchFiles("./starchasers/minecraft/mods", false, fileMod);
			fileList.setModList(fileMod);
			fileList.setConfigList(fileConfig);
			compare();
			ActionLabel.instance.setAction("deleting useless files...");
			deleteFiles();
		}	
	}
	public void deleteFiles(){
		for(int i=0; i<toDelete.size(); i++){
			File f = new File(toDelete.get(i));
			f.delete();
			
		}
	}
	public void compare() {
		boolean test;
		for (int i = 0; i < fileListExternal.getConfigList().size(); i++) {
				test=false;
			for (int j = 0; j < fileList.getConfigList().size(); j++) {	
				if (fileListExternal.getConfigList().get(i).getFileName().compareTo(fileList.getConfigList().get(j).getFileName())==0) {
					test=true;
					if (fileListExternal.getConfigList().get(i).getMd5().compareTo((fileList.getConfigList().get(j).getMd5()))==0) {
					
					} else {
						DownloadJob.getList().add(new DownloadFile(Config.instance.getProperty("sync-server")+fileListExternal.getConfigList().get(i).getDir().replace(".\\", "").replace("\\", "/")+"/"+fileListExternal.getConfigList().get(i).getFileName().replace("\\", "/").replace(" ", "%20"),fileListExternal.getConfigList().get(i).getDir()+"/"));
					}
				}
				
				
			}
			if(!test){
						DownloadJob.getList().add(new DownloadFile(Config.instance.getProperty("sync-server")+fileListExternal.getConfigList().get(i).getDir().replace(".\\", "").replace("\\", "/")+"/"+fileListExternal.getConfigList().get(i).getFileName().replace("\\", "/").replace(" ", "%20"),fileListExternal.getConfigList().get(i).getDir()+"/"));
			}
		}
		for (int j = 0; j < fileList.getConfigList().size(); j++) {
			
			test=false;
			for (int i = 0; i < fileListExternal.getConfigList().size(); i++) {
				if (fileList.getConfigList().get(j).getFileName().compareTo(fileListExternal.getConfigList().get(i).getFileName())==0){
					test=true;
				}
				
			}
			if(!test){
				toDelete.add(fileList.getConfigList().get(j).getDir()+"/"+fileList.getConfigList().get(j).getFileName());
			}
		}
		
		///////
		for (int i = 0; i < fileListExternal.getModList().size(); i++) {
				test=false;
			for (int j = 0; j < fileList.getModList().size(); j++) {	
				if (fileListExternal.getModList().get(i).getFileName().compareTo(fileList.getModList().get(j).getFileName())==0) {
					test=true;
					if (fileListExternal.getModList().get(i).getMd5().compareTo((fileList.getModList().get(j).getMd5()))==0) {
					
					} else {
						DownloadJob.getList().add(new DownloadFile(Config.instance.getProperty("sync-server")+fileListExternal.getModList().get(i).getDir().replace(".\\", "").replace("\\", "/")+"/"+fileListExternal.getModList().get(i).getFileName().replace("\\", "/").replace(" ", "%20"),fileListExternal.getModList().get(i).getDir()+"/"));
					}
				}
				
				
			}
			if(!test){
				DownloadJob.getList().add(new DownloadFile(Config.instance.getProperty("sync-server")+fileListExternal.getModList().get(i).getDir().replace(".\\", "").replace("\\", "/")+"/"+fileListExternal.getModList().get(i).getFileName().replace("\\", "/").replace(" ", "%20"),fileListExternal.getModList().get(i).getDir()+"/"));
			}
		}
		for (int j = 0; j < fileList.getModList().size(); j++) {
			
			test=false;
			for (int i = 0; i < fileListExternal.getModList().size(); i++) {
				if (fileList.getModList().get(j).getFileName().compareTo(fileListExternal.getModList().get(i).getFileName())==0){
					test=true;
				}
				
			}
			if(!test){
				toDelete.add(fileList.getModList().get(j).getDir()+"/"+fileList.getModList().get(j).getFileName());
			}
		}
				
	}
	
	public void searchFiles(String path, boolean recursive, List<FileInfo> list) {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File[] listFiles = folder.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			if (!listFiles[i].isDirectory()) {
				list.add(new FileInfo(listFiles[i].getName(),
						listFiles[i].getPath().replace("\\", "/").substring(0,
								listFiles[i].getPath().replace("\\", "/").lastIndexOf("/")),
						testChecksum(listFiles[i].getPath())));
			} else {
				if (recursive) {
					searchFiles(listFiles[i].getPath(), recursive,list);
				}
			}

		}
	}

	public String testChecksum(String file) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[1024];
			int read = 0;
			while ((read = fis.read(data)) != -1) {
				md5.update(data, 0, read);
			}
			;
			byte[] hashBytes = md5.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < hashBytes.length; i++) {
				sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

			String fileHash = sb.toString();
			fis.close();

			return fileHash;

		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		return "";

	}
}
