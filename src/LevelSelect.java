import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class LevelSelect {
	
	public static int levelSelect = 1;
	
	public void tick() {
		
	}
	
	
	
	// THIS CLASS IN NOT IN THIS VERSION OF THE GAME
	// But, it was responsible for the functionality and graphics of the level select system
	
	
	
	
	public static void render(Graphics g) {
		Font level = new Font("magneto", 1, 45);
		
		g.setColor(Color.YELLOW);
		g.drawRect(200, 425, 200, 100);
		g.drawString("Menu", 215, 500);
		
		g.drawRect(560, 425, 200, 100);
		g.drawString("Play", 590, 500);
		
		
		g.setFont(level);
		
		if(levelSelect == 1) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 1) {
			g.setColor(Color.yellow);
		}
		g.drawRect(128, 125, 64, 64);
		g.drawString("1", 150, 174);
		
		
		if(levelSelect == 2) {
			g.setColor(Color.cyan);
			
		} else if(levelSelect != 2) {
			g.setColor(Color.yellow);
		}
		g.drawRect(288, 125, 64, 64);
		g.drawString("2", 304, 174);
		
		if(levelSelect == 3) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 3) {
			g.setColor(Color.yellow);
		}
		g.drawRect(448, 125, 64, 64);
		g.drawString("3", 464, 174);
		
		if(levelSelect == 4) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 4) {
			g.setColor(Color.yellow);
		}
		g.drawRect(608, 125, 64, 64);
		g.drawString("4", 622, 174);
		
		if(levelSelect == 5) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 5) {
			g.setColor(Color.yellow);
		}
		g.drawRect(768, 125, 64, 64);
		g.drawString("5", 785, 174);
		
		if(levelSelect == 6) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 6) {
			g.setColor(Color.yellow);
		}
		g.drawRect(128, 225, 64, 64);
		g.drawString("6", 145, 274);
		
		if(levelSelect == 7) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 7) {
			g.setColor(Color.yellow);
		}
		g.drawRect(288, 225, 64, 64);
		g.drawString("7", 304, 274);
		
		if(levelSelect == 8) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 8) {
			g.setColor(Color.yellow);
		}
		g.drawRect(448, 225, 64, 64);
		g.drawString("8", 464, 274);
		
		if(levelSelect == 9) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 9) {
			g.setColor(Color.yellow);
		}
		g.drawRect(608, 225, 64, 64);
		g.drawString("9", 622, 274);
		
		if(levelSelect == 10) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 10) {
			g.setColor(Color.yellow);
		}
		g.drawRect(768, 225, 64, 64);
		g.drawString("10", 773, 274);
		
		if(levelSelect == 11) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 11) {
			g.setColor(Color.yellow);
		}
		g.drawRect(128, 325, 64, 64);
		g.drawString("11", 141, 374);
		
		if(levelSelect == 12) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 12) {
			g.setColor(Color.yellow);
		}
		g.drawRect(288, 325, 64, 64);
		g.drawString("12", 294, 374);
		
		if(levelSelect == 13) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 13) {
			g.setColor(Color.yellow);
		}
		g.drawRect(448, 325, 64, 64);
		g.drawString("13", 455, 374);
		
		if(levelSelect == 14) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 14) {
			g.setColor(Color.yellow);
		}
		g.drawRect(608, 325, 64, 64);
		g.drawString("14", 613, 374);
		
		if(levelSelect == 15) {
			g.setColor(Color.cyan);
		} else if(levelSelect != 15) {
			g.setColor(Color.yellow);
		}
		g.drawRect(768, 325, 64, 64);
		g.drawString("15", 774, 374);
		
	}
	
}
