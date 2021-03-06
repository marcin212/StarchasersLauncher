package pl.starchasers.launcher.skin.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.MyFont;
import pl.starchasers.launcher.utils.Variable;

public class CheckBox extends JCheckBox {
	private static final long serialVersionUID = 1L;
	public static CheckBox instance;

	public CheckBox() {
		super("Vanilla?");
		instance = this;
		setBounds(510, 355, 97, 23);
		setOpaque(false);
		setForeground(new Color(84, 91, 100));
		setFont(new MyFont().returnFont());
		setBorder(null);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusable(false);
		
		setSelected(Main.getConf().getProperty("vanilla").equals("true"));
		setIcon(new ImageIcon(
				CheckBox.class.getResource(Variable.resourcePath
						+ "check_off_up.png")));
		setSelectedIcon(new ImageIcon(
				CheckBox.class.getResource(Variable.resourcePath
						+ "check_on_up.png")));
		setDisabledIcon(new ImageIcon(
				CheckBox.class.getResource(Variable.resourcePath
						+ "check_off_up.png")));
		setRolloverSelectedIcon(new ImageIcon(
				CheckBox.class.getResource(Variable.resourcePath
						+ "check_on_over.png")));
		setRolloverIcon(new ImageIcon(
				CheckBox.class.getResource(Variable.resourcePath
						+ "check_off_over.png")));
		addMouseListener(new MouseListener() {
			
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

			}
		});
		addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isSelected()) {
                	Main.getConf().setProperty("vanilla", "true");
                } else {
                	Main.getConf().setProperty("vanilla", "false");
                }
                Main.getConf().store(Variable.workingDir + "starchasers.properties");
            }
        });
	}

}
