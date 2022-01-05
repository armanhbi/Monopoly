package de.informatik.monopoly.game;

import java.util.ArrayList;
import java.util.Scanner;

import de.informatik.monopoly.field.Field;
import de.informatik.monopoly.player.Player;
import de.informatik.rules.Rules;

public class ScanMessage {

	public void buyOrNot(Player p, Field cur, ArrayList<Player> playerlist) {
		@SuppressWarnings("resource")
		String input = new Scanner(System.in).nextLine();
		if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("ja") || input.equalsIgnoreCase("yes")) {
			boolean b = p.removeMoney(cur.getPrice(), playerlist, true);
			if (b) {
				cur.setOwner(p);
				System.out.println("Der Spieler " + p.getName() + " hat das Feld \"" + cur.getName() + "\" für "
						+ cur.getPrice() + "$ gekauft.");
			} else {
				System.out.println("Du hast das Feld \"" + cur.getName() + "\" nicht gekauft!");
			}
		} else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("nein") || input.equalsIgnoreCase("no")) {
			System.out.println("Du hast das Feld \"" + cur.getName() + "\" nicht gekauft!");
		} else {
			System.out.println("Deine Eingabe war ungültig, bitte versuche es erneut!");
			buyOrNot(p, cur, playerlist);
		}
	}

	public boolean useCard(Player p) {
		@SuppressWarnings("resource")
		String input = new Scanner(System.in).nextLine();
		if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("ja") || input.equalsIgnoreCase("yes")) {
			p.setBlocked(0);
			p.setGefängnisfrei(p.getGefängnisfrei() - 1);
			System.out
					.println("Du hast deine Gefängnisfreikarte genutzt und kannst jetzt wieder normal weiter spielen.");
			return true;
		} else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("nein") || input.equalsIgnoreCase("no")) {
			System.out.println("Du hast deine Gefängnisfreikarte nicht benutzt.");
			return false;
		} else {
			System.out.println("Deine Eingabe war ungültig, bitte versuche es erneut!");
			return useCard(p);
		}
	}

	public boolean buyOutWithMoney(Player p, ArrayList<Player> playerlist) {
		@SuppressWarnings("resource")
		String input = new Scanner(System.in).nextLine();
		if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("ja") || input.equalsIgnoreCase("yes")) {
			boolean b = p.removeMoney(Rules.prisonDeposit, playerlist, true);
			if (b) {
				p.setBlocked(0);
				System.out.println("Du hast die Kaution bezahlt und kannst jetzt normal weiter spielen.");
				return true;
			} else {
				System.out.println("Du hast die Kaution nicht bezahlt.");
				return false;
			}
		} else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("nein") || input.equalsIgnoreCase("no")) {
			System.out.println("Du hast die Kaution nicht bezahlt.");
			return false;
		} else {
			System.out.println("Deine Eingabe war ungültig, bitte versuche es erneut!");
			return buyOutWithMoney(p, playerlist);
		}
	}

}
