package pl.starchasers.launcher.skin;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import pl.starchasers.launcher.utils.Variable;

public class MyFont {
	private Font customFont = null;

	public MyFont() {
		try {
			customFont = Font.createFont(
					Font.TRUETYPE_FONT,
					new File("src" + Variable.resourcePath
							+ "Caviar_Dreams_Bold.ttf")).deriveFont(12f);
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
