package pl.starchasers.launcher.skin.frame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


import pl.starchasers.launcher.skin.panels.BgLayer;
import pl.starchasers.launcher.skin.panels.Contents;
import pl.starchasers.launcher.utils.Variable;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private int x,y;
	private Contents panel;
	public MainFrame() {
		super();
		setSize(new Dimension(854,480));
		setLocationRelativeTo(this);
		setTitle("Starchasers");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setUndecorated(true);
		panel = new Contents();
		getContentPane().add(panel);
		getContentPane().add(panel.iconLayer);
		
		
		Image im =new ImageIcon(MainFrame.class.getResource(Variable.resourcePath+"icon_512.png")).getImage();
		setIconImage(im);
		setFocusable(true);
		actions();

	}
	public void init(){

	}
	private void actions(){
		setVisible(true);
		addMouseListener(new MouseListener() {
			
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
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				setLocation(arg0.getXOnScreen()-x, arg0.getYOnScreen()-y);
			}
		});
	}
	public Contents getPanel() {
		return panel;
	}
	public void setPanel(Contents panel) {
		this.panel = panel;
	}
	
}
