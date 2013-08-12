
package pl.starchasers.launcher.launch;

import java.util.List;

public class Response{
   	private String accessToken;
   	private List<Object> availableProfiles;
   	private String clientToken;
   	private SelectedProfile selectedProfile;

 	public String getAccessToken(){
		return this.accessToken;
	}
	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}
 	public List<Object> getAvailableProfiles(){
		return this.availableProfiles;
	}
	public void setAvailableProfiles(List<Object> availableProfiles){
		this.availableProfiles = availableProfiles;
	}
 	public String getClientToken(){
		return this.clientToken;
	}
	public void setClientToken(String clientToken){
		this.clientToken = clientToken;
	}
 	public SelectedProfile getSelectedProfile(){
		return this.selectedProfile;
	}
	public void setSelectedProfile(SelectedProfile selectedProfile){
		this.selectedProfile = selectedProfile;
	}
}
