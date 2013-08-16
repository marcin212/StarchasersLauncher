package pl.starchasers.launcher.sync;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pl.starchasers.launcher.skin.components.MyProgressBar;
import pl.starchasers.launcher.utils.FileUtils;
import pl.starchasers.launcher.utils.Http;

public class DownloadJob {
 private static List<DownloadFile> toDownload = new ArrayList<DownloadFile>();
 public static List<String> nativesFile = new ArrayList<String>();
 
 public static List<DownloadFile> getList(){
	 return toDownload;
 }
 public static void downloadJob(){
	 MyProgressBar.instance.setProgress(0);
	 for(int i=0; i<toDownload.size(); i++){
		 File f = new File(toDownload.get(i).getDir());
			if (!f.exists()) {
				f.mkdirs();
			}
		 Http.download(toDownload.get(i).getFileName(), toDownload.get(i).getDir(),(float)1/(float)toDownload.size());
	 }
	 MyProgressBar.instance.setProgress(1);
	 try {
		 for(int i=0;i<nativesFile.size();i++)
		FileUtils.extractFolder(nativesFile.get(i),"./starchasers/minecraft/bin/natives/");
	} catch (Exception e) {
		e.printStackTrace();
	}
	 toDownload.clear();
	 nativesFile.clear();
 }
}
