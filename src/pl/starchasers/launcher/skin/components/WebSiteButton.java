package pl.starchasers.launcher.skin.components;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import pl.starchasers.launcher.utils.Variable;

public class WebSiteButton extends MyButton {
	private static final long serialVersionUID = 1L;
	public static WebSiteButton instance;
	private URI uri = null;
	private Desktop dt = Desktop.getDesktop();

	public WebSiteButton() {
		super("", Variable.resourcePath + "button_website");
		instance = this;
		this.setBounds(644, 387, 26, 26);
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				setIcon(getOverIcon());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				setIcon(getDownIcon());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setIcon(getUpIcon());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setIcon(getOverIcon());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setIcon(getUpIcon());
				try {
					uri = new URI(Variable.webSiteUrl);
					dt.browse(uri);
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}