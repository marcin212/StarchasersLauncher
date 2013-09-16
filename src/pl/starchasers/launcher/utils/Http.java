package pl.starchasers.launcher.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import pl.starchasers.launcher.skin.components.ActionLabel;
import pl.starchasers.launcher.skin.components.MyProgressBar;

public class Http {
	public static boolean exists(String url) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection con = (HttpURLConnection) new URL(url)
					.openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void download(String fileURL, String destinationDirectory,float part){
		try{
	 	String downloadedFileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
		ActionLabel.instance.addElement(downloadedFileName.replace("%20"," "));
	 	ActionLabel.instance.setProgress(0);
        URL url=new URL(fileURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int filesize = connection.getContentLength();
        double startProgress = MyProgressBar.instance.getProgress();
        float totalDataRead=0;
            BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
            FileOutputStream fos = new FileOutputStream(destinationDirectory+ "/" + downloadedFileName.replace("%20", " "));
            BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
            byte[] data = new byte[1024];
            int i=0;
            while((i=in.read(data,0,1024))>=0)
            {
            	totalDataRead=totalDataRead+i;
            	bout.write(data,0,i);
            	float Percent=totalDataRead/filesize;
            	ActionLabel.instance.setProgress(Percent);
            	MyProgressBar.instance.setProgress(startProgress+(Percent*part));
            	Thread.sleep(1);
            }  
            bout.close();
            in.close();
    	}catch(Exception e){
    	e.printStackTrace();
    }

	}
	public static String executeHttpRequest(String targetURL, String urlParameters, String requestType, String contentType) {
		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestType);
			connection.setRequestProperty("Content-Type", contentType);
			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
/////
	public static String executeHttpRequestE(String targetURL, String urlParameters, String requestType, String contentType) throws IOException {
		URL url;
		HttpURLConnection connection = null;
		
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestType);
			connection.setRequestProperty("Content-Type", contentType);
			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			if (connection != null) {
				connection.disconnect();
			}
			return response.toString();	
	}
	
	
	
	
	
	
	////
	public static long getRemoteSize(String fileURL) {
		 long fileSize=-1;
	    try {    
	    	URLConnection connection;
	        URL urle = new URL(fileURL);
	        
	        connection = urle.openConnection();
	        fileSize = connection.getContentLength();
	        connection.getInputStream().close();
	 
	      } catch(IOException e) {
	        e.printStackTrace();
	      } 
	    return fileSize;
	}

	
}
