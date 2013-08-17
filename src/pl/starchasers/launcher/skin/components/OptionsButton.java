package pl.starchasers.launcher.skin.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pl.starchasers.launcher.skin.frame.OptionsFrame;
import pl.starchasers.launcher.utils.Variable;

public class OptionsButton extends MyButton{
	private static final long serialVersionUID = 1L;
	public static OptionsButton instance;
	public OptionsButton() {
		super("", Variable.resourcePath+"button_options");
		instance = this;
		setBounds(613, 387, 26, 26);
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
				new OptionsFrame();
			}
		});
	}
}
