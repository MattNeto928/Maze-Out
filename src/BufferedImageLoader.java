import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	private BufferedImage image;

	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path)); // Sets "image" to file of the String parameter
		} catch (IOException e) { // Throws an error on an Exception
			e.printStackTrace();
		}
		return image; // Returns the image file
	}

}
