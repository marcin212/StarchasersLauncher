package pl.starchasers.launcher.utils.json;

public class JsonLogin {
	private Agent agent = new Agent();
	private String clientToken, password, username;

	public Agent getAgent() {
		return this.agent;
	}

	public String getClientToken() {
		return this.clientToken;
	}

	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
