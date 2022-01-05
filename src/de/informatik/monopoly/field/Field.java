package de.informatik.monopoly.field;

import de.informatik.monopoly.player.Player;

public class Field {

	private int number;
	private String name;
	private int price = 0;
	private int mortage = 0;
	private int house1Mortage, house2Mortage, house3Mortage, house4Mortage, hotelMortage;
	private FieldGroup group = null;
	private Player owner = null;
	private boolean hypothek = false;
	private int house = 0;
	private int hotel = 0;

	public Field(int number, String name, FieldGroup group) {
		this.number = number;
		this.name = name;
		this.group = group;
	}

	public Field(int number, String name, int price, FieldGroup group) {
		this.number = number;
		this.name = name;
		this.price = price;
		this.group = group;
	}

	public Field(int number, String name, int price, int mortage, FieldGroup group) {
		this.number = number;
		this.name = name;
		this.price = price;
		this.mortage = mortage;
		this.group = group;
	}

	public Field(int number, String name, int price, int mortage, int house1Mortage, int house2Mortage,
			int house3Mortage, int house4Mortage, int hotelMortage, FieldGroup group) {
		this.number = number;
		this.name = name;
		this.price = price;
		this.mortage = mortage;
		this.group = group;
		this.house1Mortage = house1Mortage;
		this.house2Mortage = house2Mortage;
		this.house3Mortage = house3Mortage;
		this.house4Mortage = house4Mortage;
		this.hotelMortage = hotelMortage;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int id) {
		this.number = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMortage() {
		return mortage;
	}

	public void setMortage(int mortage) {
		this.mortage = mortage;
	}

	public FieldGroup getGroup() {
		return group;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public boolean isHypothek() {
		return hypothek;
	}

	public void setHypothek(boolean hypothek) {
		this.hypothek = hypothek;
	}

	public int getHouse() {
		return house;
	}

	public void setHouse(int house) {
		this.house = house;
	}

	public int getHotel() {
		return hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

	public int getHouse1Mortage() {
		return house1Mortage;
	}

	public void setHouse1Mortage(int house1Mortage) {
		this.house1Mortage = house1Mortage;
	}

	public int getHouse2Mortage() {
		return house2Mortage;
	}

	public void setHouse2Mortage(int house2Mortage) {
		this.house2Mortage = house2Mortage;
	}

	public int getHouse3Mortage() {
		return house3Mortage;
	}

	public void setHouse3Mortage(int house3Mortage) {
		this.house3Mortage = house3Mortage;
	}

	public int getHouse4Mortage() {
		return house4Mortage;
	}

	public void setHouse4Mortage(int house4Mortage) {
		this.house4Mortage = house4Mortage;
	}

	public int getHotelMortage() {
		return hotelMortage;
	}

	public void setHotelMortage(int hotelMortage) {
		this.hotelMortage = hotelMortage;
	}

	public int getHousePrice() {
		if (group.getID() == 0 || group.getID() == 1) {
			return 50;
		} else if (group.getID() == 2 || group.getID() == 3) {
			return 100;
		} else if (group.getID() == 4 || group.getID() == 5) {
			return 150;
		} else if (group.getID() == 6 || group.getID() == 7) {
			return 200;
		} else {
			return 0;
		}
	}

	public int getNextCompany() {
		int id = this.getNumber();
		while (Fields.fi[id].getGroup() != FieldGroup.COMPANY) {
			id++;
			if (id >= 39)
				id = 0;
		}
		return id;
	}

}
