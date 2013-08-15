package pl.starchasers.launcher.skin.components;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.starchasers.launcher.skin.MyFont;

public class ActionLabel extends JPanel {
	private static final long serialVersionUID = 1L;
	List<JLabel> downladingFiles = new ArrayList<JLabel>();
	public static ActionLabel instance;
	private JLabel percent = new JLabel();
	private JLabel action = new JLabel();
	public ActionLabel() {
		instance = this;
		setLayout(null);
		setOpaque(false);
		setBounds(185,35,300,200);
		initDownloadingFilesList();
		for(int i = 0;i<downladingFiles.size();i++){
			add(downladingFiles.get(i));
			}
		add(percent);
		add(action);
	}

	public void initDownloadingFilesList() {
		Font font = new MyFont().returnFont().deriveFont(14f);
		for (int i = 0; i < 6; i++) {
			JLabel label = new JLabel("");
			label.setBounds(50, 16 * i +87, 300, 16);
			label.setOpaque(false);
			label.setFont(font);
			label.setLayout(null);
			label.setBorder(null);
			label.setForeground(new Color(68 / 255f, 107 / 255f, 135 / 255f,1 - (i / 5f)));
			downladingFiles.add(label);
		}
		percent.setOpaque(false);
		percent.setFont(font);
		percent.setForeground(new Color(68 / 255f, 107 / 255f, 135 / 255f,1f));
		percent.setLayout(null);
		percent.setBorder(null);
		percent.setBounds(0,85,50,20);
		percent.setText("");
		action.setOpaque(false);
		action.setFont(font);
		action.setForeground(new Color(68 / 255f, 107 / 255f, 135 / 255f,1f));
		action.setLayout(null);
		action.setBorder(null);
		action.setBounds(2,0,300,16);
		action.setText("");
	}

	public void addElement(String text) {
		String texttemp = text;
		for (int i = 0; i < downladingFiles.size(); i++) {
			String temp = downladingFiles.get(i).getText();
			downladingFiles.get(i).setText(texttemp);
			texttemp = temp;
		}
		repaint();
	}
	
	public void setProgress(float p){
		percent.setText((int)(p*100)+"%");
		repaint();
	}
	
	public void setAction(String text){
		action.setText(text);
		repaint();
	}
	public void setZero(){
		percent.setText("");
		for(int i=0; i<6; i++)
			addElement("");
		repaint();
	}
	
}
