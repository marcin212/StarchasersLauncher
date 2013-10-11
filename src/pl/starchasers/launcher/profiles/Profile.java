package pl.starchasers.launcher.profiles;

public class Profile {
	private String dir;
	private String minecraftversion;
	private String syncserver;
	private String xms;
	private String xmx;
	private String permgen;
	private String jvmargs;
	private String forgeName;

	public String getForgeName() {
		return forgeName;
	}

	public void setForgeName(String forgeName) {
		this.forgeName = forgeName;
	}

	public String getMinecraftversion() {
		return minecraftversion;
	}

	public void setMinecraftversion(String minecraftversion) {
		this.minecraftversion = minecraftversion;
	}

	public String getSyncserver() {
		return syncserver;
	}

	public void setSyncserver(String syncserver) {
		this.syncserver = syncserver;
	}

	public String getXms() {
		return xms;
	}

	public void setXms(String xms) {
		this.xms = xms;
	}

	public String getXmx() {
		return xmx;
	}

	public void setXmx(String xmx) {
		this.xmx = xmx;
	}

	public String getPermgen() {
		return permgen;
	}

	public void setPermgen(String permgen) {
		this.permgen = permgen;
	}

	public String getJvmargs() {
		return jvmargs;
	}

	public void setJvmargs(String jvmargs) {
		this.jvmargs = jvmargs;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

}
