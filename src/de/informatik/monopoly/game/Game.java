package de.informatik.monopoly.game;

import java.util.ArrayList;

import de.informatik.monopoly.card.Card;
import de.informatik.monopoly.card.CardType;
import de.informatik.monopoly.card.Cards;
import de.informatik.monopoly.field.Field;
import de.informatik.monopoly.field.FieldGroup;
import de.informatik.monopoly.field.Fields;
import de.informatik.monopoly.player.Player;
import de.informatik.monopoly.visualization.GUI;
import de.informatik.rules.Rules;

public class Game {

	Cards cards;
	Fields fields;
	Messenger messenger;
	ScanMessage scanmessage;
	Trade trade;
	GUI gui;
	public static Player p;
	public static String text = "";

	private int dicer;
	private int moneypool = 0;
	private int one, two, doublets = 0;
	public static ArrayList<Player> playerlist = new ArrayList<>();

	public void startGame() {
		loadGame();
		play();
	}

	public void loadGame() {
		// load all data (all fields, cards, messagesender, etc.)
		System.out.println("Lade Monopolyeinstellungen...");
		fields = new Fields();
		cards = new Cards();
		messenger = new Messenger();
		scanmessage = new ScanMessage();
		trade = new Trade();

		// add all players and start game
		System.out.println("Alle Einstellungen wurden geladen, bitte füge nun alle Spieler hinzu...");
		trade.preSettings(playerlist);

		// choose random dicer
		dicer = (int) (Math.random() * playerlist.size());

		// send message -> "game begins", display player
		messenger.showPlayer(playerlist);
		gui = new GUI();
	}

	public void play() {

		p = playerlist.get(dicer);

		if (playerlist.size() > 1) {

			System.out.println("Der Spieler " + p.getName() + " ist an der Reihe!");

			boolean a = gui.rollDice();
			int counter = 0;
			while (!a) {
				a = gui.rollDice();
				if (counter % 1000000000 == 0) {
					System.out.println("Bitte würfeln.");
				}
				counter++;
			}
			one = p.rollDice();
			two = p.rollDice();

			// add doublets if player made a pasch and check if pasch equals 3. no -> normal
			// play; yes -> prison
			if (p.getBlocked() == 0) {
				if (one == two)
					doublets++;
				if (doublets == Rules.howManyDoubletsToGoToPrison) {
					p.setFieldNumber(10);
					p.setBlocked(Rules.roundsBlockedInPrision);
					System.out.println("Der Spieler " + p.getName()
							+ " hat drei mal in Folge einen Pasch gewürfelt und muss daher ins Gefängnis!\n");
					newDicer();
					doublets = 0;
					one = 0;
					two = 0;
					play();
				} else {
					p.move(one + two);
					System.out.println(p.getName() + " hat eine " + one + " und eine " + two
							+ " gewürfelt und ist jetzt auf dem Feld " + p.getFieldNumber() + "!\n");
					action(playerlist.get(dicer));
				}
			} else {
				boolean free = false;
				if (p.getGefängnisfrei() != 0) {
					System.out.println("Möchtest du deine Gefängnisfreikarte einsetzen und aus dem Gefängnis kommen?");
					free = scanmessage.useCard(p);
				}

				if (free == false) {
					System.out.println("Möchtest du dich aus dem Gefängnis frei kaufen?");
					free = scanmessage.buyOutWithMoney(p, playerlist);
				}

				if (free == false) {
					if (one == two) {
						p.setBlocked(0);
						free = true;
					} else {
						if (p.getBlocked() == 1) {
							System.out.println("Du hast jetzt " + Rules.roundsBlockedInPrision
									+ " mal kein Pasch gewürfelt und musst daher die Kaution von " + Rules.prisonDeposit
									+ "$ zahlen um aus dem Gefängnis raus zu kommen.");
							p.removeMoney(Rules.prisonDeposit, playerlist);
							free = true;
						} else {
							System.out.println("Du hast keinen Pasch gewürfelt und musst daher eine Runde aussetzen.");
							p.setBlocked(p.getBlocked() - 1);
							if (Rules.tradingWhilePrison) {
								System.out.println("Allerdings darfst du dein Trading noch machen.");
								trade.trade(p, playerlist);
							}
							newDicer();
							doublets = 0;
							one = 0;
							two = 0;
							play();
						}
					}
				}

				if (free == true) {
					p.move(one + two);
					System.out.println(p.getName() + " hat eine " + one + " und eine " + two
							+ " gewürfelt und ist jetzt auf dem Feld " + p.getFieldNumber() + "!\n");
					action(playerlist.get(dicer));
				}
			}
		} else {
			Player winner = playerlist.get(0);
			System.out.println("Der Spieler " + winner.getName()
					+ " ist der letzte stehende Spieler und gewinnt damit diese Partie Monopoly.\nHerzlichen Glückwunsch!");
			System.out.println(winner.getName() + "'s Geldstand: " + winner.getMoney());
		}
	}

	public void action(Player p) {
		Field current = Fields.fi[p.getFieldNumber()];
		// Fields.fi[12].setOwner(p); // TEMP

		if (playerlist.contains(p)) {

			// check fields los, zusatzsteuer, gefängnis (nzb/real), free parking
			if (current.getGroup() == FieldGroup.SPECIAL) {
				switch (current.getNumber()) {
				case 0: // GO FIELD
					int money;
					if (Rules.doppleOnGO)
						money = Fields.fi[0].getPrice() * 2;
					else
						money = Fields.fi[0].getPrice();
					p.addMoney(money);
					System.out.println("Der Spieler " + p.getName() + " ist direkt auf \"" + Fields.fi[0].getName()
							+ "\" gelandet und zieht daher " + money + "$ ein! " + p.moneyMessage());
					break;
				case 4: // 1. ZUSATZSTEUER
					p.removeMoney(Fields.fi[4].getMortage(), playerlist);
					System.out.println("Der Spieler " + p.getName() + " ist auf das Feld \"" + Fields.fi[4].getName()
							+ "\" gekommen und muss " + Fields.fi[4].getMortage() + "$ in den Geldpool zahlen. "
							+ p.moneyMessage());
					moneypool += Fields.fi[4].getMortage();
					break;
				case 10: // NUR ZU BESUCH IM GEFÄNGNIS
					System.out.println("Der Spieler " + p.getName() + " ist nur zu Besuch im Gefängnis.");
					break;
				case 20: // FREI PARKEN
					if (Rules.moneypool) {
						p.addMoney(moneypool);
						moneypool = 0;
						System.out.println("Der Spieler " + p.getName() + " ist auf \"" + Fields.fi[20].getName()
								+ "\" gelandet und hat das Geld vom Geldpool bekommen. " + p.moneyMessage());
					} else {
						System.out.println("Der Spieler " + p.getName() + " ist auf \"" + Fields.fi[20].getName()
								+ "\" gelandet. Entspann dich bisschen...");
					}
					break;
				case 30: // INS GEFÄNGNIS
					p.setFieldNumber(10);
					p.setBlocked(Rules.roundsBlockedInPrision);
					System.out.println("Der Spieler " + p.getName() + " muss ins Gefängnis!");
					break;
				case 36: // 2. ZUSATZSTEUER
					p.removeMoney(Fields.fi[36].getMortage(), playerlist);
					System.out.println("Der Spieler " + p.getName() + " ist auf das Feld \"" + Fields.fi[36].getName()
							+ "\" gekommen und muss " + Fields.fi[36].getMortage() + "$ in den Geldpool zahlen. "
							+ p.moneyMessage());
					moneypool += Fields.fi[36].getMortage();
					break;
				default:
					break;
				}
				// check field cards
			} else if (current.getGroup() == FieldGroup.CARDS) {
				System.out.println("Der Spieler " + p.getName()
						+ " ist auf ein Kartenfeld gekommen. Eine Karte wird gezogen und bearbeitet...");
				// get random card
				Card card = cards.getCards().get((int) (Math.random() * cards.getCards().size()));
				//card = cards.getCards().get(19); // TEMP

				// print card
				System.out.println("");
				for (int i = 0; i < card.getText().length() + 4; i++)
					System.out.print("-");
				System.out.println("\n| " + card.getText() + " |");
				for (int i = 0; i < card.getText().length() + 4; i++)
					System.out.print("-");
				System.out.println("\n");

				// check card type and do action
				if (card.getType() == CardType.BEWEGUNG) {
					int amount = card.getLocation() - p.getFieldNumber();
					if (card.getLocation() < p.getFieldNumber())
						amount += 40;
					p.move(amount);
					action(p);
				} else if (card.getType() == CardType.ERHALTUNG) {
					p.addMoney(card.getMoney());
				} else if (card.getType() == CardType.ZAHLUNG) {
					p.removeMoney(card.getMoney(), playerlist);
					moneypool += card.getMoney();
				} else if (card.getType() == CardType.GEFÄNGNIS) {
					p.setFieldNumber(10);
					p.setBlocked(Rules.roundsBlockedInPrision);
				} else if (card.getType() == CardType.HÄUSER) {
					if (card.getLocation() == 1) {
						int money = 25 * p.getHouses() + 100 * p.getHotels();
						p.removeMoney(money, playerlist);
						moneypool += money;
					} else {
						int money = 40 * p.getHouses() + 115 * p.getHotels();
						p.removeMoney(money, playerlist);
						moneypool += money;
					}
				} else if (card.getType() == CardType.GEFÄNGNISFREI) {
					p.setGefängnisfrei(p.getGefängnisfrei() + 1);
				} else if (card.getType() == CardType.SPEZIAL) {
					switch (card.getLocation()) {
					case 0:
						for (Player a : playerlist) {
							a.removeMoney(10, playerlist);
						}
						p.addMoney(playerlist.size() * 10);
						break;
					case 1:
						for (Player a : playerlist) {
							a.addMoney(50);
						}
						p.removeMoney(playerlist.size() * 50, playerlist);
						break;
					case 2:
						p.moveBack(3);
						action(p);
						break;
					case 3:
						int locationOfNextCompany = Fields.fi[p.getFieldNumber()].getNextCompany();
						int amount = locationOfNextCompany - p.getFieldNumber();
						if (locationOfNextCompany < p.getFieldNumber())
							amount += 40;
						p.move(amount);
						action(p);
						break;
					default:
						break;
					}
				}
				// all other possible fields (colors, trainstations and companies)
			} else {
				Player owner = current.getOwner();
				int mortage = 0;
				int eyes = one + two;
				if (owner != null) {
					if (owner != p) {
						if (!current.isHypothek()) {
							if (owner.getBlocked() == 0 || Rules.gettingMortageWhileInPrison) {
								if (current.getGroup() == FieldGroup.TRAINSTATION) {
									int amount = fields.calculateTrainMortage(owner.howManyFromGroup(current));
									p.pay(amount, owner, playerlist);

								} else if (current.getGroup() == FieldGroup.COMPANY) {
									int amount = owner.howManyFromGroup(current);
									if (amount == 1) {
										mortage = eyes * Rules.mortageOneCompanyMuplicatedEyes;
										p.pay(eyes * Rules.mortageOneCompanyMuplicatedEyes, owner, playerlist);
									} else {
										mortage = eyes * Rules.mortageTwoCompanyMuplicatedEyes;
										p.pay(eyes * Rules.mortageTwoCompanyMuplicatedEyes, owner, playerlist);
									}
								} else {
									if (current.getHotel() > 0) {
										p.pay(current.getHotelMortage(), owner, playerlist);
									} else if (current.getHouse() > 0) {
										switch (current.getHouse()) {
										case 1:
											p.pay(current.getHouse1Mortage(), owner, playerlist);
											break;
										case 2:
											p.pay(current.getHouse2Mortage(), owner, playerlist);
											break;
										case 3:
											p.pay(current.getHouse3Mortage(), owner, playerlist);
											break;
										case 4:
											p.pay(current.getHouse4Mortage(), owner, playerlist);
											break;
										default:
											break;
										}
									} else {
										if (owner.hasWholeGroup(current) && Rules.doubleMortageIfYouHaveWholeGroup) {
											mortage = current.getMortage() * 2;
											p.pay(mortage, owner, playerlist);
										} else {
											mortage = current.getMortage();
											p.pay(mortage, owner, playerlist);
										}
									}
								}
								if (playerlist.contains(p))
									text = "Das Feld \"" + current.getName() + "\" gehört "
											+ current.getOwner().getName() + ". Daher muss der Spieler " + p.getName()
											+ " " + mortage + "$ Miete zahlen.";
								System.out.println("Das Feld \"" + current.getName() + "\" gehört "
										+ current.getOwner().getName() + ". Daher muss der Spieler " + p.getName() + " "
										+ mortage + "$ Miete zahlen.");
							} else {
								System.out.println("Du hast Glück! " + owner.getName()
										+ " sitzt im Gefängnis und kann daher deine Miete nicht einsammeln. Du kommst gerade noch so davon...");
							}
						} else {
							System.out.println("Du hast Glück! Das Feld \"" + current.getName()
									+ "\" ist zurzeit ein Hypothek und du musst keine Miete zahlen.");
						}
					} else {
						System.out.println(
								"Das Feld \"" + current.getName() + "\" gehört schon dir. Leg die Füße hoch...");
					}
				} else {

					text = "Das Feld \"" + current.getName() + "\" ist noch frei.";
					System.out.println(
							"Das Feld \"" + current.getName() + "\" ist noch frei. Möchtest du es kaufen? (Y/N)");
					scanmessage.buyOrNot(p, current, playerlist);
				}
			}
		}

		text = "Du hast jetzt die Möglichkeit mit anderen Spielern zu handeln, Häuser/Hotels zu (ver)kaufen oder Hypotheken zu kreieren";
		if (playerlist.contains(p)) {
			System.out.println(
					"\nDu hast jetzt die Möglichkeit mit anderen Spielern zu handeln, Häuser/Hotels zu (ver)kaufen oder Hypotheken zu kreieren");
			trade.trade(p, playerlist);
		}

		messenger.sendInformation(playerlist);

		if (one == two) {
			if (playerlist.contains(p)) {
				System.out.println("Der Spieler " + p.getName()
						+ " hat ein Pasch gewürfelt und darf deshalb noch einmal würfeln.");
			} else {
				newDicer();
				doublets = 0;
			}
		} else {
			newDicer();
			doublets = 0;
		}

		one = 0;
		two = 0;

		// PAUSE
		if (playerlist.size() > 1) {
			try {
				Thread.sleep(Rules.delayAfterRound * 1000);
			} catch (InterruptedException e) {
				System.out.println("Ein Fehler ist aufgetreten.");
			}
		}

		play();

	}

	public void newDicer() {
		if ((dicer + 1) >= playerlist.size())
			dicer = 0;
		else
			dicer++;
	}

}
