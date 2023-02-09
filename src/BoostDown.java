import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BoostDown extends GameObject {

	private BufferedImage boostD;

	public BoostDown(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);

		boostD = ss.grabImage(6, 1, 32, 32);
	}

	public void update() {

	}

	public void render(Graphics g) {
		g.drawImage(boostD, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x + 5, y + 1, 21, 30);
	}

}
