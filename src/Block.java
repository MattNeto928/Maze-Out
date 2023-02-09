import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject {

	private BufferedImage block_imageB;
	private BufferedImage block_imageS;

	private int size;
	private int smallBounds;

	public Block(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);

		block_imageB = ss.grabImage(1, 2, 32, 32); // Creates 2 different sized blocks 
		block_imageS = ss.grabImage(1, 2, 24, 24); // and assigns graphics respectively
	}

	public void update() {

	}

	// Renders blocks
	public void render(Graphics g) {
		if (Game.LEVEL == 3) { // Small blocks are used on level 3
			g.drawImage(block_imageS, x + 4, y + 4, null);
		} else
			g.drawImage(block_imageB, x, y, null);
	}

	// Returns Rectangle bounds of This
	public Rectangle getBounds() {
		if (Game.LEVEL == 3) {
			size = 24;
			smallBounds = 4;
		} else {
			size = 32;
			smallBounds = 0;
		}

		return new Rectangle(x + smallBounds, y + smallBounds, size, size);

	}

}
