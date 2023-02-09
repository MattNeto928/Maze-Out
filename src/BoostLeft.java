import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BoostLeft extends GameObject {

	private BufferedImage boostL;

	public BoostLeft(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);

		boostL = ss.grabImage(5, 2, 32, 32);
	}

	public void update() {

	}

	public void render(Graphics g) {
		g.drawImage(boostL, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x + 1, y + 5, 30, 21);
	}

}
