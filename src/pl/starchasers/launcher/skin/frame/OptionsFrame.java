package pl.starchasers.launcher.skin.frame;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import pl.starchasers.launcher.skin.components.BackGroundLabel;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Variable;

public class OptionsFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	public static JFrame instance;
	public JPanel panel = new JPanel();
	public List<String> list = new ArrayList<String>();
	public HashMap<String, JTextField> mapField = new HashMap<String, JTextField>();
	public JButton ok  = new JButton("Apply");
	public JButton cancel = new JButton("Cancel");
	private int x,y;
	public OptionsFrame() {
		instance = this;
		setSize(319,439);
		setUndecorated(true);
		setBackground(new Color(0f,0f,0f,0f));
		createList();
		
		
		createPanel();
		action();
		
		topAndBot();
		background();	
		
		add(panel);
	
		
		setLocationRelativeTo(MainFrame.instance.getFrame());
		
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				setLocation(arg0.getXOnScreen()-x, arg0.getYOnScreen()-y);
			}
		});
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
		setVisible(true);
	}

	private void createList(){
			list.add("Xms");
			list.add("Xmx");
			list.add("PermGen");
			
			list.add("Height");
			list.add("Width");
			
			list.add("sync-server");
			list.add("non-premium");
			
			list.add("AddJVMArgs");
				
	}
	private void createPanel(){
		panel.setBounds(0, 0, getWidth(), getHeight());
		panel.setLayout(null);
		for(int i=0;i<list.size();i++){
			
			JLabel label= new JLabel(list.get(i)+":");
			JTextField textfield= new JTextField(Config.instance.getProperty(list.get(i)));
			label.setBounds(0, i*25, 100, 25);
			textfield.setBounds(100, i*25, 200, 25);
			
			panel.add(label);
			panel.add(textfield);
			mapField.put(list.get(i), textfield);
		}
		
		ok.setBounds(50, list.size()*25, 100, 25);
		cancel.setBounds(160, list.size()*25, 100, 25);
		panel.add(ok);
		panel.add(cancel);
		panel.setOpaque(false);
		
	}
	
	private void action(){
		ok.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				for(int i = 0;i<list.size();i++){
					String newValue = mapField.get(list.get(i)).getText();
					Config.instance.setProperty(list.get(i), newValue);
					
				}
				Config.instance.store(Variable.workingDir + "starchasers.properties");
				instance =null;
				dispose();
			}
		});
		cancel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				instance =null;
				dispose();
			}
		});
	}
public void topAndBot(){
	JLabel panelTop = new JLabel();
	JLabel panelBot = new JLabel();
	panelTop.setIcon(new ImageIcon(BackGroundLabel.class.getResource(Variable.resourcePath+"border_options_top.png")));
	panelTop.setBounds(0,0,319,15);
	panelTop.setOpaque(false);
	panelBot.setIcon(new ImageIcon(BackGroundLabel.class.getResource(Variable.resourcePath+"border_options_bot.png")));
	panelBot.setBounds(0,getHeight()-15,319,15);
	panelBot.setOpaque(false);
	panel.add(panelTop);
	panel.add(panelBot);
	
}
public void background()
{
	JLabel panelbackground = new JLabel();
	panelbackground.setIcon(new ImageIcon(BackGroundLabel.class.getResource(Variable.resourcePath+"options background.png")));
	panelbackground.setBounds(5,4,getWidth()-5,getHeight()-13);
	panel.add(panelbackground);
	}
}

