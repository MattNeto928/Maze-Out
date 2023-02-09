import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>(); // Linked List of all GameObjects

	public boolean shrunk = false;
	private boolean up = false, down = false, left = false, right = false;
	private boolean HKey = false;
	private boolean focused = false;
	public int mode = 1;
	public int settingsMode = 4;
	public int selected = 1;
	public int coins = 1;

	public static int c1, c2, c3, cP;
	private int time = 0, ticks = 0;
	Random r = new Random();

	public void update() {
		
		//Updates every GameObject in the object LinkedList 60 frames per second
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			tempObject.update();
		}
		cP = r.nextInt(10) + 1;
		ticks++;
		time = ticks / 60;
		
		// Makes a random color (color1, color2, color3 -> r, g, b)
		if (cP == 1) {
			c1 = r.nextInt(254) + 1;
			c2 = r.nextInt(254) + 1;
			c3 = r.nextInt(254) + 1;
		}
	}

	// Graphics
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			tempObject.render(g);
		}
		//////////////////////////////////
		// Drawing Space/Dialog/Tips
		Font fnt = new Font("arial", 1, 20);
		Font fnt2 = new Font("arial", 1, 25);
		Font fnt3 = new Font("arial", 1, 15);
		Font Big = new Font("Bauhaus 93", 1, 60);
		Font magneto = new Font("magneto", 1, 60);
		if (Game.LEVEL == 6) {
			g.setFont(fnt3);
			g.setColor(Color.WHITE);
			g.drawString(
					"Hint: Move the wood using your focus ability WHILE TOUCHING THE SIDE OF THE WOOD (hold the SPACE bar)",
					50, 20);
		}
		
		if(Game.LEVEL == 12) {
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Yes... it's possible                                      hehehehehe", 50, 20);
		}
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Press P to pause", 50, 475);
		g.drawString("Press R to reset the level", 50, 500);
		g.setFont(fnt2);
		g.setColor(new Color(c1, c2, c3));
		g.drawString("Level: " + Game.LEVEL, 40, 75);
		g.drawString("Time: " + time, 40, 100);
		g.drawString("Deaths: " + Player.deaths, 800, 75);
		g.drawString("Coins: " + coins, 40, 125);
		
		if(Game.LEVEL > 15) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 996, 573);
			g.setFont(Big);
			g.setColor(new Color(c1, c2, c3));
			g.drawString("CONGRATULATIONS!", 200, 100);
			g.setFont(fnt2);
			g.drawString("You have just completed Maze Out!", 300, 200);
			g.setColor(Color.YELLOW);
			g.drawRect(385, 375, 200, 100);
			g.setFont(magneto);
			g.drawString("Menu", 390, 450);
			
		}
		
		///////////////////////////////////
	}

	// Adds a new GameObject
	public void addObject(GameObject tempObject) {
		object.add(tempObject);
	}

	// Destroys a GameObject
	public void removeObject(GameObject tempObject) {
		object.remove(tempObject);
	}

	
	// Getters and Setters of variables below
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isShrunk() {
		return shrunk;
	}

	public void setShrunk(boolean shrunk) {
		this.shrunk = shrunk;
	}

	public boolean isHKey() {
		return HKey;
	}

	public void setHKey(boolean hKey) {
		HKey = hKey;
	}

	public boolean isFocused() {
		return focused;
	}

	public void setFocused(boolean focused) {
		this.focused = focused;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getSettingsMode() {
		return settingsMode;
	}

	public void setSettingsMode(int settingsMode) {
		this.settingsMode = settingsMode;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	
}
