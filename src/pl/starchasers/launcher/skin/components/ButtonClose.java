package pl.starchasers.launcher.skin.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.SuperButton;
import pl.starchasers.launcher.utils.Variable;

public class ButtonClose extends SuperButton{
	private static final long serialVersionUID = 1L;
	public ButtonClose() {
		super(828, 11, 16, 16, "",Variable.resourcePath+"x",null); 
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
				setIcon(getUpIcon());
				Main.getConf().store(Variable.workingDir + "starchasers.properties");
				System.exit(0);
			}
		});
	}
}