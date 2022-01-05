package de.informatik.monopoly.game;

import java.util.ArrayList;
import java.util.Scanner;

import de.informatik.monopoly.field.Field;
import de.informatik.monopoly.field.FieldGroup;
import de.informatik.monopoly.field.Fields;
import de.informatik.monopoly.player.Player;
import de.informatik.rules.Rules;

public class Trade {

	Messenger m = new Messenger();

	public boolean trade(Player p, ArrayList<Player> playerlist) {

		if (playerlist.contains(p)) {
			@SuppressWarnings("resource")
			String action = new Scanner(System.in).nextLine();
			action = action.replace(" ", "");
			if (action.equalsIgnoreCase("stop") || action.equalsIgnoreCase("ende") || action.equalsIgnoreCase("exit")
					|| action.equalsIgnoreCase("weiter") || action.equalsIgnoreCase("next")) {
				System.out.println("Du hast dein Trading beendet.");
				return false;
			} else if (action.equalsIgnoreCase("help") || action.equalsIgnoreCase("hilfe")
					|| action.equalsIgnoreCase("?")) {
				m.helpMenu();
				trade(p, playerlist);
			} else if (action.equalsIgnoreCase("info") || action.equalsIgnoreCase("infos")
					|| action.equalsIgnoreCase("information") || action.equalsIgnoreCase("informationen")) {
				m.sendInformation(playerlist);
				trade(p, playerlist);
			} else if (action.equalsIgnoreCase("showfields") || action.equalsIgnoreCase("showfield")
					|| action.equalsIgnoreCase("sf")) {
				m.showfields(false);
				trade(p, playerlist);
			} else if (action.equalsIgnoreCase("showmyfields") || action.equalsIgnoreCase("showmyfield")
					|| action.equalsIgnoreCase("smf")) {
				m.showmyfields(p, false);
				trade(p, playerlist);
			} else if (action.equalsIgnoreCase("showplayer") || action.equalsIgnoreCase("player")
					|| action.equalsIgnoreCase("players") || action.equalsIgnoreCase("showplayers")
					|| action.equalsIgnoreCase("sp")) {
				m.showPlayer(playerlist);
				trade(p, playerlist);
			} else if (action.equalsIgnoreCase("quit") || action.equalsIgnoreCase("pleite")) {
				System.out.println(
						"Bist du dir sicher, dass du dich als pleite erklären willst? Damit hast du das Spiel verloren und darfst nicht mehr weiter spielen.");
				boolean quit = quit(playerlist, p);
				if (quit) {
					System.out.println(
							"Der Spieler " + p.getName() + " hat sich pleite erklärt und das Spiel verlassen.");
				} else {
					System.out.println("Du hast das Spiel nicht verlassen.");
					trade(p, playerlist);
				}
			} else if (action.indexOf(':') != -1) {
				String[] split = action.split(":");
				if (split[0].equalsIgnoreCase("hypothek")) {
					try {
						Field f = Fields.fi[Integer.parseInt(split[1])];
						if (f != null) {
							if (f.getOwner() == p) {
								if ((f.getHotel() == 0 && f.getHouse() == 0) || Rules.hypothekOnHousesAndHotels) {
									if (Boolean.parseBoolean(split[2])) {
										if (!f.isHypothek()) {
											f.setHypothek(true);
											p.addMoney(f.getPrice() / 2);
											System.out.println("Du hast das Feld \"" + f.getName()
													+ "\" zu einer Hypotheke gemacht und " + f.getPrice() / 2
													+ "$ erhalten.");
											trade(p, playerlist);
										} else {
											System.out.println("Diese Feld ist bereits eine Hypotheke.");
											trade(p, playerlist);
										}
									} else {
										if (f.isHypothek()) {
											f.setHypothek(false);
											int price = (int) ((1 + Rules.hypothekDissolveRate) * (f.getPrice() / 2));
											p.removeMoney(price, playerlist);
											System.out.println("Du hast das Feld \"" + f.getName() + "\" für " + price
													+ "$ von der Hypotheke aufgelöst.");
											trade(p, playerlist);
										} else {
											System.out.println("Diese Feld ist schon keine Hypotheke.");
											trade(p, playerlist);
										}
									}
								} else {
									System.out.println("Du kannst auf bebauten Feldern kein Hypothek beziehen.");
									trade(p, playerlist);
								}
							} else {
								System.out.println("Du bist nicht Eigentümer von dem Feld \"" + f.getName() + "\".");
								trade(p, playerlist);
							}
						} else {
							System.out.println("Das Feld existiert nicht.");
							trade(p, playerlist);
						}
					} catch (Exception e) {
						System.out.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
						trade(p, playerlist);
					}
				} else if (split[0].equalsIgnoreCase("buy")) {
					try {
						if (split[1].equalsIgnoreCase("haus") || split[1].equalsIgnoreCase("hotel")) {
							Field f = Fields.fi[Integer.parseInt(split[2])];
							if (f != null) {
								if (f.getOwner() == p) {
									if (f.getGroup() != FieldGroup.COMPANY && f.getGroup() != FieldGroup.TRAINSTATION) {
										if (p.hasWholeGroup(f)) {
											if (split[1].equalsIgnoreCase("haus")) {
												if (Rules.houses > 0) {
													if (f.getHotel() == 0) {
														if (f.getHouse() < 4) {
															f.setHouse(f.getHouse() + 1);
															p.removeMoney(f.getHousePrice(), playerlist);
															System.out.println(
																	"Du hast erfolgreich ein Haus für das Feld \""
																			+ f.getName()
																			+ "\" gekauft (Häuser auf dem Feld: "
																			+ f.getHouse() + ").");
															trade(p, playerlist);
														} else if (f.getHouse() == 4) {
															f.setHouse(0);
															f.setHotel(1);
															p.removeMoney(f.getHousePrice(), playerlist);
															System.out.println(
																	"Du hast erfolgreich das Hotel für das Feld \""
																			+ f.getName() + "\" gekauft.");
															trade(p, playerlist);
														}
													} else {
														System.out.println(
																"Du hast schon ein Hotel auf deinem Feld. Mehr als das geht leider nicht mehr...");
														trade(p, playerlist);
													}
												} else {
													System.out.println("Es sind keine Häuser mehr übrig.");
													trade(p, playerlist);
												}
											} else if (split[1].equalsIgnoreCase("hotel")) {
												if (Rules.buyHotelDirectly) {
													if (Rules.hotels > 0) {
														if (f.getHotel() == 0) {
															f.setHouse(0);
															f.setHotel(1);
															p.removeMoney((5 - f.getHouse()) * f.getHousePrice(),
																	playerlist);
															System.out.println(
																	"Du hast erfolgreich das Hotel für das Feld \""
																			+ f.getName() + "\" gekauft.");
															trade(p, playerlist);
														} else {
															System.out.println(
																	"Du hast schon ein Hotel auf deinem Feld. Mehr als das geht leider nicht mehr...");
															trade(p, playerlist);
														}
													} else {
														System.out.println("Es sind keine Hotels mehr übrig.");
														trade(p, playerlist);
													}
												} else {
													System.out.println(
															"Du musst erst vier Häuser besitzen um ein Hotel kaufen zu können.");
												}
											} else {
												System.out.println(
														"Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
												trade(p, playerlist);
											}
										} else {
											System.out.println(
													"Du kannst erst Häuser und Hotels bauen wenn du alle Farben deines Feldes besitzt.");
											trade(p, playerlist);
										}
									} else {
										System.out.println(
												"Du kannst keine Häuser und Hotels für Bahnhöfe oder Versorgungswerke kaufen.");
										trade(p, playerlist);
									}
								} else {
									System.out
											.println("Du bist nicht Eigentümer von dem Feld \"" + f.getName() + "\".");
									trade(p, playerlist);
								}
							} else {
								System.out.println("Das Feld existiert nicht.");
								trade(p, playerlist);
							}
						} else {
							System.out
									.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
							trade(p, playerlist);
						}
					} catch (Exception e) {
						System.out.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
						trade(p, playerlist);
					}
				} else if (split[0].equalsIgnoreCase("sell")) {
					try {
						if (split[1].equalsIgnoreCase("haus") || split[1].equalsIgnoreCase("hotel")) {
							Field f = Fields.fi[Integer.parseInt(split[2])];
							if (f != null) {
								if (f.getOwner() == p) {
									if (split[1].equalsIgnoreCase("haus")) {
										if (f.getHouse() > 0) {
											f.setHouse(f.getHouse() - 1);
											p.addMoney((int) (f.getHousePrice() * Rules.houseDissolveRate));
											Rules.houses++;
											System.out.println(
													"Ein Haus des Feldes \"" + f.getName() + "\" wurde aufgelöst.");
											trade(p, playerlist);
										} else {
											System.out.println("Dieses Feld hat keine Häuser.");
											trade(p, playerlist);
										}
									} else if (split[1].equalsIgnoreCase("hotel")) {
										if (f.getHotel() > 0) {
											f.setHotel(0);
											p.addMoney((int) ((f.getHousePrice() * 5) * Rules.hotelDissolveRate));
											Rules.hotels++;
											System.out.println(
													"Das Hotel des Feldes \"" + f.getName() + "\" wurde aufgelöst.");
											trade(p, playerlist);
										} else {
											System.out.println("Dieses Feld hat kein Hotel.");
											trade(p, playerlist);
										}
									} else {
										System.out.println(
												"Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
										trade(p, playerlist);
									}
								} else {
									System.out
											.println("Du bist nicht Eigentümer von dem Feld \"" + f.getName() + "\".");
									trade(p, playerlist);
								}
							} else {
								System.out.println("Das Feld existiert nicht.");
								trade(p, playerlist);
							}
						} else {
							System.out
									.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
							trade(p, playerlist);
						}
					} catch (Exception e) {
						System.out.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
						trade(p, playerlist);
					}
				} else if (split[0].equalsIgnoreCase("trade")) {
					// p1 --> trade:p2:feldvonp1,feldvonp2:geldvonp1,geldvonp2
					// 0 1 2 3
					try {

						Player p2 = getPlayer(playerlist, split[1]);
						Field giveFieldP1 = Fields.fi[Integer.parseInt(split[2].split(",")[0])];
						Field giveFieldP2 = Fields.fi[Integer.parseInt(split[2].split(",")[1])];
						int giveMoneyP1 = Integer.parseInt(split[3].split(",")[0]);
						int giveMoneyP2 = Integer.parseInt(split[3].split(",")[1]);

						System.out.println(p2.getName() + " " + giveFieldP1.getName() + " " + giveFieldP2.getName()
								+ " " + giveMoneyP1 + " " + giveMoneyP2);

						// p1 darf nicht mit sich selbst traden
						// p1 und p2 müssen ihre selbst felder besitzen
						//

						/*
						 * if (f.getOwner() == p) {
						 * 
						 * } else {
						 * System.out.println("Du bist nicht der Besitzer des Felds mit der Nummer " +
						 * f.getNumber()); }
						 */

					} catch (Exception e) {
						System.out.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
						trade(p, playerlist);
					}

				} else if (split[0].equalsIgnoreCase("showfield") || split[0].equalsIgnoreCase("showfields")
						|| split[0].equalsIgnoreCase("showmyfield") || split[0].equalsIgnoreCase("showmyfields")
						|| split[0].equalsIgnoreCase("smf") || split[0].equalsIgnoreCase("sf")) {
					try {
						if (split[1].equalsIgnoreCase("detail") || split[1].equalsIgnoreCase("details")
								|| split[1].equalsIgnoreCase("detailed") || split[1].equalsIgnoreCase("d")) {
							if (split[0].equalsIgnoreCase("showfield") || split[0].equalsIgnoreCase("showfields")
									|| split[0].equalsIgnoreCase("sf")) {
								m.showfields(true);
								trade(p, playerlist);
							} else if (split[0].equalsIgnoreCase("showmyfield")
									|| split[0].equalsIgnoreCase("showmyfields") || split[0].equalsIgnoreCase("smf")) {
								m.showmyfields(p, true);
								trade(p, playerlist);
							}
						} else {
							System.out
									.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
							trade(p, playerlist);
						}
					} catch (Exception e) {
						System.out.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
						trade(p, playerlist);
					}
				} else {
					System.out.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
					trade(p, playerlist);
				}
			} else {
				System.out.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
				trade(p, playerlist);
			}
		}
		return false;
	}

	public boolean quit(ArrayList<Player> playerlist, Player p) {
		@SuppressWarnings("resource")
		String scan = new Scanner(System.in).nextLine();
		if (scan.equalsIgnoreCase("ja") || scan.equalsIgnoreCase("yes") || scan.equalsIgnoreCase("y")) {
			playerlist.remove(p);
			return true;
		} else {
			return false;
		}
	}

	public void preSettings(ArrayList<Player> playerlist) {

		@SuppressWarnings("resource")
		String action = new Scanner(System.in).nextLine();
		action = action.replace(" ", "");
		if (action.indexOf(":") != -1) {
			String[] split = action.split(":");
			try {
				if (split[0].equalsIgnoreCase("addplayer") || split[0].equalsIgnoreCase("ap")) {
					if (!split[1].equals("")) {
						if (!getPlayerWithSameName(playerlist, split[1])) {
							playerlist.add(new Player(split[1]));
							System.out.println("Der Spieler \"" + split[1] + "\" wurde erfolgreich hinzugefügt.");
							preSettings(playerlist);
						} else {
							System.out.println("Der Spieler mit dem Namen \"" + split[1] + "\" existiert bereits.");
							preSettings(playerlist);
						}
					} else {
						System.out.println("Der Name des Spielers darf nicht nichts sein!");
						preSettings(playerlist);
					}
				} else if (split[0].equalsIgnoreCase("removeplayer") || split[0].equalsIgnoreCase("rp")) {
					if (!split[1].equals("")) {
						if (getPlayerWithSameName(playerlist, split[1])) {
							int id = getPlayerIdByName(playerlist, split[1]);
							if (id != -1) {
								playerlist.remove(id);
								System.out.println("Der Spieler \"" + split[1] + "\" wurde erfolgreich gelöscht.");
								preSettings(playerlist);
							} else {
								System.out.println(
										"Der Spieler mit dem Namen \"" + split[1] + "\" existiert noch garnicht.");
								preSettings(playerlist);
							}
						} else {
							System.out
									.println("Der Spieler mit dem Namen \"" + split[1] + "\" existiert noch garnicht.");
							preSettings(playerlist);
						}
					} else {
						System.out.println("Der Name des Spielers darf nicht nichts sein!");
						preSettings(playerlist);
					}
				} else {
					System.out.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
					preSettings(playerlist);
				}
			} catch (Exception e) {
				System.out.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
				preSettings(playerlist);
			}
		} else if (action.equalsIgnoreCase("start") || action.equalsIgnoreCase("startgame")) {
			if (playerlist.size() >= 2) {
				System.out.println("Das Monopolyspiel beginnt.");
			} else {
				System.out.println(
						"Es sind zu weniger Spieler online. (Mindest Anzahl an Spieler: " + Rules.minPlayer + ")");
				preSettings(playerlist);
			}
		} else if (action.equalsIgnoreCase("showplayer") || action.equalsIgnoreCase("player")
				|| action.equalsIgnoreCase("players") || action.equalsIgnoreCase("showplayers")
				|| action.equalsIgnoreCase("sp")) {
			if (playerlist.size() > 0) {
				m.showPlayer(playerlist);
				preSettings(playerlist);
			} else {
				System.out.println("Es sind noch keine Spieler vorhanden.");
				preSettings(playerlist);
			}
		} else if (action.equalsIgnoreCase("help") || action.equalsIgnoreCase("?")) {
			m.helpMenuStart();
			preSettings(playerlist);
		} else {
			System.out.println("Deine Eingabe war ungültig bitte versuche es erneut. (Fragen? --> help)");
			preSettings(playerlist);
		}

	}

	private boolean getPlayerWithSameName(ArrayList<Player> playerlist, String name) {
		for (int i = 0; i < playerlist.size(); i++) {
			if (playerlist.get(i).getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public static int getPlayerIdByName(ArrayList<Player> playerlist, String name) {
		for (int i = 0; i < playerlist.size(); i++) {
			if (playerlist.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	public static Player getPlayer(ArrayList<Player> playerlist, String name) {
		if (getPlayerIdByName(playerlist, name) != -1) {
			return playerlist.get(getPlayerIdByName(playerlist, name));
		} else {
			return null;
		}
	}

}
