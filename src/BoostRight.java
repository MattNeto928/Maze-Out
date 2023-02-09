import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BoostRight extends GameObject {

	private BufferedImage boostR;

	public BoostRight(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);

		boostR = ss.grabImage(6, 2, 32, 32);
	}

	public void update() {

	}

	public void render(Graphics g) {
		g.drawImage(boostR, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x + 1, y + 5, 30, 21);
	}
}
