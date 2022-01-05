package de.informatik.monopoly.card;

import java.util.ArrayList;

import de.informatik.monopoly.field.Fields;
import de.informatik.rules.Rules;

public class Cards {

	ArrayList<Card> cards = new ArrayList<>();

	public Cards() {
		String overGO = "" + Fields.fi[0].getPrice();
		String doubled = "";
		if (Rules.doppleOnGO)
			doubled = "" + (Fields.fi[0].getPrice() * 2);

		Card a = new Card(CardType.BEWEGUNG, "Rücken Sie vor bis zur Schlossallee.", 0, 39);
		Card b = new Card(CardType.BEWEGUNG,
				"Machen Sie einen Ausflug zum Südbahnhof. Wenn Sie über Los kommen, ziehen Sie M " + overGO + " ein.",
				0, 5);
		Card c = new Card(CardType.BEWEGUNG,
				"Rücken Sie vor bis zum Opernplatz. Wenn Sie über Los kommen, ziehen Sie M " + overGO + " ein.", 0, 24);
		Card d = new Card(CardType.BEWEGUNG, "Rücken Sie vor bis auf Los. (Ziehe M " + doubled + " ein).", 0, 0);
		Card e = new Card(CardType.BEWEGUNG,
				"Rücken Sie vor bis zur Seestraße. Wenn Sie über Los kommen, ziehen Sie M " + overGO + " ein.", 0, 11);
		Card f = new Card(CardType.BEWEGUNG, "Rücken Sie vor bis auf Los. (Ziehe M " + doubled + " ein).", 0, 0);
		Card g = new Card(CardType.BEWEGUNG, "Rücken Sie vor bis zum Wasserwerk.", 0, 28);

		Card h = new Card(CardType.ERHALTUNG, "Ihr Bausparvertrag wird fällig. Sie erhalten M 200.", 200, -1);
		Card i = new Card(CardType.ERHALTUNG, "Die Bank zahlt Ihnen eine Dividende von M 50.", 50, -1);
		Card j = new Card(CardType.ERHALTUNG, "Ihr Bausparvertrag wird fällig. Sie erhalten M 200.", 200, -1);
		Card k = new Card(CardType.ERHALTUNG, "Urlaubsgeld! Sie erhalten M 100.", 100, -1);
		Card l = new Card(CardType.ERHALTUNG, "Ihre Lebensversicherung wird fällig. Sie erhalten M 100.", 100, -1);
		Card m = new Card(CardType.ERHALTUNG, "Einkommenssteuerrückerstattung. Sie erhalten M 20.", 20, -1);
		Card n = new Card(CardType.ERHALTUNG, "Sie erhalten auf Vorzugs-Aktien 7% Dividende: M 25.", 25, -1);
		Card o = new Card(CardType.ERHALTUNG, "Sie erben M 100.", 100, -1);
		Card p = new Card(CardType.ERHALTUNG, "Aus Lagerverkäufen erhalten Sie M 50.", 50, -1);
		Card q = new Card(CardType.ERHALTUNG, "Zweiter Preis im Schönheitswettbewerb. Sie erhalten M 10.", 10, -1);
		Card r = new Card(CardType.ERHALTUNG, "Bank-Irrtum zu Ihren Gunsten. Ziehen Sie M 200 ein.", 200, -1);

		Card s = new Card(CardType.ZAHLUNG, "Strafzettel! Zahlen Sie M 15.", 15, -1);
		Card t = new Card(CardType.ZAHLUNG, "Schulgeld. Zahlen Sie M 50.", 50, -1);
		Card u = new Card(CardType.ZAHLUNG, "Arzt-Kosten. Zahlen Sie M 50.", 50, -1);
		Card v = new Card(CardType.ZAHLUNG, "Krankenhausgebühren. Zahlen Sie M 100.", 100, -1);

		Card w = new Card(CardType.GEFÄNGNIS,
				"Gehen Sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht über Los. Ziehen Sie nicht M "
						+ overGO + " ein.",
				0, 0);
		Card x = new Card(CardType.GEFÄNGNIS,
				"Gehen Sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht über Los. Ziehen Sie nicht M "
						+ overGO + " ein.",
				0, 0);

		Card y = new Card(CardType.HÄUSER,
				"Sie lassen Ihre Häuser renovieren. Zahlen Sie: M 25 pro Haus, M 100 pro Hotel.", 0, 1);
		Card z = new Card(CardType.HÄUSER,
				"Sie werden zu Straßenausbesserungsarbeiten herangezogen. Zahlen Sie M 40 je Haus und M 115 je Hotel an die Bank.",
				0, 2);

		Card a2 = new Card(CardType.GEFÄNGNISFREI,
				"Sie kommen aus dem Gefängnis frei! Behalten Sie diese Karte, bis Sie sie benötigen oder verkaufen.", 0,
				0);
		Card b2 = new Card(CardType.GEFÄNGNISFREI,
				"Sie kommen aus dem Gefängnis frei! Behalten Sie diese Karte, bis Sie sie benötigen oder verkaufen.", 0,
				0);

		Card c2 = new Card(CardType.SPEZIAL, "Sie haben Geburtstag. Jeder Spieler schenkt Ihnen M 10.", 0, 0);
		Card d2 = new Card(CardType.SPEZIAL, "Sie sind zum Vorstand gewählt worden. Zahlen Sie jedem Spieler M 50.", 0,
				1);
		Card e2 = new Card(CardType.SPEZIAL, "Gehen Sie 3 Felder zurück.", 0, 2);
		Card f2 = new Card(CardType.SPEZIAL, "Rücken Sie vor bis zum nächsten Versorgungswerk.", 0, 3);

		cards.add(a); // 0
		cards.add(b);
		cards.add(c);
		cards.add(d);
		cards.add(e);
		cards.add(f); // 5
		cards.add(g);
		cards.add(h);
		cards.add(i);
		cards.add(j);
		cards.add(k); // 10
		cards.add(l);
		cards.add(m);
		cards.add(n);
		cards.add(o);
		cards.add(p); // 15
		cards.add(q);
		cards.add(r);
		cards.add(s);
		cards.add(t);
		cards.add(u); // 20
		cards.add(v);
		cards.add(w);
		cards.add(x);
		cards.add(y);
		cards.add(z); // 25
		cards.add(a2);
		cards.add(b2);
		cards.add(c2);
		cards.add(d2);
		cards.add(e2); // 30
		cards.add(f2);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

}
