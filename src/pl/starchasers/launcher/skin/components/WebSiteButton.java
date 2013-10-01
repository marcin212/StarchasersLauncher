package pl.starchasers.launcher.skin.components;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import pl.starchasers.launcher.utils.Variable;

public class WebSiteButton extends SuperButton {
	private static final long serialVersionUID = 1L;
	private URI uri = null;
	private Desktop dt = Desktop.getDesktop();

	public WebSiteButton() {
		super(644, 387, 26, 26,"", Variable.resourcePath + "button_website",null);
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
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