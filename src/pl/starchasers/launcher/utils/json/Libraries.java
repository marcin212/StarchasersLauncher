package pl.starchasers.launcher.utils.json;

import java.util.List;
import java.util.Map;

public class Libraries {
	private String name;
	private String url;
	private List<Rules> rules;
	private Map<String, String> natives;
	private ExtractRules extract;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Libraries(String name){
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rules> getLibraries() {
		return this.rules;
	}

	public void setLibraries(List<Rules> rules) {
		this.rules = rules;
	}

	public Map<String, String> getNatives() {
		return this.natives;
	}

	public ExtractRules getExtractRules() {
		return this.extract;
	}
}
