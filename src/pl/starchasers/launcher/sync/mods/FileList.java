package pl.starchasers.launcher.sync.mods;

import java.util.List;

public class FileList {
	private List<FileInfo> ModList;
	private List<FileInfo> ConfigList; 
	
	public List<FileInfo> getModList(){
		return this.ModList;
	}
	public void setModList(List<FileInfo> modList){
		this.ModList = modList;
	}
	public List<FileInfo> getConfigList() {
		return ConfigList;
	}
	public void setConfigList(List<FileInfo> configList) {
		ConfigList = configList;
	}
}
