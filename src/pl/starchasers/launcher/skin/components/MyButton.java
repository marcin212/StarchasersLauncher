package pl.starchasers.launcher.skin.components;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton{
	private static final long serialVersionUID = 1L;
	private String path;
	private ImageIcon upIcon;
	private ImageIcon overIcon;
	private ImageIcon downIcon;

	public MyButton(String name, String path){
		super(name);
		this.path=path;
		setOpaque(false);
		setBorder(null);
		setContentAreaFilled(false);
		setBorderPainted(false);
		upIcon = new ImageIcon(MyButton.class.getResource(this.path+"_up.png"));
		overIcon = new ImageIcon(MyButton.class.getResource(this.path+"_over.png"));
		downIcon = new ImageIcon(MyButton.class.getResource(this.path+"_down.png"));
		setIcon(upIcon);
	}
	public ImageIcon getUpIcon(){
		return this.upIcon;
	}
	public ImageIcon getOverIcon(){
		return this.overIcon;
	}
	public ImageIcon getDownIcon(){
		return this.downIcon;
	}
}
