package pl.starchasers.launcher.launch;

import pl.starchasers.launcher.Main;

public class Run {
	public static Thread launch = null;
	public static void start() {
		if(launch == null || !launch.isAlive()){
		 launch = new Thread(new Runnable() {
			@Override
			public void run() {
				System.setOut(Main.console.getOut());
				System.setErr(Main.console.getOut());
				Launch.runMinecraft();
			}
		});
		
		 launch.start();
		}
	}
}
