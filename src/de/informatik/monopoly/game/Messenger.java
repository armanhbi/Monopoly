package de.informatik.monopoly.game;

import java.util.ArrayList;

import de.informatik.monopoly.field.Fields;
import de.informatik.monopoly.player.Player;
import de.informatik.rules.Rules;

public class Messenger {

	public void sendInformation(ArrayList<Player> playerlist) {
		System.out.println("\n#############-->INFORMATIONEN<--#############");
		for (Player a : playerlist) {
			System.out.println(a.getInformationInMessage());
		}
		System.out.println("#############################################\n");
	}

	public void helpMenu() {
		System.out.println("#################-->BEFEHLE<--#################");
		System.out.println("hypothek:FELDNUMMER:true/false");
		if (Rules.buyHotelDirectly)
			System.out.println("buy:haus/hotel:FELDNUMMER");
		else
			System.out.println("buy:haus:FELDNUMMER");
		System.out.println("sell:haus/hotel:FELDNUMMER");
		System.out.println("showfields(:detail)");
		System.out.println("showmyfields(:detail)");
		System.out.println("trade:PLAYERNAME:p1felder,p2felder:p1geld,p2geld");
		System.out.println("showplayers");
		System.out.println("pleite");
		System.out.println("info");
		System.out.println("stop");
		System.out.println("help");
		System.out.println("###############################################");
	}

	public void helpMenuStart() {
		System.out.println("#################-->BEFEHLE<--#################");
		System.out.println("startgame");
		System.out.println("addplayer:NAME");
		System.out.println("removeplayer:NAME");
		System.out.println("showplayer");
		System.out.println("help");
		System.out.println("###############################################");
	}

	public void showfields(boolean detailed) {
		for (int i = 0; i < Fields.fi.length; i++) {
			String message = Fields.fi[i].getName() + " [" + Fields.fi[i].getNumber() + "] ";
			if (detailed) {
				message += " --> Haus: " + Fields.fi[i].getHouse();
				message += "; Hotel: " + Fields.fi[i].getHotel();
				message += " --> Hypothek: " + Fields.fi[i].isHypothek();
			}
			if (Fields.fi[i].getOwner() != null) {
				message += " --> Owner:" + Fields.fi[i].getOwner().getName();
			}
			System.out.println(message);
		}
	}

	public void showmyfields(Player p, boolean detailed) {
		for (int i = 0; i < Fields.fi.length; i++) {
			if (Fields.fi[i].getOwner() == p) {
				String message = Fields.fi[i].getName() + " [" + Fields.fi[i].getNumber() + "]";
				if (detailed) {
					message += " --> Haus: " + Fields.fi[i].getHouse() + ", ";
					message += "Hotel: " + Fields.fi[i].getHotel();
					message += " --> Hypothek: " + Fields.fi[i].isHypothek();
				}
				System.out.println(message);
			}
		}
	}

	public void showPlayer(ArrayList<Player> playerlist) {
		String message = "Spieler: ";
		for (int i = 0; i < playerlist.size(); i++) {
			message += playerlist.get(i).getName() + ", ";
		}
		message = message.substring(0, message.length() - 2);
		System.out.println(message + "\n");
	}
}
