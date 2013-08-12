package pl.starchasers.launcher.skin.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Variable;

public class ButtonClose extends MyButton{
	private static final long serialVersionUID = 1L;
	public static ButtonClose instance;
	public ButtonClose() {
		super("",Variable.resourcePath+"x");
		instance = this;
		setBounds(828, 11, 16, 16);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setIcon(getOverIcon());
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setIcon(getDownIcon());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setIcon(getUpIcon());
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setIcon(getOverIcon());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				setIcon(getUpIcon());
				Config.instance.store(Variable.workingDir + "starchasers.properties");
				System.exit(0);
			}
		});
	}
}