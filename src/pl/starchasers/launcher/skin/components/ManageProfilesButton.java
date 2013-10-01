package pl.starchasers.launcher.skin.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import pl.starchasers.launcher.skin.frame.AddProfileFrame;
import pl.starchasers.launcher.utils.Variable;

public class ManageProfilesButton extends SuperButton {
	private static final long serialVersionUID = 1L;

	public ManageProfilesButton(JPanel panel) {
		super(700, 350, 114, 27,"", Variable.resourcePath + "button_launch",panel);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(AddProfileFrame.instance==null)
					new AddProfileFrame();

			}
		});
	}

}
