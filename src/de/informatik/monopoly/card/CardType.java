package de.informatik.monopoly.card;

public enum CardType {

	BEWEGUNG, ERHALTUNG, ZAHLUNG, GEFÄNGNIS, HÄUSER, GEFÄNGNISFREI, SPEZIAL;

	public int getID() {
		return this.ordinal();
	}

}
