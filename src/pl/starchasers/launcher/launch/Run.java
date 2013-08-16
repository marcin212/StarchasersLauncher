package pl.starchasers.launcher.launch;

public class Run {
	public static Thread launch = null;
	public static void start() {
		if(launch == null || !launch.isAlive()){
		 launch = new Thread(new Runnable() {
			@Override
			public void run() {
				new Launch();
			}
		});
		 launch.start();
		}
	}
}
