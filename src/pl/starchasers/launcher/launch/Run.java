package pl.starchasers.launcher.launch;

public class Run {
	public static Thread launch;
	public static void start() {
		 launch = new Thread(new Runnable() {
			@Override
			public void run() {
				new Launch();
			}
		});
		launch.start();
	}
}
