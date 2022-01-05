package de.informatik.monopoly.field;

public enum FieldGroup {

	BROWN, CYAN, PINK, ORANGE, RED, YELLOW, GREEN, BLUE, TRAINSTATION, COMPANY, CARDS, SPECIAL;

	public int getID() {
		return this.ordinal();
	}

}
