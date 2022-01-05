package de.informatik.monopoly.player;

import java.awt.Image;
import java.util.ArrayList;

import de.informatik.monopoly.field.Field;
import de.informatik.monopoly.field.Fields;
import de.informatik.monopoly.game.Trade;

public class Player {

	private String name;
	private int money = 1500;
	private int fieldNumber = 0;
	private int blocked = 0;
	private int gefängnisfrei = 0;
	private Image icon;
	Trade t = new Trade();

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void removeMoney(int amount, ArrayList<Player> playerlist) {
		while ((this.money - amount) < 0 && playerlist.contains(this)) {
			System.out.println(
					"Du hast leider nicht genug Geld, setzte Hypotheken, verkaufe Häuser/Hotels/Karten oder erkläre dich pleite.");
			t.trade(this, playerlist);
		}
		if (playerlist.contains(this))
			this.money -= amount;
	}

	public boolean removeMoney(int amount, ArrayList<Player> playerlist, boolean auswahl) {
		while ((this.money - amount) < 0 && playerlist.contains(this)) {
			System.out.println(
					"Du hast leider nicht genug Geld, setzte Hypotheken, verkaufe Häuser/Hotels/Karten oder erkläre dich pleite.");
			return t.trade(this, playerlist);
		}
		if (playerlist.contains(this)) {
			this.money -= amount;
			return true;
		} else {
			return false;
		}
	}

	public void addMoney(int amount) {
		this.money += amount;
	}

	public String moneyMessage() {
		return "[Aktueller Geldstand: " + this.getMoney() + "]";
	}

	public int getBlocked() {
		return blocked;
	}

	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}

	public int getGefängnisfrei() {
		return gefängnisfrei;
	}

	public void setGefängnisfrei(int gefängnisfrei) {
		this.gefängnisfrei = gefängnisfrei;
	}

	public int getFieldNumber() {
		return fieldNumber;
	}

	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	public int rollDice() {
		return (int) (Math.random() * 6 + 1);
	}

	public void pay(int amount, Player to, ArrayList<Player> playerlist) {
		this.removeMoney(amount, playerlist);
		to.addMoney(amount);
	}

	public void sellCard(Player buyer, Field field, int price, ArrayList<Player> playerlist) {
		field.setOwner(buyer);
		buyer.pay(price, this, playerlist);
		// System.out.println("Der Spieler " + this.getName() + " hat sein Feld \"" +
		// field.getName() + "\" für " + price + "$ an den Spieler " + buyer.getName() +
		// " verkauft.");
	}

	public void move(int amount) {
		int newField = this.getFieldNumber() + amount;
		if (newField > (Fields.fi.length - 1)) {
			newField = newField % Fields.fi.length;
			if (newField != 0) {
				this.addMoney(Fields.fi[0].getPrice());
				System.out.println("Er/Sie ist über \"" + Fields.fi[0].getName() + "\" gelaufen und zieht daher "
						+ Fields.fi[0].getPrice() + "$ ein! " + this.moneyMessage());
			}
		}
		this.setFieldNumber(newField);
	}

	public void moveBack(int amount) {
		int newField = this.getFieldNumber() - amount;
		if (newField < 0) {
			newField = Fields.fi.length + newField;
		}
		this.setFieldNumber(newField);
	}

	public boolean hasWholeGroup(Field field) {
		boolean wholeGroup = true;
		for (Field f : Fields.fi) {
			if (f.getGroup() == field.getGroup()) {
				if (f.getOwner() != this)
					wholeGroup = false;
			}
		}
		return wholeGroup;
	}

	public int howManyFromGroup(Field field) {
		int amount = 0;
		for (Field f : Fields.fi) {
			if (f.getGroup() == field.getGroup()) {
				if (f.getOwner() == this)
					amount++;
			}
		}
		return amount;
	}

	public int getHouses() {
		int amount = 0;
		for (Field f : Fields.fi) {
			if (f.getOwner() == this) {
				amount += f.getHouse();
			}
		}
		return amount;
	}

	public int getHotels() {
		int amount = 0;
		for (Field f : Fields.fi) {
			if (f.getOwner() == this) {
				amount += f.getHotel();
			}
		}
		return amount;
	}

	public String getInformationInMessage() {
		return this.getName() + " --> Money: " + this.getMoney() + "$, Field: "
				+ Fields.fi[this.getFieldNumber()].getName() + " [" + this.getFieldNumber() + "]";
	}

	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}

}
