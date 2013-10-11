package pl.starchasers.launcher.sync;

public class DownloadFile {
	private String fileName;
	private String dir;
	private String url;
	public DownloadFile(String url, String folder, String name) {
		this.url = url;
		this.dir = folder;
		this.fileName = name;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
