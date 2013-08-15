package pl.starchasers.launcher.skin.frame;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import pl.starchasers.launcher.skin.components.MyFrame;

public class MainFrame {
	public static MainFrame instance;
	private int x,y;
	private MyFrame frame;
	public MainFrame() {
		instance = this;
		frame = new MyFrame();
		frame.setVisible(true);
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			      x=arg0.getX();
			      y=arg0.getY();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		frame.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				frame.setLocation(arg0.getXOnScreen()-x, arg0.getYOnScreen()-y);
			}
		});
	}
}
