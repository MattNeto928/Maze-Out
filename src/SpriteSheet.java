import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;

	public SpriteSheet(BufferedImage image) {
		this.image = image; // Sets this-> image to the passed in Game-> image
	}

	// Returns an image from the spliced spread sheet by passed in coordinates/size
	public BufferedImage grabImage(int col, int row, int width, int height) {
		return image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
	}

}
