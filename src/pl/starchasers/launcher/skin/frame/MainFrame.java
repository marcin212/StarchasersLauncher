package pl.starchasers.launcher.skin.frame;
import pl.starchasers.launcher.skin.components.MyFrame;

public class MainFrame {
	public static MainFrame instance;
	private MyFrame frame;
	public MainFrame() {
		instance = this;
		frame = new MyFrame();
		frame.setVisible(true);
	}
}
