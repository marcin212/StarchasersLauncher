package pl.starchasers.launcher.skin.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SuperButton extends JButton{
	private static final long serialVersionUID = 1L;
	private String path;
	private ImageIcon upIcon;
	private ImageIcon overIcon;
	private ImageIcon downIcon;
	private SuperLabelButton buttonLabel;

	public SuperButton(int x, int y, int width, int height, String name, String path, JPanel panel){
		super("");
		this.path=path;
		setOpaque(false);
		setBorder(null);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setBounds(x, y, width, height);
		upIcon = new ImageIcon(SuperButton.class.getResource(this.path+"_up.png"));
		overIcon = new ImageIcon(SuperButton.class.getResource(this.path+"_over.png"));
		downIcon = new ImageIcon(SuperButton.class.getResource(this.path+"_down.png"));
		setIcon(upIcon);
		if(panel !=null){
		buttonLabel = new SuperLabelButton(name, x, y, width, height);
		panel.add(buttonLabel);
		}
		addActionOnMouse(panel);

	}
	public ImageIcon getUpIcon(){
		return this.upIcon;
	}
	public ImageIcon getOverIcon(){
		return this.overIcon;
	}
	public ImageIcon getDownIcon(){
		return this.downIcon;
	}
	
	public SuperLabelButton getButtonLabel() {
		return buttonLabel;
	}
	public void setButtonLabel(SuperLabelButton buttonLabel) {
		this.buttonLabel = buttonLabel;
	}
	public void setTextLabel(String text){
		getButtonLabel().setText(text);
	}
	private void addActionOnMouse(final JPanel panel){
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				setIcon(getOverIcon());
				if(panel !=null)
				buttonLabel.mouseReleased();
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				setIcon(getDownIcon());
				if(panel !=null)
				buttonLabel.mousePressed();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				setIcon(getUpIcon());
				if(panel !=null)
				buttonLabel.mouseExited();
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setIcon(getOverIcon());
				if(panel !=null)
				buttonLabel.mouseEntered();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
	});
	}
}
