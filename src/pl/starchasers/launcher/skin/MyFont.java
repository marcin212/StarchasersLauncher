package pl.starchasers.launcher.skin;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URL;


import pl.starchasers.launcher.skin.components.SuperButton;
import pl.starchasers.launcher.utils.Variable;

public class MyFont {
	private Font customFont = null;

	public MyFont() {
		try {
			URL url = SuperButton.class.getResource(Variable.resourcePath+ "Caviar_Dreams_Bold.ttf");	
			customFont = Font.createFont(Font.TRUETYPE_FONT,url.openStream()).deriveFont(12f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Font returnFont() {
		return this.customFont;
	}
}
