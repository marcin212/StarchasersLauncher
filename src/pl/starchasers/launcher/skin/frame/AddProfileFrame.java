package pl.starchasers.launcher.skin.frame;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.MyFont;
import pl.starchasers.launcher.skin.components.LabelLaunch;
import pl.starchasers.launcher.skin.components.MyButton;
import pl.starchasers.launcher.skin.components.ProfileList;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Variable;

public class AddProfileFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	public JPanel panel = new JPanel();
	public static AddProfileFrame instance = null;
	public List<String> list = new ArrayList<String>();
	public HashMap<String, JTextField> mapField = new HashMap<String, JTextField>();
	
	public ProfileList profilelist = new ProfileList(Main.profiles);
	public MyButton ok  = new MyButton("", Variable.resourcePath+"button_launch");
	public MyButton edit  = new MyButton("", Variable.resourcePath+"button_launch");
	public MyButton delete  = new MyButton("", Variable.resourcePath+"button_launch");
	public int index=2;
		
		
		private static JTextField jvm;
		
		private static JTextField sync;
		
		private int x,y;
		public AddProfileFrame() {
			instance = this;
			
			profilelist.setBounds(150, 60, 150, 32);
			panel.add(profilelist);
			setSize(319,439);
			setUndecorated(true);
			setBackground(new Color(0f,0f,0f,0f));
			icon();
		
			
			createPanel();
			actionButton();
			

			
		//	jvm = standardTextfield("JVM:");
		//	jvm.setText(Config.instance.getProperty("AddJVMArgs"));
		//	sync = standardTextfield("Sync-Server:");
		//	sync.setText(Config.instance.getProperty("sync-server"));	

			labeloptions();
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


		private void createPanel(){
			panel.setBounds(0, 0, getWidth(), getHeight());
			panel.setLayout(null);
			panel.setOpaque(false);
		}
		
		private void actionButton(){
			
			
			ok.setBounds(40, getHeight()-50, 114, 27);
			final LabelLaunch okLabel = new LabelLaunch("Ok", 40, getHeight()-50, 114, 27);
			ok.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					ok.setIcon(ok.getOverIcon());
					okLabel.mouseReleased();
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					ok.setIcon(ok.getDownIcon());
					okLabel.mousePressed();
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					ok.setIcon(ok.getUpIcon());
					okLabel.mouseExited();
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					ok.setIcon(ok.getOverIcon());
					okLabel.mouseEntered();
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					instance = null;
					dispose();
				}
			});
			
			panel.add(okLabel);
			panel.add(ok);
	
		}
	public void topAndBot(){
		JLabel panelTop = new JLabel();
		JLabel panelBot = new JLabel();
		panelTop.setIcon(new ImageIcon(AddProfileFrame.class.getResource(Variable.resourcePath+"border_options_top.png")));
		panelTop.setBounds(0,0,319,15);
		panelTop.setOpaque(false);
		panelBot.setIcon(new ImageIcon(AddProfileFrame.class.getResource(Variable.resourcePath+"border_options_bot.png")));
		panelBot.setBounds(0,getHeight()-15,319,15);
		panelBot.setOpaque(false);
		panel.add(panelTop);
		panel.add(panelBot);
		
	}
	public void background()
	{
		JLabel panelbackground = new JLabel();
		panelbackground.setIcon(new ImageIcon(AddProfileFrame.class.getResource(Variable.resourcePath+"options background.png")));
		panelbackground.setBounds(5,4,getWidth()-5,getHeight()-13);
		panel.add(panelbackground);
		}

	public void icon(){
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon(AddProfileFrame.class.getResource(Variable.resourcePath+"options_icon_1.png")));
		icon.setBounds(319-50,10,114,25);
		icon.setOpaque(false);

		panel.add(icon);
	}
	
	public JTextField standardTextfield(String info){
		JTextField standardTextfield =new JTextField();
		JLabel bgstd = new JLabel();
		JLabel infostd = new JLabel(info);
		infostd.setBounds(40, 32*index+3, 170, 32);
		
		bgstd.setIcon(new ImageIcon(AddProfileFrame.class.getResource(Variable.resourcePath+"options_textfield.png")));
		standardTextfield.setBounds(130, 32*index, 170, 32);
		bgstd.setBounds(120, 32*index++, 190, 32);
		standardTextfield.setForeground(new Color(84, 91, 100));
		standardTextfield.setFont(new MyFont().returnFont());
		standardTextfield.setOpaque(false);
		standardTextfield.setBorder(null);
		infostd.setForeground(new Color(84, 91, 100));
		panel.add(standardTextfield);
		panel.add(bgstd);
		panel.add(infostd);	
		return standardTextfield;
	}
	
	public void labeloptions(){
		JLabel optionslabel = new JLabel();
		optionslabel.setBounds(180,10, 100, 25);
		optionslabel.setText("Profiles editor");
		optionslabel.setForeground(new Color(84, 91, 100));
		optionslabel.setFont(new MyFont().returnFont());
		panel.add(optionslabel);
	}





}
