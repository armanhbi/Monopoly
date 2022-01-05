package de.informatik.monopoly.field;

import java.awt.Color;

import de.informatik.rules.Rules;

public class Fields {

	public static Field[] fi = new Field[40];

	public Fields() {

		fi[0] = new Field(0, "LOS", 200, FieldGroup.SPECIAL);
		fi[1] = new Field(1, "Badstraﬂe", 60, 2, 10, 30, 90, 160, 250, FieldGroup.BROWN);
		fi[2] = new Field(2, "Gemeinschaftsfeld", FieldGroup.CARDS);
		fi[3] = new Field(3, "Turmstraﬂe", 60, 4, 20, 60, 180, 320, 450, FieldGroup.BROWN);
		fi[4] = new Field(4, "Einkommensteuer", 0, 200, FieldGroup.SPECIAL);
		fi[5] = new Field(5, "S¸dbahnhof", 200, FieldGroup.TRAINSTATION);
		fi[6] = new Field(6, "Chausseestraﬂe", 100, 6, 30, 90, 270, 400, 550, FieldGroup.CYAN);
		fi[7] = new Field(7, "Ereignisfeld", FieldGroup.CARDS);
		fi[8] = new Field(8, "Elisenstraﬂe", 100, 6, 30, 90, 270, 400, 550, FieldGroup.CYAN);
		fi[9] = new Field(9, "Poststraﬂe", 120, 8, 40, 100, 300, 450, 600, FieldGroup.CYAN);
		fi[10] = new Field(10, "Im Gef‰ngnis/Nur zu Besuch", FieldGroup.SPECIAL);
		fi[11] = new Field(11, "Seestraﬂe", 140, 10, 50, 150, 450, 625, 750, FieldGroup.PINK);
		fi[12] = new Field(12, "Elektrizit‰tswerk", 150, FieldGroup.COMPANY);
		fi[13] = new Field(13, "Hafenstraﬂe", 140, 10, 50, 150, 450, 625, 750, FieldGroup.PINK);
		fi[14] = new Field(14, "Neue Straﬂe", 160, 12, 60, 180, 500, 700, 900, FieldGroup.PINK);
		fi[15] = new Field(15, "Westbahnhof", 200, FieldGroup.TRAINSTATION);
		fi[16] = new Field(16, "M¸nchnerstraﬂe", 180, 14, 70, 200, 550, 750, 950, FieldGroup.ORANGE);
		fi[17] = new Field(17, "Gemeinschaftsfeld", FieldGroup.CARDS);
		fi[18] = new Field(18, "Wieserstraﬂe", 180, 14, 70, 200, 550, 750, 950, FieldGroup.ORANGE);
		fi[19] = new Field(19, "Berlinerstraﬂe", 200, 16, 80, 220, 600, 800, 1000, FieldGroup.ORANGE);
		fi[20] = new Field(20, "Frei Parken", FieldGroup.SPECIAL);
		fi[21] = new Field(21, "Theaterstraﬂe", 220, 18, 90, 250, 700, 875, 1050, FieldGroup.RED);
		fi[22] = new Field(22, "Ereignisfeld", FieldGroup.CARDS);
		fi[23] = new Field(23, "Museumstraﬂe", 220, 18, 90, 250, 700, 875, 1050, FieldGroup.RED);
		fi[24] = new Field(24, "Opernplatz", 240, 20, 100, 300, 750, 925, 1100, FieldGroup.RED);
		fi[25] = new Field(25, "Nordbahnhof", 200, FieldGroup.TRAINSTATION);
		fi[26] = new Field(26, "Lessingstraﬂe", 260, 22, 110, 330, 800, 975, 1150, FieldGroup.YELLOW);
		fi[27] = new Field(27, "Schillerstraﬂe", 260, 22, 110, 330, 800, 975, 1150, FieldGroup.YELLOW);
		fi[28] = new Field(28, "Wasserwerk", 150, FieldGroup.COMPANY);
		fi[29] = new Field(29, "Goethestraﬂe", 280, 24, 120, 360, 850, 1025, 1200, FieldGroup.YELLOW);
		fi[30] = new Field(30, "Gehen Sie in das Gef‰gnis", FieldGroup.SPECIAL);
		fi[31] = new Field(31, "Rathhausplatz", 300, 26, 130, 390, 900, 1100, 1275, FieldGroup.GREEN);
		fi[32] = new Field(32, "Hauptstraﬂe", 300, 26, 130, 390, 900, 1100, 1275, FieldGroup.GREEN);
		fi[33] = new Field(33, "Gemeinschaftsfeld", FieldGroup.CARDS);
		fi[34] = new Field(34, "Bahnhofstraﬂe", 320, 28, 150, 450, 1000, 1200, 1400, FieldGroup.GREEN);
		fi[35] = new Field(35, "Hauptbahnhof", 200, FieldGroup.TRAINSTATION);
		fi[36] = new Field(36, "Ereignisfeld", FieldGroup.CARDS);
		fi[37] = new Field(37, "Parkstraﬂe", 350, 35, 175, 500, 1100, 1300, 1500, FieldGroup.BLUE);
		fi[38] = new Field(38, "Zusatzsteuer", 0, 100, FieldGroup.SPECIAL);
		fi[39] = new Field(39, "Schlossallee", 400, 50, 200, 600, 1400, 1700, 2000, FieldGroup.BLUE);
	}

	public int calculateTrainMortage(int amountOfTS) {
		if (amountOfTS == 1) {
			return Rules.mortageTrainStation;
		} else {
			return calculateTrainMortage(amountOfTS - 1) * 2;
		}
	}

	public static Color getColor(int id) {
		//System.out.println(id + " " + fi[id].getGroup());
		if (fi[id].getGroup() == FieldGroup.BROWN) {
			return Color.decode("#836953");
		} else if (fi[id].getGroup() == FieldGroup.CYAN) {
			return Color.decode("#92ddea");
		} else if (fi[id].getGroup() == FieldGroup.PINK) {
			return Color.decode("#f2a2e8");
		} else if (fi[id].getGroup() == FieldGroup.ORANGE) {
			return Color.decode("#f8b88b");
		} else if (fi[id].getGroup() == FieldGroup.RED) {
			return Color.decode("#fea3aa");
		} else if (fi[id].getGroup() == FieldGroup.YELLOW) {
			return Color.decode("#faf884");
		} else if (fi[id].getGroup() == FieldGroup.GREEN) {
			return Color.decode("#baed91");
		} else if (fi[id].getGroup() == FieldGroup.BLUE) {
			return Color.decode("#b2cefe");
		} else if (fi[id].getGroup() == FieldGroup.TRAINSTATION) {
			return Color.decode("#9399ad");
		} else if (fi[id].getGroup() == FieldGroup.COMPANY) {
			return Color.decode("#9399ad");
		} else if (fi[id].getGroup() == FieldGroup.CARDS) {
			return Color.decode("#9399ad");
		} else if (fi[id].getGroup() == FieldGroup.SPECIAL) {
			return Color.decode("#9399ad");
		} else {
			return null;
		}
	}

}
