package pl.starchasers.launcher.skin.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.starchasers.launcher.skin.MyFont;
import pl.starchasers.launcher.utils.Variable;

public class CopyrightLabel extends JPanel{
	private static final long serialVersionUID = 1L;
	public static CopyrightLabel instance;
	private List<String> nameLabel = new ArrayList<String>();
public CopyrightLabel() {
	instance = this;
	setBounds(0, 0, 854, 480);
	setLayout(null);
	setOpaque(false);
	
	nameLabel.add("GFX: Barlots");
	nameLabel.add("Code: marcin212");
	nameLabel.add("starchasers.pl © 2013 ");
	createLabels();
	

}
private void createLabels(){
	for(int i=0; i<nameLabel.size();i++){
	JLabel label = new JLabel(nameLabel.get(i));	
	label.setForeground(new Color(84, 91, 100));
	label.setOpaque(false);
	label.setBounds(20, (480-((nameLabel.size()-1)*(14)))-(14*i), 400, 14);
	label.setFont(new MyFont().returnFont());
	add(label);
	}
	JLabel label = new JLabel(Variable.launcherVersion);
	label.setFont(new MyFont().returnFont());
	label.setForeground(new Color(84, 91, 100));
	label.setBounds(854-90, 480-29, 90, 16);
	add(label);
}


}
