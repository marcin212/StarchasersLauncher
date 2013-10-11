package pl.starchasers.launcher.sync;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pl.starchasers.launcher.launch.CheckFiles;
import pl.starchasers.launcher.profiles.Profile;
import pl.starchasers.launcher.skin.components.MyProgressBar;
import pl.starchasers.launcher.utils.FileUtils;
import pl.starchasers.launcher.utils.Http;

public class DownloadJob {
 private static List<DownloadFile> toDownload = new ArrayList<DownloadFile>();
 public static List<String> nativesFile = new ArrayList<String>();
 
 public static List<DownloadFile> getList(){
	 return toDownload;
 }
 public static void downloadJob(Profile profile){
	 MyProgressBar.instance.setProgress(0);
	 for(int i=0; i<toDownload.size(); i++){
		 File f = new File(toDownload.get(i).getDir());
			if (!f.exists()) {
				f.mkdirs();
			}
		 Http.download(toDownload.get(i).getUrl(), toDownload.get(i).getDir(),toDownload.get(i).getFileName(),(float)1/(float)toDownload.size());
	 }
	 MyProgressBar.instance.setProgress(1);
	 try {
		 for(int i=0;i<nativesFile.size();i++)
		FileUtils.extractFolder(nativesFile.get(i),"./starchasers/minecraft/instances/"+profile.getDir()+"/bin/natives/");
	} catch (Exception e) {
		e.printStackTrace();
	}
	 if(profile.getForgeName()!=null && profile.getForgeName().length()!=0)
		 FileUtils.unzipOne(CheckFiles.forgePath(profile.getForgeName()), "version.json", "version.json");
	 toDownload.clear();
	 nativesFile.clear();
	 
 }

}
