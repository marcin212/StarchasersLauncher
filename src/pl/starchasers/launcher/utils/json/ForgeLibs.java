package pl.starchasers.launcher.utils.json;

import java.util.ArrayList;
import java.util.List;

public class ForgeLibs {
	List<Libraries> libs = new ArrayList<Libraries>();
	String forge;
	public String getForge() {
		return forge;
	}

	public void setForge(String forge) {
		this.forge = forge;
	}

	public List<Libraries> getLibs() {
		return libs;
	}

	public void setLibs(List<Libraries> libs) {
		this.libs = libs;
	}
}
