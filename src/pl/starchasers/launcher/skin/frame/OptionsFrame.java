package pl.starchasers.launcher.skin.frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Variable;

public class OptionsFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	public JPanel panel = new JPanel();
	public List<String> list = new ArrayList<String>();
	public HashMap<String, JTextField> mapField = new HashMap<String, JTextField>();
	public JButton ok  = new JButton("Apply");
	public JButton cancel = new JButton("Cancel");
	
	public OptionsFrame() {
		createList();
		action();
		createPanel();
		add(panel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setSize(300,225);
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
		for(int i=0;i<list.size();i++){
			JLabel label= new JLabel(list.get(i)+":");
			JTextField textfield= new JTextField(Config.instance.getProperty(list.get(i)));
			label.setBounds(0, i*25, 100, 25);
			textfield.setBounds(100, i*25, 200, 25);
			panel.setLayout(null);
			panel.add(label);
			panel.add(textfield);
			mapField.put(list.get(i), textfield);
		}
		ok.setBounds(50, list.size()*25, 100, 25);
		cancel.setBounds(160, list.size()*25, 100, 25);
		panel.add(ok);
		panel.add(cancel);
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
				dispose();
				
			}
		});
	}
}

