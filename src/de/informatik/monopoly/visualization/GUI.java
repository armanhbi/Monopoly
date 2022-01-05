package de.informatik.monopoly.visualization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static int h = 750;
	static int w = 1000;
	boolean rolledDice = false;

	public GUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation(screenSize.width / 2 - w / 2, screenSize.height / 2 - h / 2);
		setSize(w, h + 30);
		setResizable(false);
		setTitle("Monopoly");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Borders border = new Borders();
		add(border);

		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(e.getX() + "|" + e.getY());
				if (e.getX() < h && e.getX() > 0) {
					int i = getFieldWhichGotClicked(e.getX(), e.getY());
					if (i > -1) {
						Borders.clicked = i;
					}
				} else {
					if (e.getX() > 882 && e.getX() < 882 + 80 && e.getY() > 651 && e.getY() < 651 + 88) {
						rolledDice = true;
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		setVisible(true);
	}

	public int getFieldWhichGotClicked(int x, int y) {
		for (int i = 0; i < FieldLocations.fLs.length; i++) {
			FieldLocation cur = FieldLocations.fLs[i];
			if (x > cur.getX() && x < (cur.getX() + cur.getW()) && y > cur.getY() && y < (cur.getY() + cur.getH())) {
				return i;
			}
		}
		return -1;
	}

	public boolean rollDice() {
		if (rolledDice) {
			rolledDice = false;
			return true;
		} else {
			return false;
		}
	}

}
