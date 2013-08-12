package pl.starchasers.launcher.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
	public static void download(String fileURL, String destinationDirectory)
			throws IOException {
		String downloadedFileName = fileURL
				.substring(fileURL.lastIndexOf("/") + 1);
		URL url = new URL(fileURL);
		InputStream is = url.openStream();
		FileOutputStream fos = new FileOutputStream(destinationDirectory + "/"
				+ downloadedFileName);
		byte[] buffer = new byte[4096];
		int bytesRead = 0;
		while ((bytesRead = is.read(buffer)) != -1) {
			fos.write(buffer, 0, bytesRead);
		}
		fos.close();
		is.close();
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
	public static long getRemoteSize(String fileURL) {
		InputStream is;
		try {
			URL url = new URL(fileURL);
			is = url.openStream();
			return is.available();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
