package pl.starchasers.launcher.utils.json;

import java.util.List;

public class Versions {
	private List<MinecraftVersions> versions;
	private Latest latest;

	public class Latest {
		private String snapshot;
		private String release;
		
		public String getSnapshot(){
			return this.snapshot;
		}
		public String getRelease(){
			return this.release;
		}
	}

	public List<MinecraftVersions> getVersionsList() {
		return this.versions;
	}

	public Latest getLatest() {
		return this.latest;
	}

}
