package pl.starchasers.launcher.skin.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import pl.starchasers.launcher.utils.Variable;

public class MyProgressBar extends JPanel {
	public static MyProgressBar instance;
	private static final long serialVersionUID = 1L;
	private double percent = 1;
	private Image progressUnloadImg = new ImageIcon(
			MyProgressBar.class.getResource(Variable.resourcePath+"progressbar_darkside.png")).getImage();
	private Image flareImg =  new ImageIcon(
			MyProgressBar.class.getResource(Variable.resourcePath+"progressbar_flare.png")).getImage();
	public MyProgressBar(){
		super();
		instance = this;
		setOpaque(false);
		setBounds(0, 0, 854, 480);
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		drawTrapezoid(g2);
		if(percent != 1)
		drawFlare(g2);
		g2.finalize();
	}

	public Shape createTrapezoid() {
		final GeneralPath p0 = new GeneralPath();
		p0.moveTo(734F, 115F);
		p0.lineTo(734F, 52F);
		p0.lineTo(734 - (536 * (1 - percent)) - 63F, 52F);
		p0.lineTo(734 - (536 * (1 - percent)), 115F);
		p0.closePath();
		return p0;
	}

	public void drawTrapezoid(Graphics2D g2) {
		g2.setClip(createTrapezoid());
		g2.drawImage(progressUnloadImg, 187, 52, this);
	}

	public void drawFlare(Graphics2D g2) {
		// start point x:116 y:37
		// finish point x:599 y:-17
		if ((percent <= 1) && (percent >= 0)) {
			int x, y;
			g2.setClip(0, 0, 854, 480);
			x = (int) (116 + (483 * percent));
			y = (int) (-0.1118 * (116 + (483 * percent)) + 49.969);
			g2.drawImage(flareImg, x, y, 144, 144, this);
		}
	}

	public void setProgress(double progress) {
		if (progress > 1 || progress < 0)
			throw new IllegalArgumentException(
					"Progress must be greater or equal than 0 and less or equal than 1.");
		this.percent = progress;
		this.repaint();
		return;
	}

	public double getProgress() {
		return this.percent;
	}
}
