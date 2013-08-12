package pl.starchasers.launcher.launch;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import pl.starchasers.launcher.utils.FileUtils;
import pl.starchasers.launcher.utils.Http;
import pl.starchasers.launcher.utils.json.Libraries;
import pl.starchasers.launcher.utils.json.MinecraftJson;

public class CheckFiles {
	CheckFiles() {
		File f = new File(".\\starchasers\\minecraft\\bin");
		if (!f.exists()) {
			f.mkdirs();
			downloadBin();
		}
		f = new File(".\\starchasers\\minecraft\\libraries");
		if (!f.exists()) {
			f.mkdirs();
			List<Libraries> libraries = MinecraftJson.instance
					.getObjForVersion("1.6.2").getLibraries();
			downloadLibraries(libraries);
		}
	}

	private void downloadBin() {
		try {
			Http.download(
					"http://s3.amazonaws.com/Minecraft.Download/versions/1.6.2/1.6.2.jar",
					".\\starchasers\\minecraft\\bin");
			File f = new File(".\\starchasers\\minecraft\\bin\\1.6.2.jar");
			f.renameTo(new File(".\\starchasers\\minecraft\\bin\\minecraft.jar"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void downloadLibraries(List<Libraries> libraries) {
		File f = new File(".\\starchasers\\minecraft\\libraries\\");
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(".\\starchasers\\minecraft\\bin\\natives\\");
		if (!f.exists()) {
			f.mkdirs();
		}
		for (Libraries actLib : libraries) {
			if (actLib.getNatives() == null) {
				if (actLib.getName().contains("debug")) {
					System.out.println("lol");
					continue;
				}
				String[] parts = actLib.getName().split(":");
				String dlpath = parts[0].replace('.', '/') + "/" + parts[1]
						+ "/" + parts[2] + "/";
				String localPath = dlpath;
				File f2 = new File(".\\starchasers\\minecraft\\libraries\\"
						+ dlpath);
				if (!f2.exists()) {
					f2.mkdirs();
				}
				dlpath += parts[1] + "-" + parts[2] + ".jar";
				try {
					String temp = "https://s3.amazonaws.com/Minecraft.Download/libraries/"
							+ dlpath;
					System.out.println(dlpath);
					Http.download(temp,".\\starchasers\\minecraft\\libraries\\"+localPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				if (actLib.getName().contains("debug")) {
					continue;
				}
				String osname = System.getProperty("os.name"), nativesString = null;
				if (osname.toLowerCase().contains("windows")) {
					nativesString = "natives_windows";
				} else if (osname.toLowerCase().contains("linux")) {
					nativesString = "natives_linux";
				} else if (osname.toLowerCase().contains("os")) {
					nativesString = "natives_osx";
				} else {
					Object[] offlineButton = { "Windows", "Linux", "OS X" };
					int n = JOptionPane
							.showOptionDialog(
									null,
									"Couldn't detect your operating system, select from below:",
									"Couldn't detect OS!",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.WARNING_MESSAGE, null,
									offlineButton, offlineButton[0]);
					switch (n) {
					case 0:
						nativesString = "natives_windows";
						break;
					case 1:
						nativesString = "natives_linux";
						break;
					case 2:
						nativesString = "natives_osx";
						break;
					}
				}
				String[] parts = actLib.getName().split(":");
				String dlpath = parts[0].replace('.', '/') + "/" + parts[1]
						+ "/" + parts[2] + "/";
				File f2 = new File(".\\starchasers\\minecraft\\bin\\natives\\"
						+ dlpath);
				if (!f2.exists()) {
					f2.mkdirs();
				}
				dlpath += parts[1] + "-" + parts[2] + "-" + nativesString
						+ ".jar";
				try {

					Http.download(
							"https://s3.amazonaws.com/Minecraft.Download/libraries/"
									+ dlpath,
							".\\starchasers\\minecraft\\bin\\natives\\"
									+ dlpath);
					String name = ".\\starchasers\\minecraft\\bin\\natives\\"
							+ dlpath;
					FileUtils.unzipNatives(name,
							".\\starchasers\\minecraft\\bin\\natives\\");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
