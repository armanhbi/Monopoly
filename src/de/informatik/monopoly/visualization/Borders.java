package de.informatik.monopoly.visualization;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import de.informatik.monopoly.field.Field;
import de.informatik.monopoly.field.Fields;
import de.informatik.monopoly.game.Game;
import de.informatik.monopoly.game.Trade;
import de.informatik.monopoly.player.Player;
import de.informatik.rules.Rules;

public class Borders extends JLabel {

	private static final long serialVersionUID = 1L;

	static int factor = 11;
	static double factor2 = 1.25;
	static int a, b;
	int w = GUI.w;
	int h = GUI.h;

	static int clicked = 1;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);

		// Abtrennung zwischen Spielfeld und dem Rest

		new FieldLocations(h, (int) (h / factor * factor2), factor);
		FieldLocation[] fLs = FieldLocations.fLs;

		for (int i = 0; i < 40; i++) {
			FieldLocation cur = fLs[i];
			g.setColor(Fields.getColor(i));
			// System.out.println(i + " --> " + cur.getX() + " | " + cur.getY());
			g.fillRect(cur.getX(), cur.getY(), cur.getW(), cur.getH());
			/*
			 * g.setColor(Color.black); g.setFont(new Font("Century Gothic Bold", 0, 8)); if
			 * (i <= 10) g.drawString(Fields.fi[i].getName(), cur.getX()+20, cur.getY()+40);
			 */
		}

		g.setColor(Color.BLACK);
		a = (int) (h / factor * factor2);
		g.drawLine(a, 0, a, h);
		for (int i = 1; i < factor - 2; i++) {
			b = (int) ((h - 2 * a) / (factor - 2) * i);
			g.drawLine(a + b, 0, a + b, h);
		}
		g.drawLine(h - a, 0, h - a, h);

		g.drawLine(0, a, h, a);
		for (int i = 1; i < factor - 2; i++) {
			b = (int) ((h - 2 * a) / (factor - 2) * i);
			g.drawLine(0, a + b, h, a + b);
		}
		g.drawLine(0, h - a, h, h - a);

		g.setColor(Color.BLACK);
		g.drawRect(0, 0, h, h);

		g.drawLine(h + 20, 45, w - 20, 45);

		Field clickedField = Fields.fi[clicked];
		g.setFont(new Font("Century Gothic Bold", 0, 28));
		g.drawString("" + clickedField.getName(), h + 20, 40);
		g.setFont(new Font("Century Gothic Bold", 0, 20));
		if (clickedField.getOwner() == null) {
			g.drawString("Gehört niemandem", h + 20, 80);
		} else {
			g.drawString("Gehört: " + clickedField.getOwner().getName(), h + 20, 80);
		}
		g.drawString("Preis: " + clickedField.getPrice(), h + 20, 110);
		if (clickedField.getMortage() == 0) {
			g.drawString("Keine Miete", h + 20, 140);
		} else {
			g.drawString("Miete: " + clickedField.getMortage(), h + 20, 140);
		}
		if (clickedField.getHouse() == 0) {
			g.drawString("Keine Häuser", h + 20, 170);
		} else {
			g.drawString("Häuser: " + clickedField.getHouse(), h + 20, 170);
		}
		if (clickedField.getHouse() == 0) {
			g.drawString("Keine Hotels", h + 20, 200);
		} else {
			g.drawString("Hotel: ja", h + 20, 200);
		}

		g.setFont(new Font("Century Gothic Bold", 0, 15));
		g.drawString(Game.p.getName() + " ist an der Reihe!", 776, 393);

		for (int i = 0; i < Game.playerlist.size(); i++) {
			Player p = Game.playerlist.get(i);
			g.drawString(p.getName() + "s Geld: " + p.getMoney(), 776, 413 + i * 20);
		}

		g.setFont(new Font("Century Gothic Bold", 0, 10));
		g.drawString(Game.text, 750, 500);

		g.drawString("Hypothekenpreis: " + clickedField.getPrice() / 2, h + 20, 230);
		g.setFont(new Font("Century Gothic Bold", 0, 15));
		if (clickedField.isHypothek()) {
			g.drawString("Gebäude ist ein Hypothek.", h + 20, 260);
			g.setFont(new Font("Century Gothic", 0, 15));
			g.drawString(
					"Auflösungskosten: " + (int) ((1 + Rules.hypothekDissolveRate) * (clickedField.getPrice() / 2)),
					h + 50, 280);
		} else {
			g.drawString("Gebäude ist kein Hypothek.", h + 20, 260);
		}

		g.setColor(Color.WHITE);
		g.fillRect(a + 1, a + 1, h - 2 * a - 1, h - 2 * a - 1);

		BufferedImage head;
		try {
			head = ImageIO.read(new File("rcs/icons/monopoly/head.jpg"));
			g.drawImage(head, h / 2 - head.getHeight() / 2, h / 2 - head.getHeight() / 2, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage freeparking;
		try {
			freeparking = ImageIO.read(new File("rcs/icons/monopoly/freeparking.jpg"));
			g.drawImage(freeparking, 5, 30, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage go;
		try {
			go = ImageIO.read(new File("rcs/icons/monopoly/go.jpg"));
			g.drawImage(go, 675, 680, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage gotoprison;
		try {
			gotoprison = ImageIO.read(new File("rcs/icons/monopoly/gotoprison.jpg"));
			g.drawImage(gotoprison, 675, 15, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage prison;
		try {
			prison = ImageIO.read(new File("rcs/icons/monopoly/prison.jpg"));
			g.drawImage(prison, 10, 680, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage dice;
		try {
			dice = ImageIO.read(new File("rcs/icons/monopoly/dice.jpg"));
			g.drawImage(dice, 880, 650, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage money;
		try {
			money = ImageIO.read(new File("rcs/icons/monopoly/money.jpg"));
			g.drawImage(money, 775, 680, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < Game.playerlist.size(); i++) {
			try {
				BufferedImage icon = null;
				if (Game.playerlist.get(i).getName().equalsIgnoreCase("Eschweiler")) {
					icon = ImageIO.read(new File("rcs/icons/player/icon_eschweiler.png"));
				} else if (Game.playerlist.get(i).getName().equalsIgnoreCase("Tilman")) {
					icon = ImageIO.read(new File("rcs/icons/player/icon_tilman.png"));
				} else if (Game.playerlist.get(i).getName().equalsIgnoreCase("Arman")) {
					icon = ImageIO.read(new File("rcs/icons/player/icon_arman.png"));
				} else {
					icon = ImageIO.read(new File("rcs/icons/player/icon_unknown.png"));
				}
				g.drawImage(icon, fLs[Game.playerlist.get(i).getFieldNumber()].getX(),
						fLs[Game.playerlist.get(i).getFieldNumber()].getY(), null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		BufferedImage icon_eschweiler;
		try {
			icon_eschweiler = ImageIO.read(new File("rcs/icons/player/icon_eschweiler.png"));
			if (Trade.getPlayer(Game.playerlist, "Eschweiler") != null) {
				g.drawImage(icon_eschweiler,
						fLs[Trade.getPlayer(Game.playerlist, "Eschweiler").getFieldNumber()].getX(),
						fLs[Trade.getPlayer(Game.playerlist, "Eschweiler").getFieldNumber()].getY(), null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage icon_arman;
		try {
			icon_arman = ImageIO.read(new File("rcs/icons/player/icon_arman.png"));
			if (Trade.getPlayer(Game.playerlist, "Arman") != null) {
				g.drawImage(icon_arman, fLs[Trade.getPlayer(Game.playerlist, "Arman").getFieldNumber()].getX(),
						fLs[Trade.getPlayer(Game.playerlist, "Arman").getFieldNumber()].getY(), null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage icon_tilman;
		try {
			icon_tilman = ImageIO.read(new File("rcs/icons/player/icon_tilman.png"));
			if (Trade.getPlayer(Game.playerlist, "Tilman") != null) {
				g.drawImage(icon_tilman, fLs[Trade.getPlayer(Game.playerlist, "Tilman").getFieldNumber()].getX(),
						fLs[Trade.getPlayer(Game.playerlist, "Tilman").getFieldNumber()].getY(), null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		repaint();
	}

}
