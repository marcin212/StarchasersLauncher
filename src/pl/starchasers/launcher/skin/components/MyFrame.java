package pl.starchasers.launcher.skin.components;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import pl.starchasers.launcher.utils.Variable;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static MyFrame instance;
	public MyFrame() {
		super();
		instance = this;
		setSize(new Dimension(854,480));
		setLocationRelativeTo(this);
		setTitle("Starchasers");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setUndecorated(true);
		getContentPane().add(new MyMainPanel());
		Image im =Toolkit.getDefaultToolkit().getImage("src"+Variable.resourcePath+"icon_512.png");
		setIconImage(im);
	}
}
