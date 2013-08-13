package pl.starchasers.launcher.sync.mods;

public class FileInfo {
	private String fileName;
	private String dir;
	private String md5;
	public FileInfo(String fileName,String dir, String md5) {
		this.fileName = fileName;
		this.setDir(dir);
		this.setMd5(md5);
	}
	public String getFileName(){
		return this.fileName;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
