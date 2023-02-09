import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FakeBlock extends GameObject {

	private BufferedImage block_imageB;

	public FakeBlock(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);

		block_imageB = ss.grabImage(1, 2, 32, 32);
	}

	public void update() {

	}

	public void render(Graphics g) {
		g.drawImage(block_imageB, x, y, null);
	}

	public Rectangle getBounds() {
		return null;
	}
}
