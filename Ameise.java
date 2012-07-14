/*
 * By: Thomas Buck <taucher.bodensee@gmail.com>
 * Visit: www.xythobuz.org
 */
import javax.swing.*;
import java.awt.*;

public class Ameise extends JFrame {
	int width = 400;
	int height = 400;

	int daten[][] = new int[width][height];
	
	int ameiseX = width / 2;
	int ameiseY = height / 2;
	int ameiseRichtung = 1; // 1 hoch, 2 rechts, 3 runter, 4 links
	// int ameiseColor = 1; // 1 Weiß, 0 Schwarz

	public Ameise() {
		this.setTitle("Ant Simulator :)");
		this.setPreferredSize(new Dimension(width, height));
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				daten[i][j] = 0;
			}
		}
	}

	public void moveAmeise() {
		switch(ameiseRichtung) {
			case 1: // Up
				if (ameiseY > 0) {
					ameiseY--;
				} else {
					ameiseY = (height - 1);
				}
				break;
			case 2: // right
				if (ameiseX < (width - 1)) {
					ameiseX++;
				} else {
					ameiseX = 0;
				}
				break;
			case 3: // down
				if (ameiseY < (height - 1)) {
					ameiseY++;
				} else {
					ameiseY = 0;
				}
				break;
			case 4: // links
				if (ameiseX > 0) {
					ameiseX--;
				} else {
					ameiseX = (width - 1);
				}
				break;
		}
		switch(daten[ameiseX][ameiseY]) {
			case 0:
				moveLeft();
				daten[ameiseX][ameiseY] = 1;
				break;
			case 1:
				moveRight();
				daten[ameiseX][ameiseY] = 0;
				break;
		}
		/* if (ameiseColor == 0) {
			ameiseColor = 1;
		} else {
			ameiseColor = 0;
		}
		daten[ameiseX][ameiseY] = ameiseColor; */
	}

	void moveRight() {
		moveLeft();
		moveLeft();
		moveLeft();
	}

	void moveLeft() {
		switch(ameiseRichtung) {
			case 1:
				ameiseRichtung = 4;
				break;
			case 2:
				ameiseRichtung = 1;
				break;
			case 3:
				ameiseRichtung = 2;
				break;
			case 4:
				ameiseRichtung = 3;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				if (daten[x][y] != 0) {
					g.setColor(Color.black);
				} else {
					g.setColor(Color.white);
				}
				g.drawLine(x, y, x, y);
			}
		}
	}

	public static void main(String[] args) {
		Ameise ameise = new Ameise();
		for (int i = 0; i < 15000; i++) {
			ameise.moveAmeise();
		}
	}
}