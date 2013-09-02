package pl.starchasers.launcher.skin.frame.playerskin;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import pl.starchasers.launcher.utils.Config;

public class PlayerSkin extends JLabel {
	private static final long serialVersionUID = 1L;
	String urlskin = "http://s3.amazonaws.com/MinecraftSkins/";
	public static PlayerSkin instance;

	public PlayerSkin() {
		instance = this;
		setBounds(492, 245, 128, 128);
		setSkin(Config.instance.getProperty("nickname"));
	}

	public void setSkin(String name) {
		BufferedImage img;
		try {
			img = ImageIO.read(new URL(urlskin + name + ".png"));
			BufferedImage img2 = img.getSubimage(8, 8, 8, 8);
			AffineTransform tx = new AffineTransform();
			tx.scale(8, 8);
			AffineTransformOp op = new AffineTransformOp(tx,
					AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			img2 = op.filter(img2, null);
			setIcon(new ImageIcon(img2));
		} catch (IOException e) {
			//e.printStackTrace();
			return;
		}
	}
}
