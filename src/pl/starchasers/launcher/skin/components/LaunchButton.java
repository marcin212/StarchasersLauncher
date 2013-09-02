package pl.starchasers.launcher.skin.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pl.starchasers.launcher.auth.Login;
import pl.starchasers.launcher.launch.Launch;
import pl.starchasers.launcher.launch.Run;
import pl.starchasers.launcher.utils.Variable;

public class LaunchButton extends MyButton{
	private static final long serialVersionUID = 1L;
	public static LaunchButton instance;
	public LaunchButton() {
		super("", Variable.resourcePath+"button_launch");
		instance = this;
		setBounds(493, 386, 114, 27);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				setIcon(getOverIcon());
				LabelLaunch.instance.mouseReleased();
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				setIcon(getDownIcon());
				LabelLaunch.instance.mousePressed();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				setIcon(getUpIcon());
				LabelLaunch.instance.mouseExited();
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setIcon(getOverIcon());
				LabelLaunch.instance.mouseEntered();
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setIcon(getUpIcon());
				LabelLaunch.instance.mouseClicked();
				if(Login.getStatus()){
					Launch.runButton();
				}else if(Login.getCanRun()){
					Run.start();
				}

			}
		});
	}

	

}
