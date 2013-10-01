package pl.starchasers.launcher.skin.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import pl.starchasers.launcher.skin.frame.OptionsFrame;
import pl.starchasers.launcher.utils.Variable;

public class OptionsButton extends SuperButton{
	private static final long serialVersionUID = 1L;
	public OptionsButton() {
		super(613, 387, 26, 26,"", Variable.resourcePath+"button_options",null);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(OptionsFrame.instance == null){
				new OptionsFrame();
				}
			}
		});
	}
}
