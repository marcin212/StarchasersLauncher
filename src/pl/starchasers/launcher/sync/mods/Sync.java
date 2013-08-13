package pl.starchasers.launcher.sync.mods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import pl.starchasers.launcher.utils.json.Http;

import com.google.gson.Gson;

public class Sync {
	public FileList fileList = new FileList();
	public List<FileInfo> fileMod = new ArrayList<FileInfo>();
	public String json = Http.excuteGet("http://176.9.140.54/maszynaw/a.json");
	public Gson gson = new Gson();
	public FileList fileListExternal = gson.fromJson(json, FileList.class);

	public List<String> toDownload = new ArrayList<String>();
	public List<String> toDelete = new ArrayList<String>();

	public Sync() {
		searchFiles(".\\starchasers\\minecraft\\config", true);
		fileList.setModList(fileMod);
		compare();
		downloadFiles();
		deleteFiles();
		System.out.println(toDownload.size());
		System.out.println(toDelete.size());
		System.out.println(gson.toJson(fileList));
		System.out.println(json);
		
	}
	
	public void downloadFiles(){
		for(int i=0; i<toDownload.size(); i++){
			System.out.println(toDownload.get(i).substring(0, toDownload.get(i).lastIndexOf("\\")));
			File f = new File(toDownload.get(i).substring(0, toDownload.get(i).lastIndexOf("\\")));
			if (!f.exists()) {
				f.mkdirs();
			}
			try {
				pl.starchasers.launcher.utils.Http.download("http://176.9.140.54/starchasers/"+toDownload.get(i).replace("\\", "/").replace(" ", "%20"), toDownload.get(i).substring(0, toDownload.get(i).lastIndexOf("\\")));
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		for (int i = 0; i < fileListExternal.getModList().size(); i++) {
				test=false;
			for (int j = 0; j < fileList.getModList().size(); j++) {	
				if (fileListExternal.getModList().get(i).getFileName().compareTo(fileList.getModList().get(j).getFileName())==0) {
					test=true;
					if (fileListExternal.getModList().get(i).getMd5().compareTo((fileList.getModList().get(j).getMd5()))==0) {
					
					} else {
						toDownload.add(fileListExternal.getModList().get(i).getDir()+"\\"+fileListExternal.getModList().get(i).getFileName());
					}
				}
				
				
			}
			if(!test){
				toDownload.add(fileListExternal.getModList().get(i).getDir()+"\\"+fileListExternal.getModList().get(i).getFileName());
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
				toDelete.add(fileList.getModList().get(j).getDir()+"\\"+fileList.getModList().get(j).getFileName());
			}
		}
			
		
	}

	public void searchFiles(String path, boolean recursive) {
		File folder = new File(path);
		File[] listFiles = folder.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			if (!listFiles[i].isDirectory()) {
				this.fileMod.add(new FileInfo(listFiles[i].getName(),
						listFiles[i].getPath().substring(0,
								listFiles[i].getPath().lastIndexOf("\\")),
						testChecksum(listFiles[i].getPath())));
			} else {
				if (recursive) {
					searchFiles(listFiles[i].getPath(), recursive);
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
