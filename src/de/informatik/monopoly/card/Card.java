package de.informatik.monopoly.card;

public class Card {

	private CardType type;
	private String text;
	private int money;
	private int location;

	public Card(CardType type, String text, int money, int location) {
		this.type = type;
		this.text = text;
		this.money = money;
		this.location = location;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

}
