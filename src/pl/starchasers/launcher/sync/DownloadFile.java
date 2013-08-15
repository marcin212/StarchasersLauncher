package pl.starchasers.launcher.sync;

public class DownloadFile {
	private String fileName;
	private String dir;
	public DownloadFile(String url, String folder) {
		this.fileName = url;
		this.dir = folder;
	}
	public String getDir(){
		return this.dir;
	}
	public void setDir(String dir){
		this.dir=dir;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
