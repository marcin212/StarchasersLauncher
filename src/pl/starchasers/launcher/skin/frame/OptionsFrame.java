package pl.starchasers.launcher.skin.frame;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.MyFont;
import pl.starchasers.launcher.skin.SuperButton;
import pl.starchasers.launcher.utils.Variable;

public class OptionsFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	public static JFrame instance;
	public JLayeredPane panel = new JLayeredPane();
	public List<String> list = new ArrayList<String>();
	public HashMap<String, JTextField> mapField = new HashMap<String, JTextField>();
	public SuperButton ok;
	public SuperButton cancel;
	public SuperButton force;
	public int index=2;
	
	private static JTextField xms = new JTextField();
	private static JTextField xmx = new JTextField();
	
	
	private static JTextField jvm;
	private static JTextField permgen;
	
	private static JTextField sync;
	
	private int x,y;
	public OptionsFrame() {
		instance = this;


		setSize(319,439);
		setUndecorated(true);
		setBackground(new Color(0f,0f,0f,0f));
		icon();
		memoryTextField();
		
		createPanel();
		actionButton();
		

		
		jvm = standardTextfield("JVM:");
		jvm.setText(Main.getConf().getProperty("AddJVMArgs"));
		permgen = standardTextfield("PermGenSize:");
		permgen.setText(Main.getConf().getProperty("PermGen"));
		sync = standardTextfield("Sync-Server:");
		sync.setText(Main.getConf().getProperty("sync-server"));	
		buttonforceupdate();
		labeloptions();
		topAndBot();	
		background();	
		

		add(panel);
	
		
		setLocationRelativeTo(Main.getFrame());
		
		
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
		
		ok = new SuperButton(40, getHeight()-50, 114, 27, "Apply", Variable.resourcePath+"button_launch",panel);
		cancel = new SuperButton(170, getHeight()-50, 114, 27, "Cancel", Variable.resourcePath+"button_launch",panel);
		ok.addMouseListener(new MouseListener() {
			
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
		
				Main.getConf().setProperty("Xms", xms.getText().trim());
				Main.getConf().setProperty("Xmx", xmx.getText().trim());
				Main.getConf().setProperty("PermGen", permgen.getText().trim());
				Main.getConf().setProperty("AddJVMArgs", jvm.getText().trim());
				Main.getConf().setProperty("sync-server", sync.getText().trim());
				Main.getConf().store(Variable.workingDir + "starchasers.properties");
				
				instance =null;
				dispose();
			}
		});
		cancel.addMouseListener(new MouseListener() {
			
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
				instance =null;
				dispose();
			}
		});
		panel.add(ok);
		panel.add(cancel);
	}
public void topAndBot(){
	JLabel panelTop = new JLabel();
	JLabel panelBot = new JLabel();
	panelTop.setIcon(new ImageIcon(OptionsFrame.class.getResource(Variable.resourcePath+"border_options_top.png")));
	panelTop.setBounds(0,0,319,15);
	panelTop.setOpaque(false);
	panelBot.setIcon(new ImageIcon(OptionsFrame.class.getResource(Variable.resourcePath+"border_options_bot.png")));
	panelBot.setBounds(0,getHeight()-15,319,15);
	panelBot.setOpaque(false);
	panel.add(panelTop);
	panel.add(panelBot);
	
}
public void background()
{
	JLabel panelbackground = new JLabel();
	panelbackground.setIcon(new ImageIcon(OptionsFrame.class.getResource(Variable.resourcePath+"options background.png")));
	panelbackground.setBounds(5,4,getWidth()-5,getHeight()-13);
	panel.add(panelbackground);
	}

public void icon(){
	JLabel icon = new JLabel();
	icon.setIcon(new ImageIcon(OptionsFrame.class.getResource(Variable.resourcePath+"options_icon_1.png")));
	icon.setBounds(319-50,10,114,25);
	icon.setOpaque(false);

	panel.add(icon);
}
public void memoryTextField(){
	JLabel bgxms = new JLabel();
	JLabel infoxms = new JLabel("Xms:");
	infoxms.setBounds(40, 32*index+3, 170, 32);
	
	bgxms.setIcon(new ImageIcon(OptionsFrame.class.getResource(Variable.resourcePath+"options_doubletextfield_top.png")));
	xms.setBounds(130, 32*index+3, 170, 32);
	bgxms.setBounds(120, 32*index++, 190, 32);
	xms.setForeground(new Color(84, 91, 100));
	xms.setFont(new MyFont().returnFont());
	xms.setOpaque(false);
	xms.setBorder(null);
	xms.setText(Main.getConf().getProperty("Xms"));
	infoxms.setForeground(new Color(84, 91, 100));
	panel.add(xms);
	panel.add(bgxms);
	panel.add(infoxms);
	
	JLabel bgxmx = new JLabel();
	JLabel infoxmx = new JLabel("Xmx:");
	infoxmx.setBounds(40, 32*index-3, 170, 32);
	infoxmx.setForeground(new Color(84, 91, 100));
	bgxmx.setIcon(new ImageIcon(OptionsFrame.class.getResource(Variable.resourcePath+"options_doubletextfield_bot.png")));
	xmx.setBounds(130, 32*index-3, 170, 32);
	bgxmx.setBounds(120, 32*index++, 190, 32);
	xmx.setForeground(new Color(84, 91, 100));
	xmx.setFont(new MyFont().returnFont());
	xmx.setOpaque(false);
	xmx.setBorder(null);
	xmx.setText(Main.getConf().getProperty("Xmx"));
	panel.add(xmx);
	panel.add(bgxmx);
	panel.add(infoxmx);
}
public JTextField standardTextfield(String info){
	JTextField standardTextfield =new JTextField();
	JLabel bgstd = new JLabel();
	JLabel infostd = new JLabel(info);
	infostd.setBounds(40, 32*index+3, 170, 32);
	
	bgstd.setIcon(new ImageIcon(OptionsFrame.class.getResource(Variable.resourcePath+"options_textfield.png")));
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
public void buttonforceupdate(){
	force = new SuperButton(130, 32*index+20, 114, 27, "Force Update", Variable.resourcePath+"button_launch",panel);
	force.addMouseListener(new MouseListener() {
		
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
			Main.getConf().setProperty("forceupdate","true");
		}
	});
	panel.add(force);
}
public void labeloptions(){
	JLabel optionslabel = new JLabel();
	optionslabel.setBounds(220,10, 100, 25);
	optionslabel.setText("Options");
	optionslabel.setForeground(new Color(84, 91, 100));
	optionslabel.setFont(new MyFont().returnFont());
	panel.add(optionslabel);
	
}
}



