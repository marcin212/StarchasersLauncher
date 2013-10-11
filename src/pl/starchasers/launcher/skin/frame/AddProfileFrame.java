package pl.starchasers.launcher.skin.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.profiles.Profile;
import pl.starchasers.launcher.skin.MyFont;
import pl.starchasers.launcher.skin.SuperButton;
import pl.starchasers.launcher.skin.components.ProfileList;
import pl.starchasers.launcher.skin.panels.Contents;
import pl.starchasers.launcher.utils.Variable;

public class AddProfileFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public JLayeredPane panel = new JLayeredPane();
	public static AddProfileFrame instance = null;
	public List<String> list = new ArrayList<String>();
	public HashMap<String, JTextField> mapField = new HashMap<String, JTextField>();
	public String lastSelectedName;
	public HashMap<String, Profile> lastRemovedProfile = new HashMap<String, Profile>();
	public ProfileList profilelist = new ProfileList(Main.getProfiles());
	public SuperButton ok;
	public SuperButton edit;
	public SuperButton delete;
	public SuperButton add;
	public SuperButton save;
	public SuperButton urladd;
	public int index = 3;

	
	
	
	private JTextField minecraftversion;
	private JTextField profilname;
	private JTextField syncserver;
	private JTextField xms;
	private JTextField xmx;
	private JTextField permgen;
	private JTextField jvmargs;
	private JTextField forge;
	private int x, y;
	public void lista (){
		profilelist.setBounds((114*1+5)/2, 60, 114, 26);
		panel.add(profilelist);
		profilelist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Profile selectedProfile = Main.getProfiles().getProfileByName(profilelist.getSelectedItem().toString());
				if(selectedProfile != null){
						
						minecraftversion.setText(selectedProfile.getMinecraftversion());
					 	profilname.setText(profilelist.getSelectedItem().toString());
					 	syncserver.setText(selectedProfile.getSyncserver());
					  	xms.setText(selectedProfile.getXms());
					  	xmx.setText(selectedProfile.getXmx());
					  	permgen.setText(selectedProfile.getPermgen());
					  	jvmargs.setText(selectedProfile.getJvmargs());
					  	forge.setText(selectedProfile.getForgeName());
					  	lastSelectedName = profilelist.getSelectedItem().toString();
					 
				}
				
			}
		});
	}
	public void repaintlist(){
	  	panel.remove(panel.getIndexOf(profilelist));
	  	profilelist = new ProfileList(Main.getProfiles());
	  	lista();
	  	panel.moveToFront(profilelist);
	  	Contents mainPanel = Main.getFrame().getPanel();
	  	mainPanel.remove(mainPanel.getListProfile());
	  	mainPanel.setListProfile(new ProfileList(Main.getProfiles()));
	  	mainPanel.add(mainPanel.getListProfile());
	  	mainPanel.moveToFront(mainPanel.getListProfile());
	  	mainPanel.repaint();
	  	panel.repaint();
	}
	public AddProfileFrame() {
		instance = this;
		lista();

		setSize(319, 439);
		setUndecorated(true);
		setBackground(new Color(0f, 0f, 0f, 0f));
		icon();

		createPanel();
		panel.add(ok = new SuperButton(40, getHeight() - 50,114, 27,"Ok", Variable.resourcePath + "button_launch",panel));
		panel.add(add = new SuperButton(1*31+159,60,26, 26,"", Variable.resourcePath + "button_add",null));
		panel.add(delete = new SuperButton(2*31+159,60,26, 26,"", Variable.resourcePath + "button_delete",null));
		panel.add(urladd = new SuperButton(3*31+159,60,26, 26,"", Variable.resourcePath + "button_website",null));
		panel.add(save = new SuperButton((114*1+5)/2,355,114, 27,"Save", Variable.resourcePath + "button_launch",panel));
		
		urladd.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String inputValue = JOptionPane.showInputDialog("Please input a url to profile.json"); 
				if(inputValue !=null && inputValue !="")
					Main.getProfiles().getProfileFromURL(inputValue);
				repaintlist();
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
				
			}
		});
		
		
		
		delete.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				Profile selectedProfile = Main.getProfiles().getProfileByName(profilelist.getSelectedItem().toString());
				if(selectedProfile != null){
					Main.getProfiles().deleteProfile(profilelist.getSelectedItem().toString());	
				}
				repaintlist();
				selectedProfile = Main.getProfiles().getProfileByName(profilelist.getSelectedItem().toString());
				if(selectedProfile != null){
						
						minecraftversion.setText(selectedProfile.getMinecraftversion());
					 	profilname.setText(profilelist.getSelectedItem().toString());
					 	syncserver.setText(selectedProfile.getSyncserver());
					  	xms.setText(selectedProfile.getXms());
					  	xmx.setText(selectedProfile.getXmx());
					  	permgen.setText(selectedProfile.getPermgen());
					  	jvmargs.setText(selectedProfile.getJvmargs());
					  	forge.setText(selectedProfile.getForgeName());
					  	lastSelectedName = profilelist.getSelectedItem().toString();
					 
				}
				repaintlist();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		add.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				lastSelectedName = null;
				minecraftversion.setText("");
			 	profilname.setText("");
			 	syncserver.setText("");
			  	xms.setText("");
			  	xmx.setText("");
			  	permgen.setText("");
			  	jvmargs.setText("");
			  	forge.setText("");
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		save.addMouseListener(new MouseListener() {

			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				Main.getProfiles().deleteProfile(lastSelectedName);
				Profile lastSelectedProfile;
				lastSelectedProfile = new Profile();
				lastSelectedProfile.setMinecraftversion(minecraftversion.getText());
			 	lastSelectedProfile.setSyncserver(syncserver.getText());
			  	lastSelectedProfile.setXms(xms.getText());
			  	lastSelectedProfile.setXmx(xmx.getText());
			  	lastSelectedProfile.setPermgen(permgen.getText());
			  	lastSelectedProfile.setJvmargs(jvmargs.getText());
			  	lastSelectedProfile.setForgeName(forge.getText());
			  	String profilnametoset = profilname.getText();
			  	int i =1;
			  	while(true){
			  	if(Main.getProfiles().getProfileByName(profilnametoset)==null){
			  		lastSelectedProfile.setDir(profilnametoset);
			  		Main.getProfiles().addProfile(profilnametoset, lastSelectedProfile);
			  		Main.getProfiles().saveProfiles();
			  		System.out.println("Pomyslnie dodano");
			  		break;
			  		}else{
			  			profilnametoset = profilnametoset + i;
			  			i++;
			  		}
			  	}
			  	
				minecraftversion.setText("");
			 	profilname.setText("");
			 	syncserver.setText("");
			  	xms.setText("");
			  	xmx.setText("");
			  	permgen.setText("");
			  	jvmargs.setText("");
			  	forge.setText("");
			  	repaintlist();
			  	
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		ok.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				instance = null;
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});

	//	memoryTextField();
		profilname = standardTextfield("Profilename:");
		profilname.setText("");
		minecraftversion = standardTextfield("MC version");
		minecraftversion.setText("");
	    syncserver = standardTextfield("Sync-Server");
	    syncserver.setText("");
		permgen = standardTextfield("PermSize");
		permgen.setText("");
		jvmargs = standardTextfield("JVM:");
		jvmargs.setText("");
		xms = standardTextfield("XMS:");
		xms.setText("");
		xmx = standardTextfield("XMX:");
		xmx.setText("");
		forge = standardTextfield("Forge:");
		forge.setText("");
		
		labeloptions();
		topAndBot();
		background();

		add(panel);
		Profile selectedProfile = Main.getProfiles().getProfileByName(profilelist.getSelectedItem().toString());
		if(selectedProfile != null){
				
				minecraftversion.setText(selectedProfile.getMinecraftversion());
			 	profilname.setText(profilelist.getSelectedItem().toString());
			 	syncserver.setText(selectedProfile.getSyncserver());
			  	xms.setText(selectedProfile.getXms());
			  	xmx.setText(selectedProfile.getXmx());
			  	permgen.setText(selectedProfile.getPermgen());
			  	jvmargs.setText(selectedProfile.getJvmargs());
			  	forge.setText(selectedProfile.getForgeName());
			  	lastSelectedName = profilelist.getSelectedItem().toString();
			 
		}
		setLocationRelativeTo(Main.getFrame());

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent arg0) {

			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				setLocation(arg0.getXOnScreen() - x, arg0.getYOnScreen() - y);
			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				x = arg0.getX();
				y = arg0.getY();
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

	private void createPanel() {
		panel.setBounds(0, 0, getWidth(), getHeight());
		panel.setLayout(null);
		panel.setOpaque(false);
	}

	public void topAndBot() {
		JLabel panelTop = new JLabel();
		JLabel panelBot = new JLabel();
		panelTop.setIcon(new ImageIcon(AddProfileFrame.class
				.getResource(Variable.resourcePath + "border_options_top.png")));
		panelTop.setBounds(0, 0, 319, 15);
		panelTop.setOpaque(false);
		panelBot.setIcon(new ImageIcon(AddProfileFrame.class
				.getResource(Variable.resourcePath + "border_options_bot.png")));
		panelBot.setBounds(0, getHeight() - 15, 319, 15);
		panelBot.setOpaque(false);
		panel.add(panelTop);
		panel.add(panelBot);

	}

	public void background() {
		JLabel panelbackground = new JLabel();
		panelbackground
				.setIcon(new ImageIcon(AddProfileFrame.class
						.getResource(Variable.resourcePath
								+ "options background.png")));
		panelbackground.setBounds(5, 4, getWidth() - 5, getHeight() - 13);
		panel.add(panelbackground);
	}

	public void icon() {
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon(AddProfileFrame.class
				.getResource(Variable.resourcePath + "options_icon_1.png")));
		icon.setBounds(319 - 50, 10, 114, 25);
		icon.setOpaque(false);

		panel.add(icon);
	}

	private JTextField standardTextfield(String info) {
		JTextField standardTextfield = new JTextField();
		JLabel bgstd = new JLabel();
		JLabel infostd = new JLabel(info);
		infostd.setBounds(40, 32 * index + 3, 170, 32);

		bgstd.setIcon(new ImageIcon(AddProfileFrame.class
				.getResource(Variable.resourcePath + "options_textfield.png")));
		standardTextfield.setBounds(130, 32 * index, 170, 32);
		bgstd.setBounds(120, 32 * index++, 190, 32);
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

	public void labeloptions() {
		JLabel optionslabel = new JLabel();
		optionslabel.setBounds(180, 10, 100, 25);
		optionslabel.setText("Profiles editor");
		optionslabel.setForeground(new Color(84, 91, 100));
		optionslabel.setFont(new MyFont().returnFont());
		panel.add(optionslabel);
	}
	public void memoryTextField(){
		JLabel bgxms = new JLabel();
		JLabel infoxms = new JLabel("Xms:");
		infoxms.setBounds(40, 32*index+3, 170, 32);
		
		bgxms.setIcon(new ImageIcon(AddProfileFrame.class.getResource(Variable.resourcePath+"options_doubletextfield_top.png")));
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
		bgxmx.setIcon(new ImageIcon(AddProfileFrame.class.getResource(Variable.resourcePath+"options_doubletextfield_bot.png")));
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
}
