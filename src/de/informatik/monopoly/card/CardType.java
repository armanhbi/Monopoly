package de.informatik.monopoly.card;

public enum CardType {

	BEWEGUNG, ERHALTUNG, ZAHLUNG, GEF�NGNIS, H�USER, GEF�NGNISFREI, SPEZIAL;

	public int getID() {
		return this.ordinal();
	}

}
