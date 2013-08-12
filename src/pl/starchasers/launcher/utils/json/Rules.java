package pl.starchasers.launcher.utils.json;

public class Rules {
	private String action;
	private OSRestriction os;

	public static enum Action {
		ALLOW, DISALLOW;
	}

	public class OSRestriction {
		private String name;
		private String version;
		
		public String getName(){
			return this.name;
		}
		
		public String getVersion(){
			return this.version;
		}
	}
	
	public String getAction(){
		return this.action;
	}
	
	public OSRestriction getOs(){
		return this.os;
	}

}
